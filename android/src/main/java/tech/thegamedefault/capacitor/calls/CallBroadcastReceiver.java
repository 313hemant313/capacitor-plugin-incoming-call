package tech.thegamedefault.capacitor.calls;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class CallBroadcastReceiver extends BroadcastReceiver {

    CallDetector.CallStateChangeListener callStateChangeListener = null;
    private final PhoneState currentPhoneState = new PhoneState();
    private int prevState = TelephonyManager.CALL_STATE_IDLE;


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("CallBroadcastReceiver", intent.getAction());
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            currentPhoneState.setCallActive(true);
            currentPhoneState.setCallState("OUTGOING_CALL");
        } else {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            int callState = tm.getCallState();
            if (callState == this.prevState) {
                return;
            }
            checkPhoneState(callState, intent);
        }
        callStateChangeListener.onCallStateChanged();
    }

    private void checkPhoneState(int state, Intent intent) {
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                this.currentPhoneState.setCallActive(false);
                this.currentPhoneState.setCallState("IDLE");
                break;

            case TelephonyManager.CALL_STATE_RINGING:
                // called when someone is ringing to this phone
                this.currentPhoneState.setCallActive(true);
                this.currentPhoneState.setCallState("RINGING");
                this.currentPhoneState.setIncomingNumber(intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER));
                break;

            case TelephonyManager.CALL_STATE_OFFHOOK:

                // If call was picked
                if (prevState == TelephonyManager.CALL_STATE_RINGING) {
                    this.currentPhoneState.setCallActive(true);
                    this.currentPhoneState.setCallState("ON_CALL");
                } else {
                    // TODO: Not sure if this is correct.
                    this.currentPhoneState.setCallActive(false);
                    this.currentPhoneState.setCallState("ON_HOLD");
                }
                this.currentPhoneState.setOutgoingNumber(intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER));
                break;
        }
        this.prevState = state;
    }

    public void setCallStateChangeListener(CallDetector.CallStateChangeListener callStateChangeListener) {
        this.callStateChangeListener = callStateChangeListener;
    }

    public PhoneState getCurrentPhoneState() {
        return this.currentPhoneState;
    }

}

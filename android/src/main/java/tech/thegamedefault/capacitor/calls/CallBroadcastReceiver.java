package tech.thegamedefault.capacitor.calls;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class CallBroadcastReceiver extends BroadcastReceiver {

  CallDetector.CallStateChangeListener callStateChangeListener = null;
  private final PhoneState currentPhoneState = new PhoneState();
  private int prevState = TelephonyManager.CALL_STATE_IDLE;


  @Override
  public void onReceive(Context context, Intent intent) {





    Log.i("CallBroadcastReceiver", "CallBroadcastReceiver " + intent.getAction());
    if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
      //currentPhoneState.setCallActive(true);
      //currentPhoneState.setCallState("OUTGOING_CALL");
      Log.i("CallBroadcastReceiver", "CallBroadcastReceiver ACTION_NEW_OUTGOING_CALL");

      Log.e("CallBroadcastReceiver", "CallBroadcastReceiver" + intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER));
      int callState = 3;
            /*if (callState == this.prevState) {
                return;
            }*/
      openApp(context);

      checkPhoneState(callState, intent);
    } else {
      TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
      if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
        return;
      }
      int callState = tm.getCallState();
            /*if (callState == this.prevState) {
                return;
            }*/
      checkPhoneState(callState, intent);
    }

  }

  private void checkPhoneState(int state, Intent intent) {
    Log.e("CallBroadcastReceiver", "CallBroadcastReceiver STATE" + state);

    Bundle extras = intent.getExtras();

    // Verificar si hay extras y procesarlos
    if (extras != null) {
      // Iterar a través de todas las claves (keys) en el conjunto de extras
      for (String key : extras.keySet()) {
        // Obtener el valor asociado con la clave
        Object value = extras.get(key);

        // Puedes hacer algo con la clave y el valor aquí
        Log.e("MainActivity", " CallBroadcastReceiver Key: " + key + ", Value: " + value);
      }
    }

    switch (state) {
      case TelephonyManager.CALL_STATE_IDLE:
        this.currentPhoneState.setCallActive(false);
        this.currentPhoneState.setCallState("IDLE");

        this.currentPhoneState.setIncomingNumber(intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER));
        this.currentPhoneState.setOutgoingNumber(intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER));
        break;
      case 3: // llamando
        currentPhoneState.setCallActive(true);
        currentPhoneState.setCallState("OUTGOING");

        this.currentPhoneState.setIncomingNumber(intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER));
        this.currentPhoneState.setOutgoingNumber(intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER));
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

        this.currentPhoneState.setIncomingNumber(intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER));
        this.currentPhoneState.setOutgoingNumber(intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER));

        break;
    }
    this.prevState = state;

    if (callStateChangeListener != null) {
      callStateChangeListener.onCallStateChanged();
    }else{
      Log.e("CallBroadcastReceiver", "CallBroadcastReceiver CALLSTATECHANGELISTENER IS NULL ");

    }
  }

  private void openApp(Context context) {
    Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.xerintel.univial2007");
    if (launchIntent != null) {
      // La aplicación está instalada, inicia la actividad principal
      context.startActivity(launchIntent);

      //callStateChangeListener.onCallStateChanged();
    } else {
      // La aplicación no está instalada
      // Puedes manejar este caso según tus requisitos
    }
  }

  public void setCallStateChangeListener(CallDetector.CallStateChangeListener callStateChangeListener) {
    this.callStateChangeListener = callStateChangeListener;
  }

  public PhoneState getCurrentPhoneState() {
    return this.currentPhoneState;
  }

}

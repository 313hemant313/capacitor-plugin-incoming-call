//package tech.thegamedefault.capacitor.calls;
//
//import android.telephony.PhoneStateListener;
//import android.telephony.TelephonyManager;
//
//public class CallStateListener extends PhoneStateListener {
//
//    private final PhoneState currentPhoneState = new PhoneState();
//    private int prevState = TelephonyManager.CALL_STATE_IDLE;
//
//    @Override
//    public void onCallStateChanged(int state, String incomingNumber) {
//        switch (state) {
//            case TelephonyManager.CALL_STATE_IDLE:
//                this.currentPhoneState.setCallActive(false);
//                this.currentPhoneState.setCallState("CALL_STATE_IDLE");
//                break;
//
//            case TelephonyManager.CALL_STATE_RINGING:
//                // called when someone is ringing to this phone
//                this.currentPhoneState.setCallActive(true);
//                this.currentPhoneState.setCallState("CALL_STATE_RINGING");
//                break;
//
//            case TelephonyManager.CALL_STATE_OFFHOOK:
//
//                // If call was picked
//                if (prevState == TelephonyManager.CALL_STATE_RINGING)
//                    this.currentPhoneState.setCallActive(true);
//                else
//                    this.currentPhoneState.setCallActive(false);
//
//                this.currentPhoneState.setCallState("CALL_STATE_OFFHOOK");
//                break;
//        }
//        this.prevState = state;
//    }
//
//    public PhoneState getCurrentPhoneState() {
//        return currentPhoneState;
//    }
//}

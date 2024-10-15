package tech.thegamedefault.capacitor.calls;

import org.json.JSONException;
import org.json.JSONObject;

public class PhoneState {
  boolean callActive;
  String callState; // RINGING, OUTGOING, IDLE, ON_CALL, ON_HOLD .. etc

  String incomingNumber;

  String outgoingNumber;

  public String getOutgoingNumber() {
    return outgoingNumber;
  }

  public void setOutgoingNumber(String outgoingNumber) {
    this.outgoingNumber = outgoingNumber;
  }

  public String getIncomingNumber() {
    return incomingNumber;
  }

  public void setIncomingNumber(String incomingNumber) {
    this.incomingNumber = incomingNumber;
  }

  public PhoneState() {
  }

  public boolean isCallActive() {
    return callActive;
  }

  public void setCallActive(boolean callActive) {
    this.callActive = callActive;
  }

  public String getCallState() {
    return callState;
  }

  public void setCallState(String callState) {
    this.callState = callState;
  }

  public JSONObject toJsonObject() {
    JSONObject jsonObject = new JSONObject();
    try {
      jsonObject.put("callActive", isCallActive());
      jsonObject.put("callState", getCallState());
      jsonObject.put("incomingNumber", getIncomingNumber());
      jsonObject.put("outgoingNumber", getOutgoingNumber());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return jsonObject;
  }

}

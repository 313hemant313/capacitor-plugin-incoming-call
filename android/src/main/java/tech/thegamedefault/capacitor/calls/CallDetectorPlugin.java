package tech.thegamedefault.capacitor.calls;

import android.Manifest;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;

import org.json.JSONException;

@CapacitorPlugin(
  name = "CallDetector",
  permissions = {
    @Permission(
      alias = "phone_calls",
      strings = {
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.PROCESS_OUTGOING_CALLS
      }
    )
  }
)
public class CallDetectorPlugin extends Plugin {

  private CallDetector implementation = new CallDetector();
  public static final String CALL_STATE_EVENT = "callStateChange";

  @PluginMethod
  public void echo(PluginCall call) {
    String value = call.getString("value");

    JSObject ret = new JSObject();
    ret.put("value", implementation.echo(value));
    call.resolve(ret);
  }

  @PluginMethod
  public void detectCallState(PluginCall call) {
    String action = call.getString("action");
    JSObject ret = new JSObject();
    if ("ACTIVATE".equals(action)) {
      setupCallDetector();
      call.setKeepAlive(true);
    } else {
      implementation.disableDetectCall();
    }
    ret.put("success", true);
    call.resolve(ret);
  }

  private void setupCallDetector() {
    implementation.setCallStateChangeListener(this::updateCallState);
    implementation.enableDetectCall();
    implementation.startDetection(getActivity());
  }

  private void updateCallState() {
    Log.i("CallDetector", "Notify listeners, CurrentPhoneState: " + implementation.getCurrentPhoneState());
    notifyListeners(CALL_STATE_EVENT, getPhoneStateJSObject(implementation.getCurrentPhoneState()));
  }

  private JSObject getPhoneStateJSObject(PhoneState state) {
    JSObject ret = null;
    try {
      ret = JSObject.fromJSONObject(state.toJsonObject());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return ret;
  }

  @Override
  protected void handleOnDestroy() {
    implementation.setCallStateChangeListener(null);
  }

}

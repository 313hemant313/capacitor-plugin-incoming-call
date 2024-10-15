import type { PluginListenerHandle } from '@capacitor/core';
export interface CallDetectorPlugin {
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
    /**
     * To enable / disable detection of calls
     * options: { action: 'ACTIVATE' | 'DEACTIVATE' }
     *
     */
    detectCallState(options: {
        action: string;
    }): Promise<{
        action: string;
    }>;
    addListener(eventName: 'callStateChange', listenerFunc: CallStateChangeListener): Promise<PluginListenerHandle> & PluginListenerHandle;
}
export interface PhoneState {
    /**
     * Whether there is an active call or not.
     *
     * @since 1.0.0
     */
    callActive: boolean;
    /**
     * The type of call.
     *
     * 'RINGING' | 'OUTGOING' | 'IDLE' | 'ON_CALL' | 'ON_HOLD'
     *
     * @since 1.0.0
     */
    callState: PhoneStateType;
    incomingNumber: string;
    outgoingNumber: string;
}
export declare type PhoneStateType = 'RINGING' | 'OUTGOING' | 'IDLE' | 'ON_CALL' | 'ON_HOLD';
export declare type CallStateChangeListener = (status: PhoneState) => void;

import { WebPlugin } from '@capacitor/core';
import type { CallDetectorPlugin } from './definitions';
export declare class CallDetectorWeb extends WebPlugin implements CallDetectorPlugin {
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
    detectCallState(options: {
        action: string;
    }): Promise<{
        action: string;
    }>;
}

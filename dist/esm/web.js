import { WebPlugin } from '@capacitor/core';
export class CallDetectorWeb extends WebPlugin {
    async echo(options) {
        console.log('ECHO', options);
        return options;
    }
    async detectCallState(options) {
        console.log('Cannot work in web', options);
        return options;
    }
}
//# sourceMappingURL=web.js.map
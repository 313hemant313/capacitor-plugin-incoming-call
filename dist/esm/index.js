import { registerPlugin } from '@capacitor/core';
const CallDetector = registerPlugin('CallDetector', {
    web: () => import('./web').then(m => new m.CallDetectorWeb()),
});
export * from './definitions';
export { CallDetector };
//# sourceMappingURL=index.js.map
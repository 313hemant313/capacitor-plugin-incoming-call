import { registerPlugin } from '@capacitor/core';

import type { CallDetectorPlugin } from './definitions';

const CallDetector = registerPlugin<CallDetectorPlugin>('CallDetector', {
  web: () => import('./web').then(m => new m.CallDetectorWeb()),
});

export * from './definitions';
export { CallDetector };

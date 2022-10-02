// @flow
import type { TurboModule } from 'react-native/Libraries/TurboModule/RCTExport';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
    +multiGet: (keyValueArray: Array<any>, callback: (error: ?string, data: ?string) => void) => void,
    +multiSet: (keyValueArray: Array<any>, callback: (error: ?string) => void) => void,
    +multiRemove: (keyValueArray: Array<any>, callback: (error: ?string) => void) => void,
    +multiMerge: (keyValueArray: Array<any>, callback: (error: ?string) => void) => void,
    +clear: (callback: (error: ?string) => void) => void,
    +getAllKeys: (callback: (error: string, data: string) => void) => void
}

export default (TurboModuleRegistry.get<Spec>(
    'RNC_AsyncSQLiteDBStorage'
    ): ?Spec);
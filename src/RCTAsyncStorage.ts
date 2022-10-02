// @ts-ignore Module '"react-native"' has no exported member 'TurboModuleRegistry'.
import { NativeModules, TurboModuleRegistry } from 'react-native';

const isTurboModuleEnabled = global.__turboModuleProxy != null;
const  RCTAsyncStorage = !isTurboModuleEnabled ? NativeModules['RNC_AsyncSQLiteDBStorage'] : require('../codegen/NativeAsyncStorage').default;

export default RCTAsyncStorage;

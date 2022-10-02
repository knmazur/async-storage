/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.reactnativecommunity.asyncstorage;

import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.react.ReactPackage;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class AsyncStoragePackage extends TurboReactPackage {

    @Nullable
    @Override
    public NativeModule getModule(String name, ReactApplicationContext reactContext) {
        if (name.equals(AsyncStorageModuleImpl.NAME)) {
          if (BuildConfig.AsyncStorage_useNextStorage) {
                try {
                    Class storageClass = Class.forName("com.reactnativecommunity.asyncstorage.next.StorageModule");
                    NativeModule inst = (NativeModule) storageClass.getDeclaredConstructor(new Class[]{ReactContext.class}).newInstance(reactContext);
                    AsyncLocalStorageUtil.verifyAndForceSqliteCheckpoint(reactContext);
                    return inst;
                } catch (Exception e) {
                    String message = "Something went wrong when initializing module:"
                            + "\n"
                            + e.getCause().getClass()
                            + "\n"
                            + "Cause:" + e.getCause().getLocalizedMessage();
                    Log.e("AsyncStorage_Next", message);
                    return null;
                }
            } else {
                return new AsyncStorageModule(reactContext);
            }
        } else {
                return null;
        }
    }

    @Override
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return () -> {
            final Map<String, ReactModuleInfo> moduleInfos = new HashMap<>();
            boolean isTurboModule = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
            moduleInfos.put(
                    AsyncStorageModuleImpl.NAME,
                    new ReactModuleInfo(
                            AsyncStorageModuleImpl.NAME,
                            AsyncStorageModuleImpl.NAME,
                            false, // canOverrideExistingModule
                            false, // needsEagerInit
                            true, // hasConstants
                            false, // isCxxModule
                            isTurboModule  // isTurboModule
            ));
            return moduleInfos;
        };
    }

}
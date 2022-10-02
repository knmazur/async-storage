/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.reactnativecommunity.asyncstorage;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.common.ModuleDataCleaner;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@ReactModule(name = AsyncStorageModuleImpl.NAME)
public final class AsyncStorageModule extends ReactContextBaseJavaModule {

  private AsyncStorageModuleImpl implementation;

  public AsyncStorageModule(ReactApplicationContext reactContext) {
    super(reactContext);
    implementation = new AsyncStorageModuleImpl(reactContext);
  }

  @Override
  public String getName() {
    return AsyncStorageModuleImpl.NAME;
  }

  @Override
  public void initialize() {
    super.initialize();
    implementation.initialize();
  }

  @Override
  public void onCatalystInstanceDestroy() {
    implementation.onCatalystInstanceDestroy();
  }

  /**
   * Given an array of keys, this returns a map of (key, value) pairs for the keys found, and
   * (key, null) for the keys that haven't been found.
   */
  @ReactMethod
  public void multiGet(final ReadableArray keys, final Callback callback) {
    implementation.multiGet(keys, callback);
  }

  /**
   * Inserts multiple (key, value) pairs. If one or more of the pairs cannot be inserted, this will
   * return AsyncLocalStorageFailure, but all other pairs will have been inserted.
   * The insertion will replace conflicting (key, value) pairs.
   */
  @ReactMethod
  public void multiSet(final ReadableArray keyValueArray, final Callback callback) {
    implementation.multiSet(keyValueArray, callback);
  }

  /**
   * Removes all rows of the keys given.
   */
  @ReactMethod
  public void multiRemove(final ReadableArray keys, final Callback callback) {
    implementation.multiRemove(keys, callback);
  }

  /**
   * Given an array of (key, value) pairs, this will merge the given values with the stored values
   * of the given keys, if they exist.
   */
  @ReactMethod
  public void multiMerge(final ReadableArray keyValueArray, final Callback callback) {
    implementation.multiMerge(keyValueArray, callback);
  }

  /**
   * Clears the database.
   */
  @ReactMethod
  public void clear(final Callback callback) {
    implementation.clear(callback);
  }

  /**
   * Returns an array with all keys from the database.
   */
  @ReactMethod
  public void getAllKeys(final Callback callback) {
    implementation.getAllKeys(callback);
  }
}

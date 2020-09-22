package com.sentry_react_native;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;

import io.sentry.core.Sentry;

public class MainCustomModule extends ReactContextBaseJavaModule {

    public MainCustomModule(ReactApplicationContext reactContext) {
        super(reactContext); //required by React Native
    }

    @Override
    //getName is required to define the name of the module represented in JavaScript
    public String getName() {
        return "MainCustomModule";
    }

    @ReactMethod
    public void generateUnHandledError(Callback errorCallback, Callback successCallback) {
        try {
            //successCallback.invoke("success");
            int[] a = new int[-5];

        } catch (IllegalViewOperationException e) {
            errorCallback.invoke(e.getMessage());
        }
    }


    @ReactMethod
    public void generateHandledError(Callback errorCallback, Callback successCallback) {
        try {
            String[] strArr = new String[1];

            try {

                String s1 = strArr[2];
            } catch (Exception e) {
                Sentry.captureException(e);
            }

        } catch (IllegalViewOperationException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

}

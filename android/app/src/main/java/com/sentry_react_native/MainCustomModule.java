package com.sentry_react_native;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;

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
    public void generateError(Callback errorCallback, Callback successCallback) {
        try {
            //System.out.println("Greetings from Java");
            successCallback.invoke("Callback : Greetings from Java");
            int[] a = new int[-5];
        } catch (IllegalViewOperationException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

}

package com.example.android.itsharkandroidproject;

public interface IDataSubscriber {
    void onDataLoaded(String data);
    void onLoadError();
}

package com.example.Leaderboard.api;

public interface ApiResponseCallback<T> {
    void onResponse(T response);
    void onError(Throwable error);
}

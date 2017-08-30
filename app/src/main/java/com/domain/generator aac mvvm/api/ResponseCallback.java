package com.domain.generator aac mvvm.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ashif on 18/8/17,August,2017
 * github.com/SheikhZayed
 */

public class ResponseCallback<T> implements Callback<T> {

  private final ResponseHandler<T> mHandler;

  public ResponseCallback(ResponseHandler<T> mHandler) {
    this.mHandler = mHandler;
  }

  @Override
  public void onResponse(Call<T> call, Response<T> response) {

  }

  @Override
  public void onFailure(Call<T> call, Throwable t) {

  }
}

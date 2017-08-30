package com.domain.generator aac mvvm.conf;

import static com.domain.generator aac mvvm.util.AppConstants.Status.ERROR;
import static com.domain.generator aac mvvm.util.AppConstants.Status.LOADING;
import static com.domain.generator aac mvvm.util.AppConstants.Status.SUCCESS;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.domain.generator aac mvvm.util.AppConstants.Status;

/**
 * Created by Ashif on 22/8/17,August,2017
 * github.com/SheikhZayed
 */

public class Resource<T> {

  @NonNull
  public final Status status;
  @Nullable
  public final T data;
  @Nullable
  public final String message;

  private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
    this.status = status;
    this.data = data;
    this.message = message;
  }

  public static <T> Resource<T> success(@NonNull T data) {
    return new Resource<>(SUCCESS, data, null);
  }

  public static <T> Resource<T> error(String msg, @Nullable T data) {
    return new Resource<>(ERROR, data, msg);
  }

  public static <T> Resource<T> loading(@Nullable T data) {
    return new Resource<>(LOADING, data, null);
  }
}

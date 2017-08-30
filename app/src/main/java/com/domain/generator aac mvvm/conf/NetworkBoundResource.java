package com.domain.generator aac mvvm.conf;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ashif on 22/8/17,August,2017
 * github.com/SheikhZayed
 */

public abstract class NetworkBoundResource<ResultType, RequestType> {

  private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();
  private final AppExecutors mAppExecutors;

  @MainThread
  protected NetworkBoundResource(AppExecutors mAppExecutors) {
    this.mAppExecutors = mAppExecutors;
    result.setValue(Resource.loading(null));
    LiveData<ResultType> dbSource = loadFromDb();
    result.addSource(dbSource, data -> {
      result.removeSource(dbSource);
      if (shouldFetch(data)) {
        fetchFromNetwork(dbSource);
      } else {
        result.addSource(dbSource, newData -> result.setValue(Resource.success(newData)));
      }
    });
  }

  private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
    result.addSource(dbSource, newData -> result.setValue(Resource.loading(newData)));
    createCall().enqueue(new Callback<RequestType>() {
      @Override
      public void onResponse(@NonNull Call<RequestType> call,
          @NonNull Response<RequestType> response) {
        result.removeSource(dbSource);
        saveResult(response.body());
      }

      @Override
      public void onFailure(@NonNull Call<RequestType> call, @NonNull Throwable t) {
        onFetchFailed();
        result.removeSource(dbSource);
        result.addSource(dbSource,
            newData -> result.setValue(Resource.error(t.getMessage(), newData)));
      }
    });
  }

  private void saveResult(RequestType requestType) {
    mAppExecutors.diskIO().execute(
        () -> {
          saveCallResult(requestType);
          mAppExecutors.mainThread().execute(() ->
              result.addSource(loadFromDb(), newData -> {
                result.setValue(Resource.success(newData));
              }));
        });
  }

  @WorkerThread
  protected abstract void saveCallResult(@NonNull RequestType item);

  @MainThread
  protected boolean shouldFetch(@Nullable ResultType data) {
    return true;
  }

  @NonNull
  @MainThread
  protected abstract LiveData<ResultType> loadFromDb();

  @NonNull
  @MainThread
  protected abstract Call<RequestType> createCall();

  @MainThread
  private void onFetchFailed() {
  }

  public final LiveData<Resource<ResultType>> getAsLiveData() {
    return result;
  }
}

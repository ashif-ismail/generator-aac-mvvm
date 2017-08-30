package com.domain.generator aac mvvm.di.modules;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import com.domain.generator aac mvvm.api.LoggingInterceptor;
import com.domain.generator aac mvvm.api.RESTService;
import com.domain.generator aac mvvm.conf.AppDataBase;
import com.domain.generator aac mvvm.data.dao.ContentDao;
import com.domain.generator aac mvvm.data.model.ContentModel.Content;
import com.domain.generator aac mvvm.di.components.VMSubComponent;
import com.domain.generator aac mvvm.util.AppConstants;
import com.domain.generator aac mvvm.util.AppUtils;
import com.domain.generator aac mvvm.util.DialogUtils;
import com.domain.generator aac mvvm.view.ui.ViewModelFactory;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */

@Module(subcomponents = VMSubComponent.class)
public class AppModule {
    /*
    complete app level dependencies should be included here
     */


  @Singleton
  @Provides
  public RESTService providesApiService(OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(RESTService.class);
  }

  @Provides
  @Singleton
  public OkHttpClient providesOkHttpClient(LoggingInterceptor requestInterceptor, Cache cache) {
    OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    okHttpClient.connectTimeout(AppConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
    okHttpClient.readTimeout(AppConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
    okHttpClient.addInterceptor(requestInterceptor);
    okHttpClient.cache(cache);
    return okHttpClient.build();
  }

  @Singleton
  @Provides
  public Cache providesHttpCache(Application application) {
    int cacheSize = 10 * 1024 * 1024;
    Cache cache = new Cache(application.getCacheDir(), cacheSize);
    return cache;
  }

  @Provides
  @Singleton
  public LoggingInterceptor providesRequestInterceptor() {
    return new LoggingInterceptor();
  }

  @Singleton
  @Provides
  public AppUtils providesAppUtils() {
    return new AppUtils();
  }

  @Provides
  @Singleton
  Context providesContext(Application application) {
    return application;
  }

  @Singleton
  @Provides
  ViewModelProvider.Factory provideViewModelFactory(
      VMSubComponent.Builder viewModelSubComponent) {
    return new ViewModelFactory(viewModelSubComponent.build());
  }

  @Provides
  @Singleton
  public Picasso providesPicasso(Application application, OkHttpClient okHttpClient) {
    return new Picasso.Builder(application)
        .downloader(new OkHttp3Downloader(okHttpClient))
        .build();
  }

  @Provides
  @Singleton
  public DialogUtils provideDialogUtils() {
    return new DialogUtils();
  }


  @Provides
  @Singleton
  AppDataBase provideAppDataBase(Application application) {
    return Room.databaseBuilder(application, AppDataBase.class, "SampleApp.db").build();
  }

  @Provides
  @Singleton
  ContentDao provideContentDao(AppDataBase appDataBase) {
    return appDataBase.contentDao();
  }

}

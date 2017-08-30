package a.conf;

import android.app.Activity;
import android.app.Application;
import com.facebook.stetho.Stetho;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;
import a.BuildConfig;
import a.di.components.AppInjector;
import a.util.FireBaseCrashReportingTree;
import timber.log.Timber;
import timber.log.Timber.DebugTree;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */

public class SampleApp extends Application implements HasActivityInjector {


  @Inject
  DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

  @Override
  public AndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    AppInjector.init(this);
    Stetho.initializeWithDefaults(this);
    if (BuildConfig.DEBUG) {
      Timber.plant(new DebugTree());
    } else {
      Timber.plant(new FireBaseCrashReportingTree());
    }
  }
}

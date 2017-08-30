package com.domain.generator aac mvvm.di.components;

import android.app.Application;
import android.content.Context;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;
import com.domain.generator aac mvvm.conf.SampleApp;
import com.domain.generator aac mvvm.di.builder.ActivityBuilder;
import com.domain.generator aac mvvm.di.modules.AppModule;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */


/**
 * AppComponent --> ActivityBuilder(in which all the activities of the app gets listed)
 * --> FragmentsBuilder via Modules.
 * <p>
 * Basically Appcomponent talks to Activity Builder which then talks to FragmentsBuilder.
 * <p>
 * So, AppComponent --> ActivityBuilder --> FragmentsBuilder
 */
@Singleton
@Component(modules = {
    AndroidInjectionModule.class,
    AppModule.class,
    ActivityBuilder.class})
public interface AppComponent {

  void inject(SampleApp app);

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }
}

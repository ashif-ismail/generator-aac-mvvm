package com.domain.generator aac mvvm.util;

import android.util.Log;
import com.google.firebase.crash.FirebaseCrash;
import timber.log.Timber;

/**
 * Created by Ashif on 8/8/17,August,2017
 * github.com/SheikhZayed
 */

public class FireBaseCrashReportingTree extends Timber.Tree {

  @Override
  protected void log(int priority, String tag, String message, Throwable t) {
    if (priority == Log.VERBOSE || priority == Log.DEBUG) {
      return;
    }
    FirebaseCrash.log(message);
    if (t != null) {
      FirebaseCrash.report(t);
    }
  }
}

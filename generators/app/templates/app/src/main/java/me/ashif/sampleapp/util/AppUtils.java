package <%= appPackage %>.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import javax.inject.Inject;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */

public class AppUtils {

  @Inject
  Context mContext;

  public boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = null;
    if (connectivityManager != null) {
      activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    }
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }
}

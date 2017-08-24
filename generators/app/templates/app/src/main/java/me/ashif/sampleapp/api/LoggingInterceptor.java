package <%= appPackage %>.api;

import android.annotation.SuppressLint;
import java.io.IOException;
import <%= appPackage %>.util.AppConstants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

public class LoggingInterceptor implements Interceptor {

  @SuppressLint("DefaultLocale")
  @Override
  public Response intercept(Chain chain) throws IOException {

    Request request = chain.request();
    HttpUrl originalUrl = request.url();

    long sendingTime = System.nanoTime();
    Timber.d(String.format("Sending request %s on %s%n%s",
        request.url(), chain.connection(), request.headers()));

    //if needed to send any queryparam common for each requests
    //for ex : api_key
    HttpUrl urlToHit = originalUrl.newBuilder()
        .addQueryParameter("api_key", AppConstants.API_KEY)
        .build();

    Request serverRequest = request.newBuilder().url(urlToHit).build();
    Response response = chain.proceed(serverRequest);

    long responseTime = System.nanoTime();
    Timber.d(String.format("Received response for %s in %.1fms%n%s",
        response.request().url(), (responseTime - sendingTime) / 1e6d,
        response.headers()));

    return chain.proceed(serverRequest);
  }
}

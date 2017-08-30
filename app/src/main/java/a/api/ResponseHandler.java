package a.api;

/**
 * Created by Ashif on 18/8/17,August,2017
 * github.com/SheikhZayed
 */

public interface ResponseHandler<T> {

  void onResponseSuccess(T response);

  void onResponseFailure(int statusCode, String message);
}

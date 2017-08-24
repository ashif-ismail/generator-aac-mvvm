package <%= appPackage %>.api;

import <%= appPackage %>.data.model.ContentModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

public interface RESTService {

    @GET("SheikhZayed/Fake-Json-Server/db")
    Call<ContentModel> getContentList();
}

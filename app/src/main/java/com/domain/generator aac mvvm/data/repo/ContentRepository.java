package com.domain.generator aac mvvm.data.repo;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.domain.generator aac mvvm.api.RESTService;
import com.domain.generator aac mvvm.conf.AppExecutors;
import com.domain.generator aac mvvm.conf.NetworkBoundResource;
import com.domain.generator aac mvvm.conf.Resource;
import com.domain.generator aac mvvm.data.dao.ContentDao;
import com.domain.generator aac mvvm.data.model.ContentModel;
import com.domain.generator aac mvvm.data.model.ContentModel.Content;
import retrofit2.Call;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

@Singleton
public class ContentRepository {

  private RESTService mApiService;
  private ContentDao mContentDao;
  private AppExecutors mAppExecutors;

  @Inject
  public ContentRepository(RESTService mApiService, ContentDao mContentDao,
      AppExecutors mAppExecutors) {
    this.mContentDao = mContentDao;
    this.mApiService = mApiService;
    this.mAppExecutors = mAppExecutors;
  }

  public LiveData<Resource<List<Content>>> getContentList() {
    return new NetworkBoundResource<List<Content>, ContentModel>(mAppExecutors) {
      @Override
      protected void saveCallResult(@NonNull ContentModel item) {
        mContentDao.saveContent(item.getContent());
      }

      @NonNull
      @Override
      protected LiveData<List<Content>> loadFromDb() {
        return mContentDao.loadContents();
      }

      @NonNull
      @Override
      protected Call<ContentModel> createCall() {
        return mApiService.getContentList();
      }
    }.getAsLiveData();
  }
}

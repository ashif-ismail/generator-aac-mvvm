package <%= appPackage %>.view.ui.home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.util.List;
import javax.inject.Inject;
import <%= appPackage %>.conf.Resource;
import <%= appPackage %>.data.model.ContentModel.Content;
import <%= appPackage %>.data.repo.ContentRepository;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

public class HomeViewModel extends AndroidViewModel {

    private final LiveData<Resource<List<Content>>> contentListObservable;

    @Inject
    public HomeViewModel(Application application, ContentRepository contentRepository) {
        super(application);
        this.contentListObservable = contentRepository.getContentList();
    }

    public LiveData<Resource<List<Content>>> getContentListObservable() {
        return contentListObservable;
    }
}

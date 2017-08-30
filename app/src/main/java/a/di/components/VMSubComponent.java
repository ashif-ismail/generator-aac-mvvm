package a.di.components;

import dagger.Subcomponent;
import a.view.ui.home.HomeViewModel;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

@Subcomponent
public interface VMSubComponent {
    @Subcomponent.Builder
    interface Builder {
        VMSubComponent build();
    }

    HomeViewModel homeViewModel();
    //all viewmodel should be listed here.
}

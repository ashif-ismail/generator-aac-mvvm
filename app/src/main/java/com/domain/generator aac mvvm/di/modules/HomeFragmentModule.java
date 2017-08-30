package com.domain.generator aac mvvm.di.modules;

import dagger.Module;
import dagger.Provides;
import com.domain.generator aac mvvm.view.ui.home.HomeFragment;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */

@Module
public class HomeFragmentModule {
    /*
    all the dependencies specific to the fragment must
    be listed here,optional you can skip this class creation
    if you dont have any dependencies specific to this fragment
     */

    @Provides
//    @Singleton
    public HomeFragment providesBlankFragment(HomeFragment homeFragment){
        return homeFragment;
    }
}

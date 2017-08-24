package <%= appPackage %>.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import <%= appPackage %>.view.ui.MainActivity;

/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */

@Module
public class MainActivityModule {

    /*
   all the dependencies specific to the activity must
   be listed here,optional you can skip this class creation
   if you dont have any dependencies specific to this activity
    */

    @Provides
    @Singleton
    public MainActivity providesMainActivty(MainActivity mainActivity){
        return mainActivity;
    }
}

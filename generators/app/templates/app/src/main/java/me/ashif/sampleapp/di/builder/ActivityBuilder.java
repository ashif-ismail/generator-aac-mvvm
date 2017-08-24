package <%= appPackage %>.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import <%= appPackage %>.view.ui.MainActivity;


/**
 * Created by Ashif on 3/8/17,August,2017
 * github.com/SheikhZayed
 */

@Module
public abstract class ActivityBuilder {

    /*
    All activities that are to be attached to
    dagger should register here.

    Application has Activities,Activities has Fragments
     */

    @ContributesAndroidInjector(modules = FragmentBuilder.class)
    abstract MainActivity providesMainActivity();
}

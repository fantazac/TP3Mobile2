package ca.csf.mobile2.tp3;

import android.support.test.espresso.core.deps.dagger.Component;

/**
 * Created by Alexandre on 2017-02-17.
 */
@ActivityScope
@Component(dependencies = {ApplicationComponent.class})
public interface MainActivityComponent {

    //void inject(MainActivity mainActivity);
}

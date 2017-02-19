package ca.csf.mobile2.tp3.databinding.components;

import ca.csf.mobile2.tp3.activity.MainActivity;
import ca.csf.mobile2.tp3.databinding.scope.ActivityScope;
import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponent.class})
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}

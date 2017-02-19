package ca.csf.mobile2.tp3.databinding.components;

import ca.csf.mobile2.tp3.activity.DayRemindersActivity;
import ca.csf.mobile2.tp3.databinding.scope.ActivityScope;
import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponent.class})
public interface DayRemindersActivityComponent {

    void inject(DayRemindersActivity dayRemindersActivity);
}

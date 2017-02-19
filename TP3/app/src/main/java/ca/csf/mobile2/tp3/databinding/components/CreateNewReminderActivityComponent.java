package ca.csf.mobile2.tp3.databinding.components;

import ca.csf.mobile2.tp3.activity.CreateNewReminderActivity;
import ca.csf.mobile2.tp3.databinding.scope.ActivityScope;
import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponent.class})
public interface CreateNewReminderActivityComponent {

    void inject(CreateNewReminderActivity createNewReminderActivity);
}

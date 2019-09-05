package ru.pavlenko.julia.main;

import androidx.fragment.app.FragmentActivity;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {
        MainModule.class
})
interface MainComponent {

    void inject(MainActivity activity);

    @Component.Builder
    interface Builder {
        MainComponent build();
        @BindsInstance
        Builder fragmentActivity(FragmentActivity fragmentActivity);
    }

}

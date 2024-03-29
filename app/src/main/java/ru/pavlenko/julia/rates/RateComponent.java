package ru.pavlenko.julia.rates;

import dagger.BindsInstance;
import dagger.Component;
import ru.pavlenko.julia.data.DataModule;
import ru.pavlenko.julia.vm.ViewModelModule;

@Component(modules = {
        RateModule.class,
        ViewModelModule.class
})
interface RateComponent {

    void inject(RateFragment fragment);

    void inject(CurrencyDialog dialog);

    @Component.Builder
    interface Builder {
        RateComponent build();

        @BindsInstance
        Builder rateFragment(RateFragment rateFragment);
    }
}

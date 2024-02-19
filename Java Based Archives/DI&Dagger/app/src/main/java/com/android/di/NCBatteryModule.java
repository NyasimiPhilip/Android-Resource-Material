package com.android.di;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
abstract class NCBatteryModule {

    @Binds
    abstract Battery bindNCBattery(NickelCadmiumBattery nickelCadmiumBattery);

}

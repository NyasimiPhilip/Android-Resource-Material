package com.android.di;

import dagger.Component;

@Component(modules = {MemoryCardModule.class, NCBatteryModule.class})
public interface SmartPhoneComponent {
/*SmartPhone getSmartPhone();*/
      void inject (MainActivity mainActivity);
}

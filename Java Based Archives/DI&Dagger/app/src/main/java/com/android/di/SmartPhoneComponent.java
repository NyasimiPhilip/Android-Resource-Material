package com.android.di;

import dagger.Component;

@Component(modules = MemoryCardModule.class)
public interface SmartPhoneComponent{

      SmartPhone getSmartPhone();
}

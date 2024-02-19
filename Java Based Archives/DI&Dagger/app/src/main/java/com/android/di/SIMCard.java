package com.android.di;

import javax.inject.Inject;

public class SIMCard {

    private ServiceProvider serviceProvider;

    @Inject
    public SIMCard(ServiceProvider serviceProvider) {

        this.serviceProvider=serviceProvider;
    }
}

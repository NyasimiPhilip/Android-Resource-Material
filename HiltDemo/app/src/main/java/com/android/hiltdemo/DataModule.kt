package com.android.hiltdemo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    // @Provides is used to tell Dagger how to provide instances of the specified type (DataSource)
    @Provides
    fun providesDataSource(): DataSource {
        // Here, a new instance of DataSource is created and provided
        return DataSource()
    }
}

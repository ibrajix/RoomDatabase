package com.ibrajix.roomdatabase.global

import com.ibrajix.roomdatabase.preferences.PreferenceImpl
import com.ibrajix.roomdatabase.preferences.PreferenceStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    abstract fun bindsPreferenceStorage(preferenceStorageImpl: PreferenceImpl): PreferenceStorage

}
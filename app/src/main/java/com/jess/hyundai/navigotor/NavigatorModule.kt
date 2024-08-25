package com.jess.hyundai.navigotor

import com.jess.hyundai.navigator.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigatorModule {

    @Singleton
    @Provides
    fun provideNavigator(): Navigator {
        return NavigatorImpl()
    }
}

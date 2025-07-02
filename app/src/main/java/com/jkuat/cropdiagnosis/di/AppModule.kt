package com.jkuat.cropdiagnosis.di

import android.content.Context
import com.jkuat.cropdiagnosis.MLClassifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMLClassifier(
        @ApplicationContext appContext: Context
    ): MLClassifier {
        return MLClassifier(appContext)
    }
}

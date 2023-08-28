package com.hakkasuru.arcana.core.di

import android.content.Context
import com.hakkasuru.arcana.api.repository.ArcanaRepository
import com.hakkasuru.arcana.core.arcana.ArcanaStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideArcanaRepo() = ArcanaRepository()

    @Singleton
    @Provides
    fun provideArcanaStore(@ApplicationContext context: Context) = ArcanaStore(context)
}
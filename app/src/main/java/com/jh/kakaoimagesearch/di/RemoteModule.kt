package com.jh.kakaoimagesearch.di

import com.jh.kakaoimagesearch.data.remote.RetrofitCreator
import com.jh.kakaoimagesearch.data.remote.datasource.SearchDataSource
import com.jh.kakaoimagesearch.data.remote.datasource.SearchDataSourceImpl
import com.jh.kakaoimagesearch.data.remote.service.SearchService
import com.jh.kakaoimagesearch.data.repository.RemoteRepository
import com.jh.kakaoimagesearch.data.repository.RemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteProvidesModule {
    @Provides
    fun providesSearchService(): SearchService {
        return RetrofitCreator.createRetrofit().create(SearchService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteBindsModule {
    @Binds
    abstract fun bindsSearchDataSource(searchDataSourceImpl: SearchDataSourceImpl): SearchDataSource

    @Binds
    abstract fun bindsRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository
}
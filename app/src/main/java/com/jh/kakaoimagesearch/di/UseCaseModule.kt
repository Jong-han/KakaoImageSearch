package com.jh.kakaoimagesearch.di

import com.jh.kakaoimagesearch.usecase.SearchUseCase
import com.jh.kakaoimagesearch.usecase.SearchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindsSearchUseCase(searchUseCaseImpl: SearchUseCaseImpl): SearchUseCase
}
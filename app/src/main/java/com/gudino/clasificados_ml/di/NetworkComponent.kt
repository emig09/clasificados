package com.gudino.clasificados_ml.di

import com.gudino.clasificados_ml.ClassifiedsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface NetworkComponent {
    /**
     * Injects required dependencies into the specified ClassifiedsViewModel.
     * @param classifiedsViewModel ClassifiedsViewModel in which to inject the dependencies
     */
    fun inject(classifiedsViewModel: ClassifiedsViewModel)

    @Component.Builder
    interface Builder {

        fun build(): NetworkComponent

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
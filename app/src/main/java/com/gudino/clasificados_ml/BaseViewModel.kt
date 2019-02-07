package com.gudino.clasificados_ml

import android.arch.lifecycle.ViewModel
import com.gudino.clasificados_ml.di.DaggerNetworkComponent
import com.gudino.clasificados_ml.di.NetworkComponent
import com.gudino.clasificados_ml.di.NetworkModule

open class BaseViewModel : ViewModel() {

    private val injector: NetworkComponent = DaggerNetworkComponent
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ClassifiedsViewModel -> injector.inject(this)
        }
    }
}
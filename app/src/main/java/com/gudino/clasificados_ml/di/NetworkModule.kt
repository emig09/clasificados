package com.gudino.clasificados_ml.di

import com.gudino.clasificados_ml.api.ClassifiedsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {

    private const val ML_BASE_URL = "https://api.mercadolibre.com/"

    /**
     * Provides the Classifieds service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Classifieds service implementation.
     */
    @Provides
    internal fun provideClassifiedsApi(retrofit: Retrofit) = retrofit.create(ClassifiedsApi::class.java)

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    internal fun provideRetrofitInterface() =
            Retrofit.Builder()
                    .baseUrl(ML_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
}
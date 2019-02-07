package com.gudino.clasificados_ml

import android.arch.lifecycle.MutableLiveData
import com.gudino.clasificados_ml.api.ClassifiedsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.gudino.clasificados_ml.model.Classified
import com.gudino.clasificados_ml.model.MLResponse
import java.net.UnknownHostException
import javax.inject.Inject

class ClassifiedsViewModel : BaseViewModel() {

    @Inject
    lateinit var classifiedsApi: ClassifiedsApi
    val classifieds = MutableLiveData<List<Classified>>()
    val classifiedDetail = MutableLiveData<Classified>()
    val errors = MutableLiveData<String>()
    val navigate = MutableLiveData<String>()

    /**
     * Gets classifieds given a query/search
     */
    fun getClassifiedsByQuery(searchQuery: String) {
        classifiedsApi.getSearchResponse(searchQuery).enqueue(object : Callback<MLResponse> {

            override fun onResponse(call: Call<MLResponse>?, response: Response<MLResponse>?) {
                classifieds.value = response?.body()?.results
            }

            override fun onFailure(call: Call<MLResponse>?, t: Throwable?) {
                when (t) {
                    is UnknownHostException -> errors.value = "no connectivity"
                    else -> errors.value = "unknown error"
                }
            }
        })
    }

    /**
     * Get detail for an specific classified
     */
    fun loadClassifiedDetail(classifiedId: String) {
        classifiedsApi.getClassifiedDetail(classifiedId).enqueue(object : Callback<Classified> {

            override fun onResponse(call: Call<Classified>?, response: Response<Classified>?) {
                classifiedDetail.value = response?.body()
            }

            override fun onFailure(call: Call<Classified>?, t: Throwable?) {
                when (t) {
                    is UnknownHostException -> errors.value = "no connectivity"
                    else -> errors.value = "unknown error"
                }
            }
        })
    }

    fun navigate(classifiedId: String) {
        navigate.value = classifiedId
        navigate.value = null
    }
}
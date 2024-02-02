package com.example.nawadatatest_option2_newsapi.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nawadatatest_option2_newsapi.model.ResponseDataSource
import com.example.nawadatatest_option2_newsapi.model.Source
import com.example.nawadatatest_option2_newsapi.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(private var api: ApiService) : ViewModel() {
    var liveDataSource: MutableLiveData<List<Source>?> = MutableLiveData()

    fun getDataSource(): MutableLiveData<List<Source>?> {
        return liveDataSource
    }

    fun callApiSource(category: String) {
        api.getAllSources(category)
            .enqueue(object : Callback<ResponseDataSource> {
                override fun onResponse(
                    call: Call<ResponseDataSource>,
                    response: Response<ResponseDataSource>
                ) {
                    if (response.isSuccessful) {
                        liveDataSource.postValue(response.body()?.sources)
                        Log.d(TAG, "onResponse: ${response.body()?.sources?.size}")
                    } else {
                        Log.d(TAG, "onResponse: Unsuccessfully")
                        liveDataSource.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataSource>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                    liveDataSource.postValue(null)
                }
            })
    }
}

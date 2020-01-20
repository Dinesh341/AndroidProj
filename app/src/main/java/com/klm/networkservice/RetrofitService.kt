package com.klm.networkservice

import ProductsCategory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.assignment.kotlinmvvm.interfaces.ApiInterface
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.function.Consumer


class RetrofitService {


    companion object Factory {
        var gson = GsonBuilder().setLenient().create()
        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://mobcategories.s3-website-eu-west-1.amazonaws.com/")
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

}

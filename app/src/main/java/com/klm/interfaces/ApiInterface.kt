package com.assignment.kotlinmvvm.interfaces

import ProductsCategory
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET(".")
    fun getProductList() : Observable<List<ProductsCategory>>

}
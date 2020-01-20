package com.klm.ViewModels


import Products
import ProductsCategory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klm.networkservice.RetrofitService

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var foodList: MutableLiveData<List<ProductsCategory>> = MutableLiveData()

    fun getProductList(): MutableLiveData<List<ProductsCategory>>? = foodList

    fun loadProductList() {
        val disposable = RetrofitService.create().getProductList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                foodList.value = result
            }, { error ->
                error.printStackTrace()
            }
        )
        compositeDisposable.add(disposable)
    }

    // This is called by the Android Activity when the activity is destroyed
    public override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}

package com.klm

import Products
import ProductsCategory
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.adapter.ProductListAdapter
import com.klm.ViewModels.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.RecyclerView
import com.klm.details.ProductDetailsActivity

class MainActivity : AppCompatActivity() {

    private var mAndroidViewModel: ListViewModel? = null

    companion object {
        fun startActivity(context: Context, movie: Products) {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("productdata", movie)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAndroidViewModel = ViewModelProviders.of(this@MainActivity).get(ListViewModel::class.java)
        initObservers()
        mAndroidViewModel?.loadProductList()
    }

    private fun initObservers() {
        mAndroidViewModel?.getProductList()?.observe(this, Observer {
            it?.let {
                showFoodList(it)
            }
        })
    }

    private fun showFoodList(foodList: List<ProductsCategory>) {
        if ((foodList?.isNotEmpty())) {
            addpinner(foodList)
            setAdapter(foodList[0].products)
        }
    }

    private fun addpinner(list: List<ProductsCategory>) {
        val arrayList = ArrayList<String>()
        list.forEach {
            arrayList.add(it.name)
        }
        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(this, R.layout.spinner_row, arrayList)
            arrayAdapter.setDropDownViewResource(R.layout.spinner_row);
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    setAdapter(list[position].products)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }
    }

    private fun setAdapter(products: List<Products>) {
        val adapter = ProductListAdapter(this, products)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

}

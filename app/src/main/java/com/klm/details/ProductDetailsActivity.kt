package com.klm.details

import Products
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.klm.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val productData = intent.getParcelableExtra<Products>("productdata")
        val url = productData.url?.trim()
        name.text = productData.name
        val str1 = productData.salePrice.amount.toString()
        val str2 =  productData.salePrice.currency
        val sb = StringBuilder()
        sb.append(str1).append(str2)
        amount.text = sb.toString()
        if (url != null) {
            Picasso.get().load("http://mobcategories.s3-website-eu-west-1.amazonaws.com${url}")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(productimg)
        } else {
            productimg.setImageResource(R.drawable.ic_launcher_background)
        }
    }

}

package com.adapter

import Products
import ProductsCategory
import android.content.Context
import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.klm.MainActivity
import com.klm.R
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class ProductListAdapter(private val context: Context, private val productsList: List<Products>) :

    RecyclerView.Adapter<ProductListAdapter.MovieViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            val movieListItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.product_items_row, parent, false)
            return MovieViewHolder(movieListItem)
        }

        override fun getItemCount() = productsList.size

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            val products = productsList[position]
            holder.movieTitle.text = products.name ?: ""
            val url = products.url?.trim()
            if (url != null) {
                Picasso.get().load("http://mobcategories.s3-website-eu-west-1.amazonaws.com${url}").placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background).into(holder.moviePoster)
            } else {
                holder.moviePoster.setImageResource(R.drawable.ic_launcher_background)
            }
            holder.itemView.setOnClickListener {
                MainActivity.startActivity(context, products)
            }
        }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val moviePoster: ImageView = itemView.findViewById(R.id.food_poster)
        val movieTitle: TextView = itemView.findViewById(R.id.food_name)

    }



}
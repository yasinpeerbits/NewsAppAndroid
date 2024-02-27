package com.peerbits.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.peerbits.newsapp.R
import com.peerbits.newsapp.models.Article

class NewsListAdapter(
    private val context: Context,
    val items: List<Article>, private val listener: NewsItemClicked
) :
    RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        if (!currentItem.urlToImage.isNullOrEmpty()) {
            Glide.with(context)
                .load(currentItem.urlToImage)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.no_image)
        }
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title = itemView.findViewById<TextView>(R.id.titleText)
    val description = itemView.findViewById<TextView>(R.id.desccriptionText)
    val imageView = itemView.findViewById<ImageView>(R.id.newsImage)

}

interface NewsItemClicked {
    fun onItemClicked(item: Article)
}
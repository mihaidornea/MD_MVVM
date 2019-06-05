package com.example.wd_mvvm.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wd_mvvm.databinding.NewsArticleItemBinding
import com.example.wd_mvvm.models.article.Article
import com.mcxiaoke.koi.ext.onClick

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private var news = mutableListOf<Article>()
    var callback: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsArticleItemBinding.inflate(inflater)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount() = news.size


    fun updateData(data: List<Article>) {
        news.clear()
        news.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindData(news[position])
    }


    inner class ArticleViewHolder(private val binding: NewsArticleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(article : Article) {
            binding.article = article
            Glide.with(binding.root)
                .load(article.urlToImage)
                .into(binding.rviArticleIvImage)
            binding.executePendingBindings()
            binding.root.onClick{
                callback.invoke(position)
            }
        }
    }
}
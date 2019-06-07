package com.example.wd_mvvm.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wd_mvvm.R
import com.example.wd_mvvm.models.NewsApiResponse
import com.example.wd_mvvm.models.article.Article
import com.example.wd_mvvm.utils.isInternetAvailable
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    private var newsViewModel: NewsViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUIElements()
        getViewModel()
    }

    override fun onStart() {
        super.onStart()
        getNews(isInternetAvailable(context))
    }

    private fun getViewModel() {
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

    private fun getNews(isInternetAvailable: Boolean) {
        if (isInternetAvailable)
            newsViewModel!!.getNews()?.observe(this, Observer<NewsApiResponse> {
                displayNews(it.articles)
                newsViewModel!!.insertAll(it.articles)
            })
        else newsViewModel!!.getSoredArticles().observe(this, Observer<List<Article>> {
            displayNews(it as MutableList<Article>)
        })
    }

    private fun loadUIElements() {
        fr_news_rv_news.layoutManager = LinearLayoutManager(context)
        fr_news_rv_news.adapter = NewsAdapter()
        fr_news_iv_back.setOnClickListener {
            navigatePreviousScreen()
        }
    }

    fun navigateNextScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun navigatePreviousScreen() {
        activity?.onBackPressed()
    }

    private fun displayNews(articles: MutableList<Article>) {
        (fr_news_rv_news?.adapter as NewsAdapter).updateData(articles)
    }

    fun displayToast(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }
}
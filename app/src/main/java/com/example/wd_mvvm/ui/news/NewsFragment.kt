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
import androidx.recyclerview.widget.RecyclerView
import com.example.wd_mvvm.R
import com.example.wd_mvvm.databinding.FragmentNewsBinding
import com.example.wd_mvvm.databinding.FragmentNewsBindingImpl
import com.example.wd_mvvm.models.NewsApiResponse
import com.example.wd_mvvm.models.article.Article
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.get

class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        getViewModel()
        binding = FragmentNewsBinding.inflate(inflater, container, false).apply {
            this.viewModel = this@NewsFragment.newsViewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frNewsRvNews.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.frNewsRvNews.adapter = NewsAdapter()
        binding.viewModel?.getNews()
    }

    private fun getViewModel() {
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

    private fun navigateNextScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun navigatePreviousScreen() {
        activity?.onBackPressed()
    }

    fun displayToast(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }
}
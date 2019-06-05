package com.example.wd_mvvm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.wd_mvvm.databinding.FragmentLoginBinding
import com.example.wd_mvvm.ui.phone.PhoneVerificationFragment
import com.example.wd_mvvm.utils.replaceWith

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        getViewModel()
        return FragmentLoginBinding.inflate(inflater, container, false).apply {
            this.viewModel = this@MainFragment.viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    private fun getViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.apply {
            command.observe(this@MainFragment, commandObserver)
        }
    }

    private val commandObserver = Observer<MainViewModel.Command> {
        when (it) {
            is MainViewModel.Command.NextScreen -> {
                navigateNextScreen()
            }
        }
    }

    private fun navigateNextScreen() {
        activity?.let { act ->
            val fragment = PhoneVerificationFragment()
            act replaceWith fragment
        }
    }
}
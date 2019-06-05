package com.example.wd_mvvm.ui.otp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.wd_mvvm.databinding.FragmentOtpVerificationBinding
import com.example.wd_mvvm.ui.news.NewsFragment
import com.example.wd_mvvm.utils.replaceWithAnimation

class OtpVerificationFragment : Fragment() {

    private lateinit var viewModel: OtpVerificationViewModel
    private val commandObserver = Observer<OtpVerificationViewModel.Command> {
        when (it) {
            is OtpVerificationViewModel.Command.NextScreen -> {
                navigateNextScreen()
            }
            is OtpVerificationViewModel.Command.BackScreen -> {
                navigatePreviousScreen()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        getViewModel()
        return FragmentOtpVerificationBinding.inflate(inflater, container, false).apply {
            this.viewModel = this@OtpVerificationFragment.viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    private fun getViewModel() {
        viewModel = ViewModelProviders.of(this).get(OtpVerificationViewModel::class.java)
        viewModel.apply {
            command.observe(this@OtpVerificationFragment, commandObserver)
        }
    }
    private fun navigateNextScreen() {
        activity?.let { act ->
            val fragment = NewsFragment()
            act replaceWithAnimation fragment
        }
    }

    private fun navigatePreviousScreen() {
        activity?.onBackPressed()
    }
}

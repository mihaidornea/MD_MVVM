package com.example.wd_mvvm.ui.phone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.wd_mvvm.databinding.FragmentVerifyPhoneNumberBinding
import com.example.wd_mvvm.ui.otp.OtpVerificationFragment
import com.example.wd_mvvm.utils.replaceWithAnimation

class PhoneVerificationFragment : Fragment() {

    private lateinit var viewModel: PhoneVerificationViewModel
    private val commandObserver = Observer<PhoneVerificationViewModel.Command> {
        when (it) {
            is PhoneVerificationViewModel.Command.NextScreen -> {
                navigateNextScreen()
            }
            is PhoneVerificationViewModel.Command.BackScreen -> {
                navigatePreviousScreen()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        getViewModel()
        return FragmentVerifyPhoneNumberBinding.inflate(inflater, container, false).apply {
            this.viewModel = this@PhoneVerificationFragment.viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    private fun getViewModel() {
        viewModel = ViewModelProviders.of(this).get(PhoneVerificationViewModel::class.java)
        viewModel.apply {
            command.observe(this@PhoneVerificationFragment, commandObserver)
        }
    }

    private fun navigateNextScreen() {
        activity?.let { act ->
            val fragment = OtpVerificationFragment()
            act replaceWithAnimation fragment
        }
    }

    private fun navigatePreviousScreen() {
        activity?.onBackPressed()
    }
}

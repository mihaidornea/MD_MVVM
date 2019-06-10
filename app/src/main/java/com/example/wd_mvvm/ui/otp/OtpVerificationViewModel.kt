package com.example.wd_mvvm.ui.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.example.wd_mvvm.utils.default

class OtpVerificationViewModel : ViewModel() {

    sealed class Command {
        class NextScreen(val name: String) : Command()
        class BackScreen(val name: String) : Command()
    }

    private var _command = MutableLiveData<Command>()
    val command: LiveData<Command> get() = _command

    var otp = MutableLiveData<String>().default("123")

    var callback = PinEntryEditText.OnPinEnteredListener {
        _command.value = Command.NextScreen("sal")
    }

}
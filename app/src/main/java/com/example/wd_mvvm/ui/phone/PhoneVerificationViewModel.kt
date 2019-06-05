package com.example.wd_mvvm.ui.phone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhoneVerificationViewModel : ViewModel() {

    sealed class Command {
        class NextScreen(val name: String) : Command()
        class BackScreen(val name: String) : Command()
    }

    private var _command = MutableLiveData<Command>()
    val command: LiveData<Command> get() = _command

    var phoneNumber = ""

    fun onNextPressed() {
        _command.value = Command.NextScreen("nextScreen")
    }

    fun onBackPressed(){
        _command.value = Command.BackScreen("backSreen")
    }
}
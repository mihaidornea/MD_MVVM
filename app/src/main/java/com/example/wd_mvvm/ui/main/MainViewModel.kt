package com.example.wd_mvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wd_mvvm.utils.default

class MainViewModel : ViewModel() {

    sealed class Command {
        class NextScreen(val name: String) : Command()
    }

    private var _command = MutableLiveData<Command>()
    val command: LiveData<Command> get() = _command

    var username = ""
    var password = ""

    var title = MutableLiveData<String>().default("Welcome back")

    fun loginClicked() {
        if (username.isNotBlank() && password.isNotBlank()) {
            _command.value = Command.NextScreen("Anditsa")
        }

    }

    fun signUpClicked() {
    }

}
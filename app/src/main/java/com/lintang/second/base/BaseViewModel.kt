package com.lintang.second.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lintang.second.base.modular.util.SingleLiveEvent

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var eventShowProgress = SingleLiveEvent<Boolean>()
    var eventIsEmpty = SingleLiveEvent<Boolean>()

}
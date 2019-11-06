package com.lintang.second.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lintang.second.base.ui.BaseActivity

open class BaseAppActivity : BaseActivity() {

    fun <T : ViewModel> obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProvider(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

}
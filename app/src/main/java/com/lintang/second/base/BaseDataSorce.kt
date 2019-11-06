package com.lintang.second.base

interface BaseDataSorce {
    interface ResponseCallback<T> {
        fun onShowProgressDialog()
        fun onHideProgressDialog()
        fun onFinish()
        fun onEmpty()
        fun onSuccess(data: T)
        fun onFailed(statusCode: Int, errorMessage: String? = "")
    }
}
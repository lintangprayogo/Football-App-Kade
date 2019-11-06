package com.lintang.second.base.modular.source.remote

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class ApiCallback<M> : Observer<M> {

    abstract fun onSuccess(model: M)

    abstract fun onFailure(code: Int, errorMessage: String)

    abstract fun onFinish()

    override fun onComplete() {
        onFinish()
    }

    override fun onNext(t: M) {
        onSuccess(t)
    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is HttpException -> {
                val code = e.code()
                var msg = e.message()

                when (code) {
                    504 -> {
                        msg = "Error Response"
                    }
                    502, 404 -> {
                        msg = "Error Connect or Resource Not Found"
                    }
                    400 -> {
                        msg = "Bad Request"
                    }
                    401 -> {
                        msg = "Not Authorized"
                    }
                }

                onFailure(code, msg)
            }

            is IllegalStateException -> onFailure(-1, "Invalid Id")

            is UnknownHostException -> onFailure(
                -1,
                "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}"
            )
            is SocketTimeoutException -> onFailure(
                -1,
                "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}"
            )
            else -> onFailure(-1, e.message ?: "Unknown error occured")
        }

        onFinish()
    }
}

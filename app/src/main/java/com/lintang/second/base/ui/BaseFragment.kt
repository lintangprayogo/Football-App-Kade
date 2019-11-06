package com.lintang.second.base.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lintang.second.base.BaseHelper


abstract class BaseFragment : Fragment() {
    lateinit var mActivity: BaseActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = (activity as BaseActivity)
    }

    protected fun setupEventEmptyView(view: View, isEmpty: Boolean) {
        if (isEmpty) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    protected inline fun <reified ClassActivity, Model> baseStartActivity(
        context: Context,
        extraKey: String,
        data: Model
    ) {
        val intent = Intent(context, ClassActivity::class.java)
        val extraData = BaseHelper().baseToJson(data)
        intent.putExtra(extraKey, extraData)
        context.startActivity(intent)
    }

    protected fun setupEventProgressView(view: View, progress: Boolean) {
        if (progress) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }


}

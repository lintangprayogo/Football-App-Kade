package com.lintang.second.main.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lintang.second.R
import com.lintang.second.base.BaseAppActivity
import com.lintang.second.base.modular.model.match.SingleMatch
import com.lintang.second.main.adapter.MatchAdapter
import com.lintang.second.main.viewmodel.match.MatchSearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar_search.*

class SearchActivity : BaseAppActivity() {
    private lateinit var mViewModel: MatchSearchViewModel
    private lateinit var adapter: MatchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_search)
        setUpSearch()
        setUpViewModel()
        adapter = MatchAdapter()
    }

    fun setUpViewModel() {
        mViewModel = obtainMatchSearchViewModel().apply {
            eventIsEmpty.observe(this@SearchActivity, Observer {
                adapter.clearData()
                setupEventEmptyView(empty_search, it)
            })
            eventShowProgress.observe(this@SearchActivity, Observer {
                setupEventProgressView(load_search, it)
            })
            matchListLive.observe(this@SearchActivity, Observer {
                setUpRecylerView(it)

            })
        }
    }

    private fun setUpSearch() {
        search_edit.requestFocus()
        search_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val search = s.toString()
                adapter.clearData()
                setupEventEmptyView(empty_search, false)
                if (!s.isNullOrEmpty()) {
                    mViewModel.getSearchMatch(search)
                }
            }

        })
        back.setOnClickListener {
            finish()
        }
        close.setOnClickListener {
            search_edit.text.clear()
        }
    }


    private fun setUpRecylerView(data: List<SingleMatch>) {
        adapter.setRecyclerViewLayout(this, R.layout.match_item)
        adapter.setListData(data)
        adapter.setRecyclerViewListener {
            baseStartActivity<MatchActivity, SingleMatch>(this, "data", it)
        }
        list_rv_match_search.layoutManager = LinearLayoutManager(this)
        list_rv_match_search.adapter = adapter
    }

    fun obtainMatchSearchViewModel() = obtainViewModel(MatchSearchViewModel::class.java)
}

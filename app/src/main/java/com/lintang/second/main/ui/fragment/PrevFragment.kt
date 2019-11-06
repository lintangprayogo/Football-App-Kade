package com.lintang.second.main.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lintang.second.R
import com.lintang.second.base.modular.model.match.SingleMatch
import com.lintang.second.base.ui.BaseFragment
import com.lintang.second.main.adapter.LeagueAdapterItem
import com.lintang.second.main.adapter.MatchAdapter
import com.lintang.second.main.ui.activity.LeagueActivity
import com.lintang.second.main.ui.activity.MatchActivity
import com.lintang.second.main.viewmodel.match.MatchPrevViewModel
import kotlinx.android.synthetic.main.fragment_prev.*

/**
 * A simple [Fragment] subclass.
 */
@Suppress("NAME_SHADOWING")
class PrevFragment : BaseFragment() {
    lateinit var mViewModel: MatchPrevViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setUpViewModel()
        return inflater.inflate(R.layout.fragment_prev, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val league = mActivity.baseGetExtraData<LeagueAdapterItem>("data")
        val id = league.id
        mViewModel.getPrevMatch(id)
    }

    private fun setUpViewModel() {
        mViewModel = (activity as LeagueActivity).obtainPrevMatchViewModel().apply {

            eventIsEmpty.observe(this@PrevFragment, Observer {
                setupEventEmptyView(empty_prev, it)
            })
            eventShowProgress.observe(this@PrevFragment, Observer {
                setupEventProgressView(load_next, it)
            })
            matchPrevListLive.observe(this@PrevFragment, Observer {
                setUpRecylerView(it)
            })
        }
    }

    private fun setUpRecylerView(data: List<SingleMatch>) {
        val adapter = MatchAdapter()
        context?.let { adapter.setRecyclerViewLayout(it, R.layout.match_item) }
        adapter.setListData(data)
        adapter.setRecyclerViewListener { data ->
            context?.let { baseStartActivity<MatchActivity, SingleMatch>(it, "data", data) }
        }
        rv_match_prev.layoutManager = LinearLayoutManager(context)
        rv_match_prev.adapter = adapter
    }

}

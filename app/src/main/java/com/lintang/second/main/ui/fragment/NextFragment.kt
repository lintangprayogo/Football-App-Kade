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
import com.lintang.second.main.viewmodel.match.MatchViewModel
import kotlinx.android.synthetic.main.fragment_next.*

/**
 * A simple [Fragment] subclass.
 */
@Suppress("NAME_SHADOWING")
class NextFragment : BaseFragment() {
    private lateinit var mViewModel: MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setUpViewModel()
        return inflater.inflate(R.layout.fragment_next, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val league = mActivity.baseGetExtraData<LeagueAdapterItem>("data")
        val id = league.id
        mViewModel.getMatch(id)
    }

    private fun setUpViewModel() {

        mViewModel = (activity as LeagueActivity).obtainNextMatchViewModel().apply {
            eventIsEmpty.observe(this@NextFragment, Observer {
                setupEventEmptyView(empty_next, it)
            })
            eventShowProgress.observe(this@NextFragment, Observer {
                setupEventProgressView(load_next, it)

            })
            matchListLive.observe(this@NextFragment, Observer {
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
        rv_match_next.layoutManager = LinearLayoutManager(context)
        rv_match_next.adapter = adapter
    }

}

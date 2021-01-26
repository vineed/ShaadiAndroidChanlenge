package com.shaadi.shaadiandroidchallenge.partner_match.view

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.shaadi.shaadiandroidchallenge.partner_match.viewmodel.PartnerMatcherViewModel
import com.shaadi.shaadiandroidchallenge.R
import com.shaadi.shaadiandroidchallenge.core.base.view.BaseFragment
import com.shaadi.shaadiandroidchallenge.databinding.PartnerMatcherFragmentBinding
import com.shaadi.shaadiandroidchallenge.partner_match.adapter.PartnerAdapter
import com.shaadi.shaadiandroidchallenge.partner_match.model.UserMatch
import com.shaadi.shaadiandroidchallenge.partner_match.viewmodel.PartnerMatcherViewEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class PartnerMatcherFragment :
    BaseFragment<PartnerMatcherViewEvent, PartnerMatcherViewModel, PartnerMatcherFragmentBinding>(R.layout.partner_matcher_fragment) {

    override val viewModel: PartnerMatcherViewModel by viewModel()

    override fun getBinding(rootView: View) = PartnerMatcherFragmentBinding.bind(rootView)

    private val partnerAdapter: PartnerAdapter by lazy {
        PartnerAdapter(
            requireContext(),
            onActionListener = onActionListener
        )
    }

    private val onActionListener =
        { userMatch: UserMatch, isAccepted: Boolean ->
            viewModel.updateUserAcceptStatus(userMatch.apply { this.isAccepted = isAccepted })
        }

    override fun onEvent(event: PartnerMatcherViewEvent) {
        when (event) {
            is PartnerMatcherViewEvent.MatchItemChange -> {
                partnerAdapter.notifyItemChanged(
                    event.pos,
                    event.userMatch.apply { animateIsAccepted = true })
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBindings()
        setUpListeners()
        setUpObservers()
    }

    private fun setUpBindings() {
        binding?.let { mBinding ->
            mBinding.rvUserMatch.layoutManager = LinearLayoutManager(requireContext())
            mBinding.rvUserMatch.adapter = partnerAdapter
        }
    }

    private fun setUpListeners() {
        binding?.let { mBinding ->
            mBinding.srlUserMatch.setOnRefreshListener {
                mBinding.srlUserMatch.isRefreshing = false
                viewModel.retrieveAllMatchUsers(false)
            }
        }
    }

    private fun setUpObservers() {
        viewModel.userMatchListLiveData.observe(viewLifecycleOwner) { userMatchList ->
            partnerAdapter.setItems(userMatchList)
        }

        viewModel.isApiFetching.observe(viewLifecycleOwner) { isApiFetching ->
            animateLoader(isApiFetching)
        }
    }

    private fun animateLoader(show: Boolean) {
        binding?.tvApiFetchLoader?.isVisible = show

        val slideDownAnimation = AnimationUtils.loadAnimation(
            context,
            if (show) R.anim.slide_down else R.anim.slide_up
        )
            .apply {
                duration = 300
                interpolator = FastOutSlowInInterpolator()
            }

        binding?.tvApiFetchLoader?.animation = slideDownAnimation

        slideDownAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding?.tvApiFetchLoader?.isVisible = show
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        slideDownAnimation.start()

    }
}
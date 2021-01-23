package com.shaadi.shaadiandroidchallenge.partner_match.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.shaadi.shaadiandroidchallenge.partner_match.viewmodel.PartnerMatcherViewModel
import com.shaadi.shaadiandroidchallenge.R
import com.shaadi.shaadiandroidchallenge.core.base.view.BaseFragment
import com.shaadi.shaadiandroidchallenge.databinding.PartnerMatcherFragmentBinding
import com.shaadi.shaadiandroidchallenge.partner_match.viewmodel.PartnerMatcherViewEvent

class PartnerMatcherFragment :
    BaseFragment<PartnerMatcherViewEvent, PartnerMatcherViewModel, PartnerMatcherFragmentBinding>(R.layout.partner_matcher_fragment) {

    override val viewModel: PartnerMatcherViewModel by viewModels()

    override fun getBinding(rootView: View) = PartnerMatcherFragmentBinding.bind(rootView)

    override fun onEvent(event: PartnerMatcherViewEvent) {
        when (event) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let { mBinding ->
            mBinding.btnTest.setOnClickListener {

            }
        }
    }

}
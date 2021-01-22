package com.shaadi.shaadiandroidchallenge.partner_match.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.shaadi.shaadiandroidchallenge.partner_match.viewmodel.PartnerMatcherViewModel
import com.shaadi.shaadiandroidchallenge.R

class PartnerMatcherFragment : Fragment(R.layout.partner_matcher_fragment) {

    private val viewModel by viewModels<PartnerMatcherViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
package com.shaadi.shaadiandroidchallenge

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PartnerMatcherFragment : Fragment() {

    companion object {
        fun newInstance() = PartnerMatcherFragment()
    }

    private lateinit var viewModel: PartnerMatcherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.partner_matcher_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PartnerMatcherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
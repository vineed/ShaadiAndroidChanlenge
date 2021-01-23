package com.shaadi.shaadiandroidchallenge.partner_match.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shaadi.shaadiandroidchallenge.partner_match.viewmodel.PartnerMatcherViewModel
import com.shaadi.shaadiandroidchallenge.R
import com.shaadi.shaadiandroidchallenge.core.base.BaseFragment
import com.shaadi.shaadiandroidchallenge.databinding.PartnerMatcherFragmentBinding
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.CompletableFuture

class PartnerMatcherFragment : BaseFragment(R.layout.partner_matcher_fragment) {

    private val viewModel by viewModels<PartnerMatcherViewModel>()
    private var binding: PartnerMatcherFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = PartnerMatcherFragmentBinding.bind(view)

        binding?.let { mBinding ->
            mBinding.btnTest.setOnClickListener {
                Toast.makeText(requireContext(), "Test", Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun onDestroyView() {
        binding = null

        super.onDestroyView()
    }
}
package com.shaadi.shaadiandroidchallenge.core.base.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.shaadi.shaadiandroidchallenge.core.base.viewmodel.BaseViewModel
import com.shaadi.shaadiandroidchallenge.databinding.ActivityMainBinding
import com.shaadi.shaadiandroidchallenge.host.view.IHostView
import com.shaadi.shaadiandroidchallenge.host.viewmodel.MainViewModel

abstract class BaseFragment<EV, VM : BaseViewModel<EV>, T : ViewBinding>(@LayoutRes layoutId: Int) :
    Fragment(layoutId) {

    protected val hostViewModel: MainViewModel by viewModels()
    protected abstract val viewModel: VM
    protected var binding: T? = null
    protected lateinit var hostBinding: ActivityMainBinding

    protected abstract fun getBinding(rootView: View): T
    protected abstract fun onEvent(event: EV)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getBinding(view)

        setupBaseObservers()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val context = requireActivity()

        if (context is IHostView) {
            hostBinding = context.hostBinding
        }
    }

    /*override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is IHostView) {
            hostBinding = context.hostBinding
        }
    }*/

    private fun setupBaseObservers() {
        viewModel.toastLiveData.observe(this) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }

        viewModel.doLoading.observe(viewLifecycleOwner) { progressModel ->
            hostBinding.loader.let { loader ->
                loader.llProgress.isVisible = progressModel.doLoading
                loader.tvProgressMessage.text = progressModel.message
                loader
            }
        }

        viewModel.viewEventLiveData.observe(this) { event ->
            onEvent(event)
        }
    }

    override fun onDestroyView() {
        binding = null

        super.onDestroyView()
    }
}
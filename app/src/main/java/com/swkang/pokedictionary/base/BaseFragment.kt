package com.swkang.pokedictionary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.swkang.model.BR
import com.swkang.model.base.BaseViewModel

abstract class BaseFragment : Fragment() {
    private lateinit var binding: ViewDataBinding
    private lateinit var viewModel: BaseViewModel

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun createViewModel(): BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = createViewModel()

        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )
        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        if (::viewModel.isInitialized) {
            viewModel.dispose()
        }
        super.onDestroyView()
    }

}
package com.polotechnologies.heroes.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.databinding.FragmentHomeBinding
import com.polotechnologies.heroes.viewModels.HomeViewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mViewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        mBinding.lifecycleOwner = this

        mViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        mBinding.viewModel = mViewModel


        return mBinding.root
    }


}

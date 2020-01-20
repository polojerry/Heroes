package com.polotechnologies.heroes.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.databinding.FragmentFavouriteBinding
import com.polotechnologies.heroes.viewModels.FavouriteViewModel

/**
 * A simple [Fragment] subclass.
 */
class FavouriteFragment : Fragment() {

    private lateinit var mBinding: FragmentFavouriteBinding
    private lateinit var mViewModel : FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_favourite, container, false)
        mBinding.lifecycleOwner = this

        mViewModel = ViewModelProviders.of(this).get(FavouriteViewModel::class.java)
        mBinding.viewModel = mViewModel


        return mBinding.root
    }


}

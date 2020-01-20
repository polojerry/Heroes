package com.polotechnologies.heroes.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.databinding.FragmentFavouriteBinding

/**
 * A simple [Fragment] subclass.
 */
class FavouriteFragment : Fragment() {

    private lateinit var mBinding: FragmentFavouriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_favourite, container, false)


        return mBinding.root
    }


}

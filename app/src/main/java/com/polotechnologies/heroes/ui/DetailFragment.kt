package com.polotechnologies.heroes.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get

import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.databinding.FragmentDetailBinding
import com.polotechnologies.heroes.viewModels.DetailViewModel
import com.polotechnologies.heroes.viewModels.DetailViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    lateinit var mBinding: FragmentDetailBinding
    lateinit var mViewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        mBinding.lifecycleOwner = this

        val application = activity!!.application
        val superHero = DetailFragmentArgs.fromBundle(arguments!!).selectedHero
        val viewModelFactory  = DetailViewModelFactory(superHero, application)

        mBinding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
        mBinding.ctbDetails.title = superHero.name


        return mBinding.root
    }


}

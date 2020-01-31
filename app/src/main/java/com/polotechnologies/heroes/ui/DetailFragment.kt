package com.polotechnologies.heroes.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.findNavController

import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.databinding.FragmentDetailBinding
import com.polotechnologies.heroes.viewModels.DetailViewModel
import com.polotechnologies.heroes.viewModels.DetailViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    lateinit var mBinding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        mBinding.lifecycleOwner = this

        inflateMenu()

        val application = activity!!.application
        val superHero = DetailFragmentArgs.fromBundle(arguments!!).hero
        val viewModelFactory  = DetailViewModelFactory(superHero, application)

        mBinding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
        mBinding.ctbDetails.title = superHero.name

        mBinding.tbDetails.setNavigationOnClickListener{
            activity!!.onBackPressed()
        }

        return mBinding.root
    }

    private fun inflateMenu() {
        mBinding.tbDetails.setOnMenuItemClickListener(this@DetailFragment)

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_add_favourite -> Toast.makeText(context, "Added",Toast.LENGTH_SHORT).show()

            else-> return false

        }
        return true

    }


}

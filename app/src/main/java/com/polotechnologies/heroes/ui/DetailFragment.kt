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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.database.HeroesDatabase
import com.polotechnologies.heroes.databinding.FragmentDetailBinding
import com.polotechnologies.heroes.viewModels.DetailViewModel
import com.polotechnologies.heroes.viewModelFactory.DetailViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    lateinit var mBinding: FragmentDetailBinding
    lateinit var mViewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        mBinding.lifecycleOwner = this

        inflateMenu()

        val application = activity!!.application
        val superHero = DetailFragmentArgs.fromBundle(arguments!!).hero
        val database = HeroesDatabase.getInstance(application).daoFavouriteHero
        val viewModelFactory  = DetailViewModelFactory(superHero, application, database)

        mBinding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        mBinding.ctbDetails.title = superHero.name

        mBinding.tbDetails.setNavigationOnClickListener{
            activity!!.onBackPressed()
        }

        mViewModel.statusAddToFavourite.observe(viewLifecycleOwner, Observer {
            when(it){
                true -> Toast.makeText(context, "${mViewModel.selectedHero.value!!.name} Added to Favourites", Toast.LENGTH_SHORT).show()
                else-> Toast.makeText(context, "Failed to Add to Favourites", Toast.LENGTH_SHORT).show()
            }
        })

        return mBinding.root
    }

    private fun inflateMenu() {
        mBinding.tbDetails.setOnMenuItemClickListener(this@DetailFragment)

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_add_favourite -> mBinding.viewModel!!.addToFavourites()

            else-> return false

        }
        return true

    }


}

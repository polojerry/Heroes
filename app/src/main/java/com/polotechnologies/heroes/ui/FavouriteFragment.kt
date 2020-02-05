package com.polotechnologies.heroes.ui


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.adapters.FavouriteHerosRecyclerAdapter
import com.polotechnologies.heroes.dataModels.Hero
import com.polotechnologies.heroes.database.HeroesDatabase
import com.polotechnologies.heroes.database.favouriteHero.FavouriteHero
import com.polotechnologies.heroes.databinding.FragmentFavouriteBinding
import com.polotechnologies.heroes.uiHosts.HomeFragmentDirections
import com.polotechnologies.heroes.viewModelFactory.FavouritesViewModelFactory
import com.polotechnologies.heroes.viewModels.FavouriteViewModel

/**
 * A simple [Fragment] subclass.
 */
class FavouriteFragment : Fragment(), SearchView.OnQueryTextListener,
    Toolbar.OnMenuItemClickListener {

    private lateinit var mBinding: FragmentFavouriteBinding
    private lateinit var mViewModel : FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_favourite, container, false)
        mBinding.lifecycleOwner = this
        mBinding.tbMain.setOnMenuItemClickListener(this)
        inflateSearchMenu()

        val application = activity!!.application
        val database = HeroesDatabase.getInstance(application).daoFavouriteHero
        val mFavouriteViewModelFactory = FavouritesViewModelFactory(application, database)

        mViewModel = ViewModelProvider(this, mFavouriteViewModelFactory).get(FavouriteViewModel::class.java)
        mBinding.viewModel = mViewModel

        val adapter = FavouriteHerosRecyclerAdapter(FavouriteHerosRecyclerAdapter.OnClickListener{
            mViewModel.displaySelectedHero(it)
        })

        mBinding.rvFavouriteHero.adapter = adapter

        mViewModel.favouriteHero.observe(viewLifecycleOwner, Observer {
            it?.let {
                    adapter.submitList(it)
            }
        })

        mViewModel.selectedHero.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                val hero  = favouriteHeroToHero(it)
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(hero)
                activity!!.findNavController(R.id.nav_host_main).navigate(action)
                mViewModel.displaySelectedHeroComplete()
            }
        })

        mViewModel.deletedHeroes.observe(viewLifecycleOwner, Observer{
            when(it){
                true -> Toast.makeText(context, "Favourite heroes Cleared", Toast.LENGTH_SHORT).show()
                else-> Toast.makeText(context, "Nothing to clear here!!!", Toast.LENGTH_SHORT).show()
            }
        })

        return mBinding.root
    }

    private fun favouriteHeroToHero(favouriteHero: FavouriteHero?): Hero {
        return Hero(
            favouriteHero!!.name!!,
            favouriteHero.powerstats!!,
            favouriteHero.biography!!,
            favouriteHero.appearance!!,
            favouriteHero.work!!,
            favouriteHero.connections!!,
            favouriteHero.image!!
        )
    }

    private fun inflateSearchMenu() {
        val toolbar = mBinding.tbMain
        val searchManager = context!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = toolbar.menu.findItem(R.id.action_search).actionView as SearchView

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
            setOnQueryTextListener(this@FavouriteFragment)
            setIconifiedByDefault(true)
            isSubmitButtonEnabled = false
            isIconified = false
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        mViewModel.searchHero(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.action_clear -> mViewModel.clearFavourites()
        }
        return true
    }

}

package com.polotechnologies.heroes.ui


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.adapters.HeroRecyclerAdapter
import com.polotechnologies.heroes.databinding.FragmentHeroesBinding
import com.polotechnologies.heroes.uiHosts.HomeFragmentDirections
import com.polotechnologies.heroes.viewModels.HeroesViewModel

/**
 * A simple [Fragment] subclass.
 */
class HeroesFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var mBinding: FragmentHeroesBinding
    private lateinit var mViewModel : HeroesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_heroes, container, false)
        mBinding.lifecycleOwner = this
        inflateSearchMenu()

        mViewModel = ViewModelProviders.of(this).get(HeroesViewModel::class.java)
        mBinding.viewModel = mViewModel


        val adapter = HeroRecyclerAdapter(HeroRecyclerAdapter.OnClickListener{
            mViewModel.displaySelectedHero(it)
        })

        mBinding.rvHero.adapter = adapter

        mViewModel.heroesData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        mViewModel.selectedHero.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                activity!!.findNavController(R.id.nav_host_main).navigate(action)
                mViewModel.displaySelectedHeroComplete()
            }
        })

        return mBinding.root
    }

    private fun inflateSearchMenu() {
        val toolbar = mBinding.tbMain
        val searchManager = context!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = toolbar.menu.findItem(R.id.action_search).actionView as SearchView

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
            setOnQueryTextListener(this@HeroesFragment)
            setIconifiedByDefault(true)
            isSubmitButtonEnabled = false
            isIconified = false
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}

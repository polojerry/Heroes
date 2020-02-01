package com.polotechnologies.heroes.ui


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.adapters.HeroRecyclerAdapter
import com.polotechnologies.heroes.databinding.FragmentHeroesBinding
import com.polotechnologies.heroes.network.HeroesApi
import com.polotechnologies.heroes.network.HeroesApiService
import com.polotechnologies.heroes.uiHosts.HomeFragmentDirections
import com.polotechnologies.heroes.viewModels.HeroesViewModel
import com.polotechnologies.heroes.viewModelFactory.HeroesViewModelFactory
import com.polotechnologies.heroes.viewModels.HeroApiStatus

/**
 * A simple [Fragment] subclass.
 */
class HeroesFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var mBinding: FragmentHeroesBinding
    private lateinit var mViewModel : HeroesViewModel
    private lateinit var mHeroesViewModelFactory: HeroesViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        mBinding = FragmentHeroesBinding.inflate(inflater)
        mBinding.lifecycleOwner = this
        inflateSearchMenu()

        mHeroesViewModelFactory =
            HeroesViewModelFactory(
                "man",
                activity!!.application
            )
        mViewModel = ViewModelProviders.of(this, mHeroesViewModelFactory).get(HeroesViewModel::class.java)
        mBinding.viewModel = mViewModel

        val adapter = HeroRecyclerAdapter(HeroRecyclerAdapter.OnClickListener{
            mViewModel.displaySelectedHero(it)
        })

        mBinding.rvHero.adapter = adapter
        mBinding.swipeRefreshHeroes.setOnRefreshListener {
            mViewModel.fetchHeroes("man")
            mViewModel.heroStatus.observe(viewLifecycleOwner, Observer {
                when(it){
                    HeroApiStatus.DONE ->{
                        mBinding.imgLoadingStatus.visibility = View.INVISIBLE
                        mBinding.swipeRefreshHeroes.isRefreshing = false
                    }
                    HeroApiStatus.ERROR->{
                        mBinding.imgLoadingStatus.visibility = View.VISIBLE
                        mBinding.imgLoadingStatus.setImageResource(R.drawable.ic_connection_error)
                        mBinding.swipeRefreshHeroes.isRefreshing = false
                    }
                    HeroApiStatus.LOADING->{
                        mBinding.imgLoadingStatus.visibility = View.INVISIBLE
                    }
                }
            })
        }

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
        mViewModel.fetchHeroes(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }


}

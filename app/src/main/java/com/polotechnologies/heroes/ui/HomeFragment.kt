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
import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.adapters.HeroRecyclerAdapter
import com.polotechnologies.heroes.databinding.FragmentHomeBinding
import com.polotechnologies.heroes.viewModels.HomeViewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mViewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        mBinding.lifecycleOwner = this
        inflateSearchMenu()

        mViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        mBinding.viewModel = mViewModel


        val adapter = HeroRecyclerAdapter()
        mBinding.rvHero.adapter = adapter

        mViewModel.heroesData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return mBinding.root
    }

    private fun inflateSearchMenu() {
        val toolbar = mBinding.tbMain
        val searchManager = context!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchWidget = toolbar.menu.findItem(R.id.action_search).actionView as SearchView

        searchWidget.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
            setOnQueryTextListener(this@HomeFragment)
            setIconifiedByDefault(false)
            isSubmitButtonEnabled = false
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}

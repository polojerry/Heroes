package com.polotechnologies.heroes.uiHosts


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController

import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var mBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        setUpBottomNav()
        return mBinding.root
    }

    private fun setUpBottomNav() {
        val navController = childFragmentManager.findFragmentById(R.id.nav_host_home)
        mBinding.bottomNavHome.setupWithNavController(navController = navController!!.findNavController())
    }


}

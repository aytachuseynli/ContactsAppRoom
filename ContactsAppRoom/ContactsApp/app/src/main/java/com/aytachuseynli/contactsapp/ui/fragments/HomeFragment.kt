package com.aytachuseynli.contactsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.aytachuseynli.contactsapp.R
import com.aytachuseynli.contactsapp.data.entity.Persons
import com.aytachuseynli.contactsapp.databinding.FragmentHomeBinding
import com.aytachuseynli.contactsapp.ui.adapter.PersonAdapter
import com.aytachuseynli.contactsapp.ui.util.go
import com.aytachuseynli.contactsapp.ui.viewmodel.DetailViewModel
import com.aytachuseynli.contactsapp.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
        private lateinit var binding: FragmentHomeBinding
        private lateinit var viewModel: HomeViewModel
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.homeFragment = this
        binding.toolbarHomeTitle = "Contacts"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarHome)

            viewModel.personsList.observe(viewLifecycleOwner){
                val adapter = PersonAdapter(requireContext(),it,viewModel)
                binding.adapter = adapter

            }

            requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                                      menuInflater.inflate(R.menu.toolbar_menu,menu)

                                val item = menu.findItem(R.id.action_search)
                                val searchView = item.actionView as SearchView
                                searchView.setOnQueryTextListener(this@HomeFragment)
                        }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                                return false
                        }

            },viewLifecycleOwner,Lifecycle.State.RESUMED)

        return binding.root
    }

        fun fabClick(it:View){
            Navigation.go(it,R.id.toSave)

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomeViewModel by viewModels()
        viewModel = tempViewModel
    }


        override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return true
        }



    override fun onResume() {
        super.onResume()
        viewModel.loadPersons()
        //Return to HomeFragment and reload persons
    }

}

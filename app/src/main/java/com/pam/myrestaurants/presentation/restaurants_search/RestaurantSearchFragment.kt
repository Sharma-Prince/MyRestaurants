package com.pam.myrestaurants.presentation.restaurants_search



import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.ancestors
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pam.myrestaurants.databinding.FragmentRestaurantSearchBinding
import com.pam.myrestaurants.domain.model.DRestaurant
import com.pam.myrestaurants.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class RestaurantSearchFragment : Fragment() {

    private var _binding: FragmentRestaurantSearchBinding? = null
    private val binding: FragmentRestaurantSearchBinding
        get() = _binding!!

    private val searchAdapter = RestaurantAdapter()
    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRestaurantSearchBinding.inflate(inflater, container, false)
        return _binding?.root
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.restaurantsSearchRecycler.apply {
            adapter = searchAdapter
        }
        binding.restaurantsSearchRecycler.layoutManager = LinearLayoutManager(context)

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.restaurantSearchList.collect{ it ->
                if(it.isLoading){
                    binding.nothingFound.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                if(it.error.isNotBlank()){
                    binding.nothingFound.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let {

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressBar.visibility = View.GONE
                    searchAdapter.setContentList(it as ArrayList<DRestaurant>)
                }
            }
        }

        viewModel.getRestaurantList()

        binding.restaurantsSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(keyWord: String): Boolean {
                return false
            }
            override fun onQueryTextChange(keyWord: String): Boolean {
                searchAdapter.filter.filter(keyWord)
                return false
            }
        })

        binding.restaurantsSearchRecycler.setOnTouchListener(OnTouchListener { v, event ->
            hideKeyboard()
            true
        })
    }
}
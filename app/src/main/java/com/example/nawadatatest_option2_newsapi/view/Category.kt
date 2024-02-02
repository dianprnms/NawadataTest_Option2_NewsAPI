package com.example.nawadatatest_option2_newsapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nawadatatest_option2_newsapi.R
import com.example.nawadatatest_option2_newsapi.adapter.CategoryAdapter
import com.example.nawadatatest_option2_newsapi.databinding.FragmentCategoryBinding
import com.example.nawadatatest_option2_newsapi.model.CategoryData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Category : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryAdapter: CategoryAdapter

    // NavController
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        // Inisialisasi NavController
        navController = findNavController()

        val listCategory = arrayListOf(
            CategoryData("BUSINESS"),
            CategoryData("ENTERTAINMENT"),
            CategoryData("GENERAL"),
            CategoryData("HEALTH"),
            CategoryData("SCIENCE"),
            CategoryData("SPORTS"),
            CategoryData("TECHNOLOGY")
        )

        categoryAdapter = CategoryAdapter(listCategory)
        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = categoryAdapter
        }

        categoryAdapter.onClick = { category ->
            val categ = category.name

            // Navigasi ke SourceFragment menggunakan NavController
            val bundle = Bundle().apply {
                putString("name", categ)
            }
            navController.navigate(R.id.action_category_to_source, bundle)
        }

        return binding.root
    }
}

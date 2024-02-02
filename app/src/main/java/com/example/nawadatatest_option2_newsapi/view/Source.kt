package com.example.nawadatatest_option2_newsapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nawadatatest_option2_newsapi.R
import com.example.nawadatatest_option2_newsapi.adapter.SourceAdapter
import com.example.nawadatatest_option2_newsapi.databinding.FragmentSourceBinding
import com.example.nawadatatest_option2_newsapi.model.Source
import com.example.nawadatatest_option2_newsapi.viewmodel.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Source : Fragment(), SourceAdapter.SourceClickListener {

    private lateinit var binding: FragmentSourceBinding
    private lateinit var sourceAdapter: SourceAdapter
    private lateinit var sourceVm: SourceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSourceBinding.inflate(inflater, container, false)

        sourceVm = ViewModelProvider(this)[SourceViewModel::class.java]
        sourceAdapter = SourceAdapter(ArrayList(), this)

        sourceVm.getDataSource().observe(viewLifecycleOwner) { sources ->
            sourceAdapter.setDataSource(sources ?: emptyList())
        }

        val datacat = arguments?.getString("name")

        if (datacat != null) {
            sourceVm.callApiSource(datacat)
        }

        val layoutMan = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvSource.layoutManager = layoutMan
        binding.rvSource.adapter = sourceAdapter

        return binding.root
    }

    override fun onSourceClick(source: Source) {
        openWebView(source.url)
    }

    private fun openWebView(url: String) {
        WebviewFragment.newInstance(url)
        val navController = findNavController()
        navController.navigate(R.id.action_source_to_webviewFragment, bundleOf("url" to url))
    }

}

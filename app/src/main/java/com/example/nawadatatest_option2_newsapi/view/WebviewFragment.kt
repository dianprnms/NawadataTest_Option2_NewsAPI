package com.example.nawadatatest_option2_newsapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nawadatatest_option2_newsapi.databinding.FragmentWebviewBinding

class WebviewFragment : Fragment() {
    private lateinit var binding: FragmentWebviewBinding

    companion object {
        private const val URL_KEY = "url"

        fun newInstance(url: String): WebviewFragment {
            val fragment = WebviewFragment()
            val args = Bundle()
            args.putString(URL_KEY, url)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString(URL_KEY)
        if (url != null) {
            binding.webView.loadUrl(url)
        }
    }
}

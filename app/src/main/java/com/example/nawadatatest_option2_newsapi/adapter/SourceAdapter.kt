package com.example.nawadatatest_option2_newsapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nawadatatest_option2_newsapi.databinding.ItemSourceBinding
import com.example.nawadatatest_option2_newsapi.model.Source

class SourceAdapter(
    private var listSource: List<Source>,
    private var sourceClickListener: SourceClickListener? = null
) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    interface SourceClickListener {
        fun onSourceClick(source: Source)
    }

    class ViewHolder(val binding: ItemSourceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source = listSource[position]
        holder.binding.nameSource.text = source.name
        holder.binding.cvSource.setOnClickListener {
            sourceClickListener?.onSourceClick(source)
        }
    }

    override fun getItemCount(): Int {
        return listSource.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataSource(data: List<Source>) {
        listSource = data
        notifyDataSetChanged()
    }
}

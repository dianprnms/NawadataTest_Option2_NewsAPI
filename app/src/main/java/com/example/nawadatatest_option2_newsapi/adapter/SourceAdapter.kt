package com.example.nawadatatest_option2_newsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nawadatatest_option2_newsapi.databinding.ItemCategoryBinding
import com.example.nawadatatest_option2_newsapi.databinding.ItemSourceBinding
import com.example.nawadatatest_option2_newsapi.model.Source

class SourceAdapter(private var listSource: List<Source>) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    private var onClick: ((Source) -> Unit)? = null

    class ViewHolder(val binding: ItemSourceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source = listSource[position]
        if (source != null) {
            holder.binding.nameSource.text = source.name
            holder.binding.itemSource.setOnClickListener {
                onClick?.invoke(listSource[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return listSource.size
    }

    // Fungsi ini akan memperbarui data pada adapter dan memberitahu bahwa data telah berubah
    fun setDataSource(data: List<Source>) {
        listSource = data
        notifyDataSetChanged()
    }

    // Fungsi ini digunakan untuk menetapkan onClickListener dari luar adapter
    fun setOnClickListener(listener: (Source) -> Unit) {
        onClick = listener
    }
}
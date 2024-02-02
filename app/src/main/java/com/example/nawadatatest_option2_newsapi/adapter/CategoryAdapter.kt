package com.example.nawadatatest_option2_newsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nawadatatest_option2_newsapi.databinding.ItemCategoryBinding
import com.example.nawadatatest_option2_newsapi.model.CategoryData

class CategoryAdapter(private var listCategory: List<CategoryData>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var onClick : ((CategoryData)->Unit)? = null

    class ViewHolder (var binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.categoryName.text = listCategory[position].name
        holder.binding.itemCategory.setOnClickListener{
            onClick!!.invoke(listCategory[position])
        }
    }
}
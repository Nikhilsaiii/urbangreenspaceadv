package com.example.urbangreenspace

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.urbangreenspace.databinding.ItemGreenSpaceBinding

class GreenSpaceAdapter(
    private var items: List<GreenSpace>,
    private val onItemClick: (GreenSpace) -> Unit
) : RecyclerView.Adapter<GreenSpaceAdapter.GreenSpaceViewHolder>() {

    inner class GreenSpaceViewHolder(private val binding: ItemGreenSpaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(greenSpace: GreenSpace) {
            binding.nameText.text = greenSpace.name
            binding.descriptionText.text = greenSpace.description
            binding.ratingBar.rating = greenSpace.rating
            binding.root.setOnClickListener {
                onItemClick(greenSpace)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GreenSpaceViewHolder {
        val binding = ItemGreenSpaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GreenSpaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GreenSpaceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newItems: List<GreenSpace>) {
        items = newItems
        notifyDataSetChanged()
    }
}

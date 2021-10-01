package br.com.interbootcamp.businesscard.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.interbootcamp.businesscard.data.local.room.entity.BusinessCard
import br.com.interbootcamp.businesscard.databinding.ItemBusinessCardBinding


class BusinessCardAdapter : ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemBusinessCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BusinessCard){
            binding.tvNameCard.text = item.name
            binding.tvPhoneCard.text = item.phone
            binding.tvEmailCard.text = item.email
            binding.tvCompanyNameCard.text = item.company
            binding.cvContent.setCardBackgroundColor(Color.parseColor(item.backgroundColor))

            binding.cvContent.setOnClickListener {

            }
        }
    }

}

class DiffCallBack: DiffUtil.ItemCallback<BusinessCard>(){
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem.id == newItem.id
    }

}
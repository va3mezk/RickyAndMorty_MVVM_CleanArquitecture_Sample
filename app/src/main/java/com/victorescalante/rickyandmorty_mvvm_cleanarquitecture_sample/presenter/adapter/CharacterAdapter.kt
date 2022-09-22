package com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.R
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.databinding.ItemCardBinding

import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.domain.model.CharacterModel

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean =
            oldItem == newItem
    }

    private val differ = AsyncListDiffer(this,differCallback)

    fun submitList(list:List<CharacterModel>)= differ.submitList(list)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        ItemCardBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),parent,false
        )
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            tvName.text = item.name
            tvType.text = "${item.status} - ${item.species}"
            ivCharacter.load(item.image)
            {
                placeholder(R.drawable.ic_launcher_foreground)
                crossfade(1000)
                build()
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}
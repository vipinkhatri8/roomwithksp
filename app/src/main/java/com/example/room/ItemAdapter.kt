package com.yesitlab.room

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yesitlab.room.databinding.TextBinding
import org.w3c.dom.Text

class ItemAdapter(var context: Context, var list : List<UserData>, var listener: OnItemClickListener):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(var binding: TextBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = TextBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return   ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = list[position]
        holder.binding.textAge.text = currentItem.age
        holder.binding.textName.text = currentItem.name
        holder.binding.imageDelete.setOnClickListener {
            listener.clickEvent(position,"Delete")
        }
        holder.binding.imageEdit.setOnClickListener{
            listener.clickEvent(position,"Edit")
        }
    }

    fun updateData(list : List<UserData>){
        this.list = list
        notifyDataSetChanged()

    }


}
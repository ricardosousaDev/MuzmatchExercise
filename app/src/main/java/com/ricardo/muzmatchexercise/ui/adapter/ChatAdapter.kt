package com.ricardo.muzmatchexercise.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ricardo.muzmatchexercise.BR
import com.ricardo.muzmatchexercise.R
import com.ricardo.muzmatchexercise.data.local.ChatEntity
import com.ricardo.muzmatchexercise.databinding.ListItemChatMessageBinding

class ChatAdapter(
    private val items: List<ChatEntity>
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding: ListItemChatMessageBinding =
            DataBindingUtil.inflate(inflater, R.layout.list_item_chat_message, parent, false)
        return ChatViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ChatViewHolder(private val binding: ListItemChatMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChatEntity) {
            binding.setVariable(BR.chatMessage, data)
        }
    }
}
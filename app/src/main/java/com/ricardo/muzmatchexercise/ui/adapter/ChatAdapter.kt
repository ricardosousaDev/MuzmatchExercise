package com.ricardo.muzmatchexercise.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ricardo.muzmatchexercise.BR
import com.ricardo.muzmatchexercise.R
import com.ricardo.muzmatchexercise.data.local.ChatEntity
import com.ricardo.muzmatchexercise.databinding.ListItemChatMessageBinding
import com.ricardo.muzmatchexercise.util.areMoreThanAnHourApart
import javax.inject.Inject
import androidx.constraintlayout.widget.ConstraintLayout




class ChatAdapter(
    private var items: List<ChatEntity>
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    @Inject
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding: ListItemChatMessageBinding =
            DataBindingUtil.inflate(inflater, R.layout.list_item_chat_message, parent, false)
        return ChatViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(newItems: List<ChatEntity>) {
        items = newItems
        // Assuming we are only adding one message at a time
        notifyItemInserted(items.size)
    }

    inner class ChatViewHolder(private val binding: ListItemChatMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChatEntity, itemPosition: Int) {
            binding.setVariable(BR.chatMessage, data)

            // Item sectioning
            if (itemPosition == 0 || itemPosition - 1 > 0 &&
                data.timestamp.areMoreThanAnHourApart(items[itemPosition - 1].timestamp)
            ) {
                binding.setVariable(BR.displayTime, true)
            } else {
                binding.setVariable(BR.displayTime, false)
            }

            // Chat bubble criteria
            if (data.isReply) {
                if (shouldDisplayTail(itemPosition, data)) {
                    binding.sentBubbleView.setBackgroundResource(R.drawable.tailed_right_bubble)
                } else {
                    binding.sentBubbleView.setBackgroundResource(R.drawable.right_bubble)
                }
            } else {
                if (shouldDisplayTail(itemPosition, data)) {
                    binding.receivedBubbleView.setBackgroundResource(R.drawable.tailed_left_bubble)
                } else {
                    binding.receivedBubbleView.setBackgroundResource(R.drawable.left_bubble)
                }
            }
        }
    }

    private fun shouldDisplayTail(itemPosition: Int, data: ChatEntity): Boolean {
        return if (itemPosition == items.size - 1) {
            // Is last item
            true
        } else {
            ((items[itemPosition + 1].isReply != data.isReply)
                    || items[itemPosition + 1].timestamp - 20 > data.timestamp)
        }
    }
}
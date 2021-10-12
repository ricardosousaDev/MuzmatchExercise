package com.ricardo.muzmatchexercise.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ricardo.muzmatchexercise.BR
import com.ricardo.muzmatchexercise.R
import com.ricardo.muzmatchexercise.data.local.ContactEntity
import com.ricardo.muzmatchexercise.databinding.ListItemContactBinding

class ContactsAdapter(
    private val items: List<ContactEntity>,
    private val itemSelected: (ContactEntity) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding: ListItemContactBinding =
            DataBindingUtil.inflate(inflater, R.layout.list_item_contact, parent, false)
        return ContactViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ContactViewHolder(private val binding: ListItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ContactEntity) {
            binding.setVariable(BR.contact, data)
            binding.listItemSelectableView.setOnClickListener {
                itemSelected(data)
            }
        }
    }
}
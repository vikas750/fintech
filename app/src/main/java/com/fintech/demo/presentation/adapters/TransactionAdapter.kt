package com.fintech.demo.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.domain.Contacts
import com.fintech.demo.databinding.ItemTransactionBinding

class TransactionAdapter(
    private val mItemClickListener: (Contacts) -> Unit,
) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    private val mContact = mutableListOf<Contacts>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding =
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = mContact[position]
        holder.binding.contact = contact
        holder.binding.root.setOnClickListener {
            mItemClickListener.invoke(contact)
        }
    }

    override fun getItemCount(): Int = mContact.size

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(contactList: List<Contacts>) = with(mContact) {
        clear()
        addAll(contactList)
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): Contacts = mContact[position]

    inner class ViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root)
}
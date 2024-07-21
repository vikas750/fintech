package com.fintech.demo.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.domain.CardDetails
import com.fintech.demo.databinding.ItemCardListBinding
import com.fintech.demo.presentation.utils.DeviceUtils

class CardListAdapter(
    private val mItemClickListener: (CardDetails) -> Unit,
) : RecyclerView.Adapter<CardListAdapter.ViewHolder>() {

    private val cardDetailsList = mutableListOf<CardDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding =
            ItemCardListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardDetails = cardDetailsList[position]
        holder.mCardListItemBinding?.cardData = cardDetails

        holder.mCardListItemBinding?.root?.setOnClickListener {
            mItemClickListener.invoke(cardDetails)
        }
    }

    override fun getItemCount(): Int = cardDetailsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(cardList: List<CardDetails>) = with(cardDetailsList) {
        clear()
        addAll(cardList)
        notifyDataSetChanged()
    }

    inner class ViewHolder : RecyclerView.ViewHolder {
        var mCardListItemBinding: ItemCardListBinding? = null

        constructor(itemCardListBinding: ItemCardListBinding) : super(itemCardListBinding.root) {
            mCardListItemBinding = itemCardListBinding
            setCardTileHtWd(mCardListItemBinding!!)
        }
    }

    private fun setCardTileHtWd(binder: ItemCardListBinding) {
        val width = ((DeviceUtils.getDeviceWidth(binder.root.context)) / 1.20).toInt()
        val params = binder.root.layoutParams
        params.width = width
        params.height = (width * 0.6).toInt()
        binder.root.layoutParams = params
    }
}
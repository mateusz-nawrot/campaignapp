package com.nawrot.mateusz.campaignapp.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nawrot.mateusz.campaignapp.R
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import com.nawrot.mateusz.campaignapp.list.CampaignRowInterface


class CampaignsAdapter : RecyclerView.Adapter<CampaignViewHolder>() {

    var campaignRowInterface: CampaignRowInterface? = null

    private val items: MutableList<Campaign> = mutableListOf()

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {
        holder.bind(items[position], campaignRowInterface)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_campaign, parent, false)
        return CampaignViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun swapData(newData: List<Campaign>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }
}
package com.nawrot.mateusz.campaignapp.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import com.nawrot.mateusz.campaignapp.base.hide
import com.nawrot.mateusz.campaignapp.base.show
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import com.nawrot.mateusz.campaignapp.list.CampaignRowInterface
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_campaign.*
import kotlinx.android.synthetic.main.item_campaign.view.*


class CampaignViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var campaignRowInterface: CampaignRowInterface? = null
    private lateinit var campaign: Campaign

    init {
        campaignImage.clicks().subscribe {
            campaignRowInterface?.onCampaignClick(campaign)
        }
    }

    fun bind(campaign: Campaign, campaignRowInterface: CampaignRowInterface?) {
        this.campaignRowInterface = campaignRowInterface
        this.campaign = campaign

        containerView.campaignTitle.text = campaign.name
        campaignImageProgress.show()

        Picasso.with(containerView.context)
                .load(campaign.imageUrl)
                .into(containerView.campaignImage, object : Callback {
                    override fun onSuccess() {
                        campaignImageProgress.hide()
                    }
                    override fun onError() {
                        campaignImageProgress.hide()
                    }
                })
    }
}
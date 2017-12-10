package com.nawrot.mateusz.campaignapp.details

import com.nawrot.mateusz.campaignapp.base.BaseView
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign


interface CampaignDetailsView : BaseView {

    fun getCampaignParam(): Campaign?

    fun showCampaignDetails(campaign: Campaign)
}
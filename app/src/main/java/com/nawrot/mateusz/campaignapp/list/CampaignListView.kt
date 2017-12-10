package com.nawrot.mateusz.campaignapp.list

import com.nawrot.mateusz.campaignapp.base.BaseView
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign


interface CampaignListView : BaseView {

    fun showCampaigns(campaigns: List<Campaign>)

}
package com.nawrot.mateusz.campaignapp.list

import com.nawrot.mateusz.campaignapp.base.BasePresenter
import com.nawrot.mateusz.campaignapp.domain.campaign.usecase.GetCampaignsUseCase
import javax.inject.Inject


class CampaignListPresenter @Inject constructor(private val getCampaignsUseCase: GetCampaignsUseCase) : BasePresenter<CampaignListView>() {

    fun getCampaigns() {

    }

}
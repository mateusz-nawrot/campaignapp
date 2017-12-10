package com.nawrot.mateusz.campaignapp.list

import com.nawrot.mateusz.campaignapp.base.BasePresenter
import com.nawrot.mateusz.campaignapp.base.addToCompositeDisposable
import com.nawrot.mateusz.campaignapp.di.ActivityScope
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import com.nawrot.mateusz.campaignapp.domain.campaign.usecase.GetCampaignsUseCase
import javax.inject.Inject

@ActivityScope
class CampaignListPresenter @Inject constructor(private val getCampaignsUseCase: GetCampaignsUseCase) : BasePresenter<CampaignListView>() {

    fun getCampaigns() {
        getCampaignsUseCase.execute().subscribe { campaigns, error ->
            view?.showCampaigns(campaigns)
        }.addToCompositeDisposable(compositeDisposable)
    }

    fun campaignClicked(campaign: Campaign) {
        view?.openCampaignDetailsScreen(campaign)
    }

}
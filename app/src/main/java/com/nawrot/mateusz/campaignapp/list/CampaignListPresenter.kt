package com.nawrot.mateusz.campaignapp.list

import com.nawrot.mateusz.campaignapp.base.BasePresenter
import com.nawrot.mateusz.campaignapp.base.addToCompositeDisposable
import com.nawrot.mateusz.campaignapp.data.network.NetworkException
import com.nawrot.mateusz.campaignapp.di.ActivityScope
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import com.nawrot.mateusz.campaignapp.domain.campaign.usecase.GetCampaignsUseCase
import javax.inject.Inject

@ActivityScope
class CampaignListPresenter @Inject constructor(private val getCampaignsUseCase: GetCampaignsUseCase) : BasePresenter<CampaignListView>() {

    fun getCampaigns() {
        getCampaignsUseCase.execute()
                .doOnSubscribe { view?.showLoadingVisible(true) }
                .subscribe { campaigns, error ->
                    if (error is NetworkException) {
                        view?.showNetworkError()
                    } else {
                        view?.showLoadingVisible(false)
                        view?.showCampaigns(campaigns)
                    }
                }.addToCompositeDisposable(compositeDisposable)
    }

    fun campaignClicked(campaign: Campaign) {
        view?.openCampaignDetailsScreen(campaign)
    }

    fun retryClicked() {
        view?.retryGetCampaigns()
    }

}
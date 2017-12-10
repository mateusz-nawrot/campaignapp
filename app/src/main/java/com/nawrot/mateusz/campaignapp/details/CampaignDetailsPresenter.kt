package com.nawrot.mateusz.campaignapp.details

import com.nawrot.mateusz.campaignapp.base.BasePresenter
import com.nawrot.mateusz.campaignapp.di.ActivityScope
import javax.inject.Inject


@ActivityScope
class CampaignDetailsPresenter @Inject constructor() : BasePresenter<CampaignDetailsView>() {

    fun getCampaignDetailsFromBundle() {
        val campaign = view?.getCampaignParam()

        campaign?.let {
            view?.showCampaignDetails(campaign)
        }
    }

}
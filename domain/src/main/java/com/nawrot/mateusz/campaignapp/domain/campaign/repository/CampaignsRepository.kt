package com.nawrot.mateusz.campaignapp.domain.campaign.repository

import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import io.reactivex.Single


interface CampaignsRepository {

    fun getCampaigns(): Single<List<Campaign>>

}
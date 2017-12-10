package com.nawrot.mateusz.campaignapp.domain.campaign.repository

import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import io.reactivex.Observable


interface CampaignsRepository {

    fun getCampaigns(): Observable<List<Campaign>>

}
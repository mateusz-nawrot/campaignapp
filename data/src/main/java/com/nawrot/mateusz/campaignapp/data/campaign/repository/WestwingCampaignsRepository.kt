package com.nawrot.mateusz.campaignapp.data.campaign.repository

import com.nawrot.mateusz.campaignapp.data.campaign.api.CampaignsApi
import com.nawrot.mateusz.campaignapp.data.campaign.model.GetCampaignsResponse
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import com.nawrot.mateusz.campaignapp.domain.campaign.repository.CampaignsRepository
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject


class WestwingCampaignsRepository @Inject constructor(private val retrofit: Retrofit) : CampaignsRepository {

    override fun getCampaigns(): Observable<List<Campaign>> {
        return retrofit.create(CampaignsApi::class.java)
                .getCampaigns().map { mapResponse(it) }
    }

    private fun mapResponse(response: GetCampaignsResponse): List<Campaign> {
        TODO()
    }

}
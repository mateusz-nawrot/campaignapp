package com.nawrot.mateusz.campaignapp.data.campaign.api

import com.nawrot.mateusz.campaignapp.data.campaign.model.GetCampaignsResponse
import io.reactivex.Single
import retrofit2.http.GET


interface CampaignsApi {

    @GET("cms/test/data.json")
    fun getCampaigns(): Single<GetCampaignsResponse>
}
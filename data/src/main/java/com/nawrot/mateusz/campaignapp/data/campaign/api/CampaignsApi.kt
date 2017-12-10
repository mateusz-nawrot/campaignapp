package com.nawrot.mateusz.campaignapp.data.campaign.api

import com.nawrot.mateusz.campaignapp.data.campaign.model.GetCampaignsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface CampaignsApi {

    @GET("cms/test/data.json")
    fun getCampaigns(): Observable<GetCampaignsResponse>
}
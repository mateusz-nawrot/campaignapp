package com.nawrot.mateusz.campaignapp.data.campaign.model

import com.squareup.moshi.Json


data class GetCampaignsResponse(

        val success: Boolean,

        @Json(name = "messages")
        val messages: MessagesDto,

        @Json(name = "metadata")
        val metadata: MetadataDto

)
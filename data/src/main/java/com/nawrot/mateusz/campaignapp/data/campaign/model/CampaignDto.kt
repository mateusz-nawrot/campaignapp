package com.nawrot.mateusz.campaignapp.data.campaign.model

import com.squareup.moshi.Json


data class CampaignDto(
        val name: String,

        val description: String,

        @Json(name = "url_key")
        val urlKey: String,

        val image: ImageDto
)
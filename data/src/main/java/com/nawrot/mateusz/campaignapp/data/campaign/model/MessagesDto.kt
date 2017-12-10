package com.nawrot.mateusz.campaignapp.data.campaign.model

import com.squareup.moshi.Json


data class MessagesDto(
        @Json(name = "success")
        val messages: List<String>
)
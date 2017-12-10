package com.nawrot.mateusz.campaignapp.domain.campaign.model

import java.io.Serializable


data class Campaign(val name: String,
                    val description: String,
                    val imageUrl: String
) : Serializable
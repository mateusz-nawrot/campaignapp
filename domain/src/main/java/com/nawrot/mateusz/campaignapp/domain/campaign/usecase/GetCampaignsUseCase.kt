package com.nawrot.mateusz.campaignapp.domain.campaign.usecase

import com.nawrot.mateusz.campaignapp.domain.base.SchedulersProvider
import com.nawrot.mateusz.campaignapp.domain.base.usecase.SingleUseCase
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import com.nawrot.mateusz.campaignapp.domain.campaign.repository.CampaignsRepository
import io.reactivex.Single
import javax.inject.Inject


class GetCampaignsUseCase @Inject constructor(schedulersProvider: SchedulersProvider,
                                              private val campaignsRepository: CampaignsRepository) : SingleUseCase<List<Campaign>>(schedulersProvider) {

    override fun createUseCaseSingle(): Single<List<Campaign>> {
        return campaignsRepository.getCampaigns()
    }

}
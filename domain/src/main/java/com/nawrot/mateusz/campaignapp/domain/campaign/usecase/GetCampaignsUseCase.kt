package com.nawrot.mateusz.campaignapp.domain.campaign.usecase

import com.nawrot.mateusz.campaignapp.domain.base.SchedulersProvider
import com.nawrot.mateusz.campaignapp.domain.base.usecase.ObservableUseCase
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import com.nawrot.mateusz.campaignapp.domain.campaign.repository.CampaignsRepository
import io.reactivex.Observable
import javax.inject.Inject


class GetCampaignsUseCase @Inject constructor(schedulersProvider: SchedulersProvider,
                                              private val campaignsRepository: CampaignsRepository) : ObservableUseCase<Unit, List<Campaign>>(schedulersProvider) {

    override fun createUseCaseObservable(param: Unit): Observable<List<Campaign>> {
        return campaignsRepository.getCampaigns()
    }

}
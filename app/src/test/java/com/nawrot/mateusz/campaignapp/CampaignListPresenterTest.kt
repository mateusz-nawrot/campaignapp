@file:Suppress("IllegalIdentifier")

package com.nawrot.mateusz.campaignapp

import com.nawrot.mateusz.campaignapp.data.network.NetworkException
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import com.nawrot.mateusz.campaignapp.domain.campaign.usecase.GetCampaignsUseCase
import com.nawrot.mateusz.campaignapp.list.CampaignListPresenter
import com.nawrot.mateusz.campaignapp.list.CampaignListView
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class CampaignListPresenterTest {

    private val testError: NetworkException = NetworkException()
    private val testCampaign: Campaign = Campaign("Test", "Description", "url")
    private val testCampaignList: List<Campaign> = listOf(testCampaign)

    @Mock
    private lateinit var view: CampaignListView

    @Mock
    private lateinit var getCampaignsUseCase: GetCampaignsUseCase

    private lateinit var presenter: CampaignListPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = CampaignListPresenter(getCampaignsUseCase)

        presenter.attachView(view)
    }

    @Test
    fun `Should show error view after get campaigns request failed`() {
        Mockito.`when`(getCampaignsUseCase.execute()).thenReturn(Single.error(testError))

        presenter.getCampaigns()

        Mockito.verify(view).showNetworkError()
    }

    @Test
    fun `Should set progress to visible when campaigns data is loading`() {
        Mockito.`when`(getCampaignsUseCase.execute()).thenReturn(Single.just(testCampaignList))

        presenter.getCampaigns()

        Mockito.verify(view).showLoadingVisible(true)
    }

    @Test
    fun `Should set progress to gone after get campaigns request is finished`() {
        Mockito.`when`(getCampaignsUseCase.execute()).thenReturn(Single.just(testCampaignList))

        presenter.getCampaigns()

        Mockito.verify(view).showLoadingVisible(true)
        Mockito.verify(view).showLoadingVisible(false)
    }

    @Test
    fun `Should call get campaigns again after retry button clicked`() {
        Mockito.`when`(getCampaignsUseCase.execute()).thenReturn(Single.just(testCampaignList))

        presenter.retryClicked()

        Mockito.verify(view).retryGetCampaigns()
    }

    @Test
    fun `Should open campaign details screen after click on campaign`() {
        presenter.campaignClicked(testCampaign)

        Mockito.verify(view).openCampaignDetailsScreen(testCampaign)
    }


}
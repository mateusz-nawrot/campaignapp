@file:Suppress("IllegalIdentifier")

package com.nawrot.mateusz.campaignapp

import com.nawrot.mateusz.campaignapp.details.CampaignDetailsPresenter
import com.nawrot.mateusz.campaignapp.details.CampaignDetailsView
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class CampaignDetailsPresenterTest {

    private val testCampaign: Campaign = Campaign("Test", "Description", "url")

    @Mock
    private lateinit var view: CampaignDetailsView

    private lateinit var presenter: CampaignDetailsPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = CampaignDetailsPresenter()
        presenter.attachView(view)
    }

    @Test
    fun `Should show campaign details after get campaign details is called`() {
        Mockito.`when`(view.getCampaignParam()).thenReturn(testCampaign)

        presenter.getCampaignDetailsFromBundle()

        Mockito.verify(view).showCampaignDetails(testCampaign)
    }
}
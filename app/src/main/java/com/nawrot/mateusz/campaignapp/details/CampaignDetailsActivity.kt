package com.nawrot.mateusz.campaignapp.details

import android.os.Bundle
import com.nawrot.mateusz.campaignapp.R
import com.nawrot.mateusz.campaignapp.base.BaseActivity
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_campaign_details.*
import javax.inject.Inject


class CampaignDetailsActivity : BaseActivity(), CampaignDetailsView {

    companion object {
        const val CAMPAIGN_DETAILS_KEY = "campaign_details"
    }

    @Inject
    lateinit var presenter: CampaignDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign_details)
        initToolbar(toolbar, true)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
        presenter.getCampaignDetailsFromBundle()
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun getCampaignParam(): Campaign? {
        return intent.getSerializableExtra(CAMPAIGN_DETAILS_KEY) as? Campaign
    }

    override fun showCampaignDetails(campaign: Campaign) {
        Picasso.with(this).load(campaign.imageUrl).fit().centerCrop().into(campaignDetailsImage)
        campaignDetailsTitle.text = campaign.name
        campaignDetailsDescription.text = campaign.description
    }
}
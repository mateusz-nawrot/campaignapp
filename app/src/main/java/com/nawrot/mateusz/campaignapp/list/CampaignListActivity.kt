package com.nawrot.mateusz.campaignapp.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nawrot.mateusz.campaignapp.R
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import dagger.android.AndroidInjection
import javax.inject.Inject

class CampaignListActivity : AppCompatActivity(), CampaignListView {

    @Inject
    lateinit var presenter: CampaignListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign_list)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun showCampaigns(campaigns: List<Campaign>) {

    }
}

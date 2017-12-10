package com.nawrot.mateusz.campaignapp.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.nawrot.mateusz.campaignapp.R
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import com.nawrot.mateusz.campaignapp.list.adapter.CampaignsAdapter
import com.nawrot.mateusz.campaignapp.list.adapter.GridItemDecoration
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_campaign_list.*
import javax.inject.Inject

class CampaignListActivity : AppCompatActivity(), CampaignListView, CampaignRowInterface {

    @Inject
    lateinit var presenter: CampaignListPresenter

    private val campaignsAdapter: CampaignsAdapter by lazy {
        CampaignsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign_list)

        setSupportActionBar(toolbar)

        campaignsRecycler.adapter = campaignsAdapter
        campaignsRecycler.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.gallery_columns))
        campaignsRecycler.addItemDecoration(GridItemDecoration(resources.getDimensionPixelSize(R.dimen.gallery_padding)))
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
        presenter.getCampaigns()
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun showCampaigns(campaigns: List<Campaign>) {
        campaignsRecycler.scheduleLayoutAnimation()
        campaignsAdapter.swapData(campaigns)
    }

    override fun onCampaignClick(campaign: Campaign) {
        presenter.campaignClicked(campaign)
    }

    override fun openCampaignDetailsScreen(campaign: Campaign) {

    }
}

interface CampaignRowInterface {
    fun onCampaignClick(campaign: Campaign)
}

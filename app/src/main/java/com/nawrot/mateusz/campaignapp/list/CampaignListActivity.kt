package com.nawrot.mateusz.campaignapp.list

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.nawrot.mateusz.campaignapp.R
import com.nawrot.mateusz.campaignapp.base.BaseActivity
import com.nawrot.mateusz.campaignapp.details.CampaignDetailsActivity
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import com.nawrot.mateusz.campaignapp.list.adapter.CampaignsAdapter
import com.nawrot.mateusz.campaignapp.list.adapter.GridItemDecoration
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_campaign_list.*
import javax.inject.Inject

class CampaignListActivity : BaseActivity(), CampaignListView, CampaignRowInterface {

    @Inject
    lateinit var presenter: CampaignListPresenter

    private val recyclerItemDecoration: GridItemDecoration by lazy {
        GridItemDecoration(resources.getDimensionPixelSize(R.dimen.gallery_padding))
    }

    private val campaignsAdapter: CampaignsAdapter by lazy {
        CampaignsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign_list)

        initToolbar(toolbar)

        campaignsAdapter.campaignRowInterface = this
        campaignsRecycler.adapter = campaignsAdapter
        campaignsRecycler.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.gallery_columns))
        campaignsRecycler.addItemDecoration(recyclerItemDecoration)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
        presenter.getCampaigns()
        campaignsRecycler.addItemDecoration(recyclerItemDecoration)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
        campaignsRecycler.removeItemDecoration(recyclerItemDecoration)
    }

    override fun onDestroy() {
        super.onDestroy()
        campaignsAdapter.campaignRowInterface = null
    }

    override fun showCampaigns(campaigns: List<Campaign>) {
        campaignsRecycler.scheduleLayoutAnimation()
        campaignsAdapter.swapData(campaigns)
    }

    override fun onCampaignClick(campaign: Campaign) {
        presenter.campaignClicked(campaign)
    }

    override fun openCampaignDetailsScreen(campaign: Campaign) {
        start(CampaignDetailsActivity::class,
                params = mapOf(CampaignDetailsActivity.CAMPAIGN_DETAILS_KEY to campaign))
    }
}

interface CampaignRowInterface {
    fun onCampaignClick(campaign: Campaign)
}

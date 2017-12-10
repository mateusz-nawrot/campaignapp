package com.nawrot.mateusz.campaignapp.list

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.jakewharton.rxbinding2.view.clicks
import com.nawrot.mateusz.campaignapp.R
import com.nawrot.mateusz.campaignapp.base.BaseActivity
import com.nawrot.mateusz.campaignapp.base.addToCompositeDisposable
import com.nawrot.mateusz.campaignapp.base.hide
import com.nawrot.mateusz.campaignapp.base.show
import com.nawrot.mateusz.campaignapp.details.CampaignDetailsActivity
import com.nawrot.mateusz.campaignapp.domain.campaign.model.Campaign
import com.nawrot.mateusz.campaignapp.list.adapter.CampaignsAdapter
import com.nawrot.mateusz.campaignapp.list.adapter.GridItemDecoration
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_campaign_list.*
import kotlinx.android.synthetic.main.view_error.*
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

        errorView.clicks().subscribe {
            presenter.retryClicked()
        }.addToCompositeDisposable(viewDisposable)
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

    override fun showLoadingVisible(visible: Boolean) {
        if (visible) {
            errorView.hide()
            campaignsRecycler.hide()
            loadingProgress.show()
        } else {
            campaignsRecycler.show()
            loadingProgress.hide()
        }
    }

    override fun showNetworkError() {
        loadingProgress.hide()
        campaignsRecycler.hide()
        errorView.show()
    }
}

interface CampaignRowInterface {
    fun onCampaignClick(campaign: Campaign)
}

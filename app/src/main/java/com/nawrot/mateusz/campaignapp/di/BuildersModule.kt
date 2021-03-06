package com.nawrot.mateusz.campaignapp.di

import com.nawrot.mateusz.campaignapp.details.CampaignDetailsActivity
import com.nawrot.mateusz.campaignapp.details.CampaignDetailsActivityModule
import com.nawrot.mateusz.campaignapp.list.CampaignListActivity
import com.nawrot.mateusz.campaignapp.list.CampaignListActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(CampaignListActivityModule::class))
    abstract fun bindCampaignListActivity(): CampaignListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(CampaignDetailsActivityModule::class))
    abstract fun bindCampaignDetailsActivity(): CampaignDetailsActivity

}
package com.nawrot.mateusz.campaignapp.di

import android.content.Context
import com.nawrot.mateusz.campaignapp.App
import com.nawrot.mateusz.campaignapp.R
import com.nawrot.mateusz.campaignapp.data.base.AndroidSchedulersProvider
import com.nawrot.mateusz.campaignapp.data.campaign.repository.WestwingCampaignsRepository
import com.nawrot.mateusz.campaignapp.domain.base.SchedulersProvider
import com.nawrot.mateusz.campaignapp.domain.campaign.repository.CampaignsRepository
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(app: App): Context

    @Binds
    abstract fun schedulersProvider(androidSchedulersProvider: AndroidSchedulersProvider): SchedulersProvider

    @Binds
    abstract fun campaignRepository(stackQuestionsRepository: WestwingCampaignsRepository): CampaignsRepository

    @Module
    companion object {


        @JvmStatic
        @Provides
        @Named("baseUrl")
        fun baseUrl(context: Context): String {
            return context.getString(R.string.base_url)
        }

        @JvmStatic
        @Provides
        @Singleton
        fun okHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.retryOnConnectionFailure(true)

            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logInterceptor)
            return builder.build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun moshi(): Moshi {
            return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun retrofit(okHttpClient: OkHttpClient, moshi: Moshi, @Named("baseUrl") baseUrl: String): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
        }

    }

}
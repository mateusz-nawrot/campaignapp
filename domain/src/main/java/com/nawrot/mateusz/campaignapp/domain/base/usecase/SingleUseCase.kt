package com.nawrot.mateusz.campaignapp.domain.base.usecase

import com.nawrot.mateusz.campaignapp.domain.base.SchedulersProvider
import io.reactivex.Single


abstract class SingleUseCase <R>(private val schedulersProvider: SchedulersProvider) {

    private lateinit var single: Single<R>

    fun execute(): Single<R> {
        single = createUseCaseSingle().compose(schedulersProvider.singleTransformer())
        return single
    }

    protected abstract fun createUseCaseSingle(): Single<R>

}
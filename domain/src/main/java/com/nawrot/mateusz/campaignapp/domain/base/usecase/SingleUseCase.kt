package com.nawrot.mateusz.campaignapp.domain.base.usecase

import com.nawrot.mateusz.campaignapp.domain.base.SchedulersProvider
import io.reactivex.Single


abstract class SingleUseCase <in P, R>(private val schedulersProvider: SchedulersProvider) {

    private lateinit var single: Single<R>

    fun execute(param: P): Single<R> {
        single = createUseCaseSingle(param).compose(schedulersProvider.singleTransformer())
        return single
    }

    protected abstract fun createUseCaseSingle(param: P): Single<R>

}
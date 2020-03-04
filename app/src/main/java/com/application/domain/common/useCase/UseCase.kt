package com.application.domain.common.useCase

import com.application.domain.net.MyResult
import com.application.domain.common.scheduler.DefaultSchedulers
import com.application.domain.common.scheduler.Schedulers
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Base use case class with parameters, handles the coroutine context switching
 * It also allows you to scope it to required job (most of the time viewmodel's)
 *
 * @param schedulers - [DefaultSchedulers] as default value,
 * to switch schedulers, pass the proper ones through the constructor
 * ```
 *    class LoginUseCase(
 *        schedulers: Schedulers,
 *        ...
 *    ) : UseCase<User, LoginUseCase.Params>(
 *        schedulers = schedulers
 *    )
 * ```
 */

abstract class UseCase<out Type, in Params>(
    schedulers: Schedulers = DefaultSchedulers()
) where Type : Any {
    var backgroundContext: CoroutineContext = schedulers.background()
    var foregroundContext: CoroutineContext = schedulers.main()

    abstract suspend fun run(params: Params): MyResult<Type>

    operator fun invoke(parentJob: Job = Job(), params: Params, onResult: (MyResult<Type>) -> Unit = {}) {
        CoroutineScope(backgroundContext + parentJob).launch {
            val result = run(params)
            withContext(foregroundContext) {
                onResult(result)
            }
        }
    }
}

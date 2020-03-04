package com.application.UseCaseTest

import com.application.domain.common.useCase.makeUnconfined
import com.application.domain.net.MyResult
import com.application.domain.net.success
import com.application.domain.repository.AuthenticationRepository
import com.application.domain.usecase.authusecases.CheckUserSignedInUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class CheckUserSignedInUseCaseTest {

    private lateinit var useCase: CheckUserSignedInUseCase
    private val authRepo: AuthenticationRepository = mockk(relaxed = true)

    @Before
    fun setup(){
        useCase = CheckUserSignedInUseCase(authRepo).makeUnconfined()
    }

    @Test
    fun `When CheckUserSignedInUseCase invoked call authentication repository to get user`() {
        coEvery { useCase.run() } returns success(true)

        useCase.invoke()

        coVerify { authRepo.getCurrentUser() }
    }
}
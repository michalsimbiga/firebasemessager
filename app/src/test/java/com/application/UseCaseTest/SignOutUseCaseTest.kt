package com.application.UseCaseTest

import com.application.domain.common.useCase.makeUnconfined
import com.application.domain.net.success
import com.application.domain.repository.AuthenticationRepository
import com.application.domain.usecase.authusecases.SignOutUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class SignOutUseCaseTest {

    private lateinit var useCase: SignOutUseCase
    private val authRepo: AuthenticationRepository = mockk(relaxed = true)

    @Before
    fun setup(){
        useCase = SignOutUseCase(authRepo).makeUnconfined()
    }

    @Test
    fun `When SignOutUseCase invoked call authentication repository to sign out`() {
        coEvery { useCase.run() } returns success(Unit)

        useCase.invoke()

        coVerify { authRepo.signOut() }
    }
}
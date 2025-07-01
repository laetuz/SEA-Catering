package id.neotica.domain.usecase

import id.neotica.domain.remote.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetUserIdUseCaseTest {
    @Mock
    private lateinit var userRepo: UserRepository
    private lateinit var getUserIdUseCase: GetUserIdUseCase

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getUserIdUseCase = GetUserIdUseCase(userRepo)
    }

    @Test
    fun `should return userId when user is logged in`() {
        `when`(userRepo.isLoggedIn()).thenReturn(true)
        `when`(userRepo.getUserId()).thenReturn("1234567")

        val result = getUserIdUseCase()

        assertEquals("1234567", result)
        verify(userRepo).isLoggedIn()
        verify(userRepo).getUserId()
    }

    @Test
    fun `should return empty string when user is not logged in`() {
        // Arrange
        `when`(userRepo.isLoggedIn()).thenReturn(false)

        // Act
        val result = getUserIdUseCase()

        // Assert
        assertEquals("", result)
        verify(userRepo).isLoggedIn()
        verify(userRepo, never()).getUserId()
    }

}
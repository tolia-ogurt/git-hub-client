package com.ua.githubclient.ui.repos_commits

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ua.githubclient.RxImmediateSchedulerRule
import com.ua.githubclient.model.commit_model.CommitModelItem
import com.ua.githubclient.repository.commits.CommitsNetworkRepository
import io.mockk.Ordering
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import io.mockk.verifyOrder
import io.reactivex.Single
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class CommitsViewModelTest {

    private lateinit var commitsRepository: CommitsNetworkRepository
    private lateinit var commitsViewModel: CommitsViewModel
    private lateinit var commitsModelMock: CommitModelItem
    private lateinit var savedStateHandle: SavedStateHandle
    private val isInProgressTemp = MutableLiveData<Boolean>()

    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    val ruleForLivaData = InstantTaskExecutorRule()

    @Before
    fun before() {
        commitsRepository = mockk(relaxed = true)
        savedStateHandle = mockk(relaxed = true)
        commitsViewModel = CommitsViewModel(commitsRepository, savedStateHandle)
        commitsModelMock = mockk(relaxed = true)
    }

    @After
    fun after() {
        unmockkAll()
    }

    @Test
    fun `should return commits list`() {
        val response = listOf(commitsModelMock)
        every { commitsRepository.getCommits(any(), any()) } returns Single.just(response)

        commitsViewModel.loadCommits("userName", "reposName")

        verifyOrder {
            commitsRepository.getCommits(any(), any())
            isInProgressTemp.value = true
            isInProgressTemp.value = false
        }

        val actual = commitsViewModel.commitsList.value
        assertEquals(response.size, actual?.size)
        assertEquals(response[0].commit, actual?.get(0)?.commitsModel?.commit)
    }

    @Test
    fun `should throws exception`() {
        every { commitsRepository.getCommits(any(), any()) } returns Single.error(Throwable())

        commitsViewModel.loadCommits("userName", "reposName")

        verify(ordering = Ordering.ORDERED) {
            commitsRepository.getCommits(any(), any())
            isInProgressTemp.value = true
            isInProgressTemp.value = false
        }

        val errorValue = commitsViewModel.errorData.value
        Assert.assertTrue(errorValue is Throwable)
    }
}
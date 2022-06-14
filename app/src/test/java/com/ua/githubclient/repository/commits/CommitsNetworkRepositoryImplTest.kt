package com.ua.githubclient.repository.commits

import com.ua.githubclient.RxImmediateSchedulerRule
import com.ua.githubclient.model.commit_model.CommitModelItem
import com.ua.githubclient.service.GetCommits
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals


internal class CommitsNetworkRepositoryImplTest {

    private lateinit var commitService: GetCommits
    private lateinit var commitsNetworkRepositoryImpl: CommitsNetworkRepositoryImpl
    private lateinit var commitModelItem: CommitModelItem

    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun createRepository() {
        commitModelItem = mockk(relaxed = true)
        commitService = mockk()
        commitsNetworkRepositoryImpl = CommitsNetworkRepositoryImpl(commitService)
    }

    @After
    fun after() {
        clearAllMocks()
    }

    @Test
    fun `get GitHub repository`() {
        val response = listOf(commitModelItem)
        every { commitService.getReposCommits(any(), any()) } returns Single.just(response)

        val result = commitsNetworkRepositoryImpl.getCommits(owner = "owner", repoName = "repoName")

        verify { commitService.getReposCommits(any(), any()) }

        val testObserver = TestObserver<List<CommitModelItem>>()
        result.subscribe(testObserver)
        val listResult = testObserver.values()[0]
        assertEquals(listResult.size, response.size)
        assertEquals(listResult[0].author, response[0].author)
        assertEquals(listResult[0].commit, response[0].commit)
    }
}
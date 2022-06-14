package com.ua.githubclient.repository.github_repos

import com.ua.githubclient.RxImmediateSchedulerRule
import com.ua.githubclient.model.repos_model.GitHubReposModel
import com.ua.githubclient.service.GetGithubReposService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions

internal class GithubReposNetworkRepositoryImplTest {

    private lateinit var githubReposService: GetGithubReposService
    private lateinit var githubReposNetworkRepository: GithubReposNetworkRepositoryImpl

    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun createRepository() {
        githubReposService = mockk()
        githubReposNetworkRepository = GithubReposNetworkRepositoryImpl(githubReposService)
    }

    @Test
    fun `get GitHub repository`() {
        val response = listOf(
            GitHubReposModel("name1", "description1", mockk(relaxed = true)),
            GitHubReposModel("name2", "description2", mockk(relaxed = true))
        )
        every { githubReposService.getGitHubRepos(any()) } returns Single.just(response)

        val result = githubReposNetworkRepository.getGitHubRepos("userName")

        verify { githubReposService.getGitHubRepos(any()) }
        val testObserver = TestObserver<List<GitHubReposModel>>()
        result.subscribe(testObserver)
        val listResult = testObserver.values()[0]
        assertEquals(listResult.size, response.size)
        assertEquals(listResult[0].name, response[0].name)
        assertEquals(listResult[0].description, response[0].description)
        Assertions.assertEquals(listResult[1].name, response[1].name)
        Assertions.assertEquals(listResult[1].description, response[1].description)
    }
}
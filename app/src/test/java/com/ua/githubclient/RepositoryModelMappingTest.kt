package com.ua.githubclient

import com.ua.githubclient.model.repos_model.GitHubReposDomainModel
import com.ua.githubclient.model.repos_model.GitHubReposModel
import com.ua.githubclient.model.repos_model.toGitHubReposDomainModel
import io.mockk.mockk
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class RepositoryModelMappingTest {

    private val gitHubReposModel = GitHubReposModel("name", "description", mockk(relaxed = true))
    private val gitHubReposModelDomain = GitHubReposDomainModel(
        gitHubReposModel.name,
        gitHubReposModel.description,
        gitHubReposModel.owner.login
    )

    @Test
    fun `each field input class should equals each field output class`() {
        assertThat(
            gitHubReposModel.toGitHubReposDomainModel(),
            `is`(equalTo(gitHubReposModelDomain))
        )
    }
}
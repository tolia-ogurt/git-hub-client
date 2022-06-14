package com.ua.githubclient.data.source

import com.ua.githubclient.model.repos_model.GitHubReposModel
import com.ua.githubclient.service.GetGithubReposService
import io.reactivex.Single

class FakeDataSource(private val gitHubRepos: List<GitHubReposModel>) : GetGithubReposService {

    override fun getGitHubRepos(userName: String): Single<List<GitHubReposModel>> {
        return Single.just(gitHubRepos)
    }
}
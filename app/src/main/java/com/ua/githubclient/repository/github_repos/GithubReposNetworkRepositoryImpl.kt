package com.ua.githubclient.repository.github_repos

import com.ua.githubclient.model.repos_model.GitHubReposModel
import com.ua.githubclient.service.GetGithubReposService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GithubReposNetworkRepositoryImpl @Inject constructor(
    private val getGithubReposService: GetGithubReposService
) : GithubReposNetworkRepository {

    override fun getGitHubRepos(userName: String): Single<List<GitHubReposModel>> {
        return getGithubReposService.getGitHubRepos(userName)
            .subscribeOn(Schedulers.io())
    }
}
package com.ua.githubclient.repository.github_repos

import com.ua.githubclient.model.repos_model.GitHubReposModel
import io.reactivex.Single

interface GithubReposNetworkRepository {

    fun getGitHubRepos(userName: String): Single<List<GitHubReposModel>>
}
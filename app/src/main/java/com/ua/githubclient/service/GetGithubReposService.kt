package com.ua.githubclient.service

import com.ua.githubclient.model.repos_model.GitHubReposModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GetGithubReposService {

    @GET("/users/{username}/repos")
    fun getGitHubRepos(@Path("username") userName: String): Single<List<GitHubReposModel>>
}
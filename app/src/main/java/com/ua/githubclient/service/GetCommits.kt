package com.ua.githubclient.service

import com.ua.githubclient.model.commit_model.CommitModelItem
import com.ua.githubclient.model.repos_model.GitHubReposModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GetCommits {

    @GET("/repos/{owner}/{repo}/commits")
    fun getReposCommits(
        @Path("owner") owner: String,
        @Path("repo") repoName: String
    ): Single<List<CommitModelItem>>
}
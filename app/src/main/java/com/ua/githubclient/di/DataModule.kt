package com.ua.githubclient.di

import com.ua.githubclient.repository.commits.CommitsNetworkRepository
import com.ua.githubclient.repository.commits.CommitsNetworkRepositoryImpl
import com.ua.githubclient.repository.github_repos.GithubReposNetworkRepository
import com.ua.githubclient.repository.github_repos.GithubReposNetworkRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun provideReposGithubNetwork(
        githubReposNetworkRepositoryImpl: GithubReposNetworkRepositoryImpl
    ): GithubReposNetworkRepository

    @Binds
    abstract fun provideCommitsNetworkRepository(
        commitsNetworkRepositoryImp: CommitsNetworkRepositoryImpl
    ): CommitsNetworkRepository
}
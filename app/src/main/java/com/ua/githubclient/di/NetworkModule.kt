package com.ua.githubclient.di

import com.ua.githubclient.service.GetCommits
import com.ua.githubclient.service.GetGithubReposService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubReposService(retrofit: Retrofit): GetGithubReposService {
        return retrofit.create(GetGithubReposService::class.java)
    }

    @Provides
    @Singleton
    fun provideCommitsService(retrofit: Retrofit): GetCommits {
        return retrofit.create(GetCommits::class.java)
    }
}
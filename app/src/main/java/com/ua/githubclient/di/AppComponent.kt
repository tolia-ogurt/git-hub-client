package com.ua.githubclient.di

import android.content.Context
import com.ua.githubclient.ui.github_repos.GithubReposFragment
import com.ua.githubclient.ui.repos_commits.CommitsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DataModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(githubReposFragment: GithubReposFragment)
    fun inject(commitsFragment: CommitsFragment)
}
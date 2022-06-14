package com.ua.githubclient.di

import androidx.savedstate.SavedStateRegistryOwner
import com.ua.githubclient.ui.github_repos.GithubReposViewModelFactory
import dagger.assisted.AssistedFactory

@AssistedFactory
interface GithubReposViewModelAssistedFactory {
    fun create(owner: SavedStateRegistryOwner): GithubReposViewModelFactory
}
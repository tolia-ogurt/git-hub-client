package com.ua.githubclient.ui.github_repos

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.ua.githubclient.repository.github_repos.GithubReposNetworkRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class GithubReposViewModelFactory @AssistedInject constructor(
    private val userNetworkRepository: GithubReposNetworkRepository,
    @Assisted owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = GithubReposViewModel(userNetworkRepository, handle) as T
}
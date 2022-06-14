package com.ua.githubclient.ui.repos_commits

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.ua.githubclient.repository.commits.CommitsNetworkRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class CommitsViewModelFactory @AssistedInject constructor(
    private val repository: CommitsNetworkRepository,
    @Assisted owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = CommitsViewModel(repository, handle) as T
}
package com.ua.githubclient.di

import androidx.savedstate.SavedStateRegistryOwner
import com.ua.githubclient.ui.repos_commits.CommitsViewModelFactory
import dagger.assisted.AssistedFactory

@AssistedFactory
interface CommitsViewModelAssistedFactory {
    fun create(owner: SavedStateRegistryOwner): CommitsViewModelFactory
}
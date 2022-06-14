package com.ua.githubclient.ui.github_repos

import android.view.View
import androidx.navigation.findNavController
import com.ua.githubclient.R
import com.ua.githubclient.model.repos_model.GitHubReposDomainModel
import com.ua.githubclient.utils.ItemViewModel

class ReposItemViewModel(val reposModel: GitHubReposDomainModel) : ItemViewModel {
    override val layoutId: Int = R.layout.repos_item

    fun onItemClicked(view: View, userName: String, reposName: String) {
        val action =
            GithubReposFragmentDirections.actionUserListFragmentToCommitsFragment(
                userName,
                reposName
            )
        view.findNavController().navigate(action)
    }
}
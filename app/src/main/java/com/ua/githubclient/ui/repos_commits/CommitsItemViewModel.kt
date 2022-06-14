package com.ua.githubclient.ui.repos_commits

import com.ua.githubclient.R
import com.ua.githubclient.model.commit_model.CommitItemDomainModel
import com.ua.githubclient.utils.ItemViewModel

class CommitsItemViewModel(val commitsModel: CommitItemDomainModel) : ItemViewModel {
    override val layoutId: Int = R.layout.commit_item
}
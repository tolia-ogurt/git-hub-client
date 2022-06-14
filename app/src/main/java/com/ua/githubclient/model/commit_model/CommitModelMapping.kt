package com.ua.githubclient.model.commit_model

fun CommitModelItem.toCommitItemDomainModel(): CommitItemDomainModel {
    return CommitItemDomainModel(
        author = this.author,
        commit = this.commit,
    )
}
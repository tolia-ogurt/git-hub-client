package com.ua.githubclient.repository.commits

import com.ua.githubclient.model.commit_model.CommitModelItem
import io.reactivex.Single

interface CommitsNetworkRepository {

    fun getCommits(owner: String,repoName: String): Single<List<CommitModelItem>>
}
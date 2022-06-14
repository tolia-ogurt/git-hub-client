package com.ua.githubclient.repository.commits

import com.ua.githubclient.model.commit_model.CommitModelItem
import com.ua.githubclient.service.GetCommits
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommitsNetworkRepositoryImpl @Inject constructor(
    private val getCommitsService: GetCommits
) : CommitsNetworkRepository {

    override fun getCommits(owner: String, repoName: String): Single<List<CommitModelItem>> {
        return getCommitsService.getReposCommits(owner, repoName)
            .subscribeOn(Schedulers.io())
    }
}
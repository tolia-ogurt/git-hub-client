package com.ua.githubclient.model.commit_model

data class CommitModelItem(
    val author: Author? = null,
    val comments_url: String? = null,
    val commit: Commit? = null,
    val committer: CommitterX? = null,
    val html_url: String? = null,
    val node_id: String? = null,
    val parents: List<Parent>? = null,
    val sha: String? = null,
    val url: String? = null
)

package com.ua.githubclient.model.repos_model

data class GitHubReposModel(
    val name: String,
    val description: String,
    val owner: Owner
)

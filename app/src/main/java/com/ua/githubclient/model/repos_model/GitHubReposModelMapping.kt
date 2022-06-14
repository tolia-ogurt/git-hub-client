package com.ua.githubclient.model.repos_model

fun GitHubReposModel.toGitHubReposDomainModel(): GitHubReposDomainModel {
    return GitHubReposDomainModel(
        reposName = this.name,
        description = this.description,
        login = this.owner.login
    )
}

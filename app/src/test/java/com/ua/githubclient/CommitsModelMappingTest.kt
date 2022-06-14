package com.ua.githubclient

import com.ua.githubclient.model.commit_model.CommitItemDomainModel
import com.ua.githubclient.model.commit_model.CommitModelItem
import com.ua.githubclient.model.commit_model.toCommitItemDomainModel
import io.mockk.mockk
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class CommitsModelMappingTest {
    private val commitsModel = mockk<CommitModelItem>(relaxed = true)
    private val commitsDomainModel = CommitItemDomainModel(author = commitsModel.author, commit = commitsModel.commit)

    @Test
    fun `each field input class should equals each field output class`() {
        assertEquals(commitsModel.author, commitsDomainModel.author)
        Assert.assertThat(
            commitsModel.toCommitItemDomainModel(),
            CoreMatchers.`is`(CoreMatchers.equalTo(commitsDomainModel))
        )
    }
}
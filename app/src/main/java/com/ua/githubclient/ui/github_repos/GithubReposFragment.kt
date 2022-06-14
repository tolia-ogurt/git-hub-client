package com.ua.githubclient.ui.github_repos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ua.githubclient.MyApplication
import com.ua.githubclient.databinding.ReposListFragmentBinding
import com.ua.githubclient.di.GithubReposViewModelAssistedFactory
import javax.inject.Inject

class GithubReposFragment : Fragment() {

    @Inject
    lateinit var assistedFactory: GithubReposViewModelAssistedFactory
    private val viewModel: GithubReposViewModel by viewModels { assistedFactory.create(this) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ReposListFragmentBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@GithubReposFragment
            this.viewModel = this@GithubReposFragment.viewModel
        }.also {
            it.btnSearch.setOnClickListener { viewModel.loadUserRepos() }
        }.root
    }
}
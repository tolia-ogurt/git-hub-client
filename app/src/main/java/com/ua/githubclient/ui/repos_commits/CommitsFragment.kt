package com.ua.githubclient.ui.repos_commits

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ua.githubclient.MyApplication
import com.ua.githubclient.databinding.CommitsListFragmentBinding
import com.ua.githubclient.di.CommitsViewModelAssistedFactory
import javax.inject.Inject

class CommitsFragment : Fragment() {

    @Inject
    lateinit var assistedFactory: CommitsViewModelAssistedFactory
    private val viewModel: CommitsViewModel by viewModels { assistedFactory.create(this) }

    private val args: CommitsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return CommitsListFragmentBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@CommitsFragment
            this.viewModel = this@CommitsFragment.viewModel
        }.also {
            viewModel.loadCommits(args.userName, args.reposName)
        }.root
    }
}
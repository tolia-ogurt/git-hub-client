package com.ua.githubclient.ui.github_repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ua.githubclient.model.repos_model.toGitHubReposDomainModel
import com.ua.githubclient.repository.github_repos.GithubReposNetworkRepository
import com.ua.githubclient.utils.ItemViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class GithubReposViewModel @Inject constructor(
    private val userNetworkRepository: GithubReposNetworkRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _reposList = MutableLiveData<List<ItemViewModel>>()
    val reposList: LiveData<List<ItemViewModel>> get() = _reposList
    private val _errorData = MutableLiveData<String>()
    val errorData: LiveData<String> get() = _errorData
    val searchEditTextData = MutableLiveData<String>()
    val isInProgressTemp = MutableLiveData<Boolean>()
    val noResultData = MutableLiveData<Boolean>()
    private var disposable: Disposable? = null

    fun loadUserRepos() {
        val userName = searchEditTextData.value.toString()
        disposable = userNetworkRepository.getGitHubRepos(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isInProgressTemp.value = true }
            .doFinally {
                isInProgressTemp.value = false
                noResultData.value = _reposList.value.isNullOrEmpty()
            }
            .subscribe({ response ->
                _reposList.value = response.map { ReposItemViewModel(it.toGitHubReposDomainModel()) }
            }, {
                _errorData.value = it.message
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
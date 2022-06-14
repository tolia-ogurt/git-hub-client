package com.ua.githubclient.ui.repos_commits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ua.githubclient.model.commit_model.toCommitItemDomainModel
import com.ua.githubclient.repository.commits.CommitsNetworkRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CommitsViewModel @Inject constructor(
    private val repository: CommitsNetworkRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _commitsList = MutableLiveData<List<CommitsItemViewModel>>()
    val commitsList: LiveData<List<CommitsItemViewModel>> get() = _commitsList
    private val _errorData = MutableLiveData<Throwable>()
    val errorData: LiveData<Throwable> get() = _errorData
    val isInProgressTemp = MutableLiveData<Boolean>()
    private var disposable: Disposable? = null

    fun loadCommits(userName: String, reposName: String) {
        disposable = repository.getCommits(userName, reposName)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isInProgressTemp.value = true }
            .doFinally {
                isInProgressTemp.value = false
            }
            .subscribe({ response ->
                _commitsList.value =
                    response.map { CommitsItemViewModel(it.toCommitItemDomainModel()) }
            }, {
                _errorData.value = it
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
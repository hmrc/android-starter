package uk.gov.hmrc.ui.home

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import uk.gov.hmrc.domain.commands.PostsCommand
import uk.gov.hmrc.ui.dashboard.DashboardViewModel
import javax.inject.Inject
import javax.inject.Provider

class HomeViewModel(
    private val postsCommand: PostsCommand
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hello darkness my old friend"
    }
    val text: LiveData<String> = _text

    private val _buttonTitle = MutableLiveData<String>().apply {
        value = "Primary button"
    }
    val buttonTitle: LiveData<String> = _buttonTitle

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {

            when (postsCommand.execute()) {
                PostsCommand.Result.Success -> {
                    _text.postValue("Success")
                }
                PostsCommand.Result.ServiceError, PostsCommand.Result.NetworkError -> {
                    _text.postValue("Failed")
                }
            }
        }
    }

    fun onButtonTapped() {
        fetchPosts()
    }
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(
    private val postsCommand: Provider<PostsCommand>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(postsCommand.get()) as T
    }
}

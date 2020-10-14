package uk.gov.hmrc.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uk.gov.hmrc.ui.dashboard.DashboardViewModel
import javax.inject.Inject

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hello darkness my old friend"
    }
    val text: LiveData<String> = _text

    private val _buttonTitle = MutableLiveData<String>().apply {
        value = "Primary button"
    }
    val buttonTitle: LiveData<String> = _buttonTitle
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel() as T
    }
}

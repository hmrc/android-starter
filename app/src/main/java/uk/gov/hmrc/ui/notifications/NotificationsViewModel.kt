package uk.gov.hmrc.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uk.gov.hmrc.ui.home.HomeViewModel
import javax.inject.Inject

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}

@Suppress("UNCHECKED_CAST")
class NotificationsViewModelFactory @Inject constructor(
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotificationsViewModel() as T
    }
}

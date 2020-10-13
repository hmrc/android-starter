package uk.gov.hmrc.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import uk.gov.hmrc.App
import uk.gov.hmrc.databinding.FragmentNotificationsBinding
import uk.gov.hmrc.common.autoCleared
import javax.inject.Inject

class NotificationsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: NotificationsViewModelFactory

    private val viewModel: NotificationsViewModel by viewModels { viewModelFactory }
    private var binding: FragmentNotificationsBinding by autoCleared()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.get(requireContext()).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textNotifications.text = it
        })
        return binding.root
    }
}
package uk.gov.hmrc.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import uk.gov.hmrc.App
import uk.gov.hmrc.databinding.FragmentHomeBinding
import uk.gov.hmrc.common.autoCleared
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private var binding: FragmentHomeBinding by autoCleared()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.get(requireContext()).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textView.text = it
        })
        viewModel.buttonTitle.observe(viewLifecycleOwner, Observer {
            binding.primaryButton.text = it
        })
        return binding.root
    }
}
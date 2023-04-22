package com.example.horoscopeapp.presentation.fragments

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.horoscopeapp.R
import com.example.horoscopeapp.databinding.FragmentLuckyBinding
import com.example.horoscopeapp.presentation.RandomCardProvider
import com.example.horoscopeapp.presentation.viewmodel.LuckyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LuckyFragment : Fragment() {

    private val viewModel by viewModels<LuckyViewModel>()
    private var _binding: FragmentLuckyBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardsProvider: RandomCardProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.viewFrontContainer.ivLuckyCard
        binding.viewBackContainer.viewBack.setOnClickListener {
            prepareCard()
            flipCard()
        }
    }

    private fun flipCard() {
        try {
            // visibilidad
            binding.viewFrontContainer.viewFront.isVisible = true

            // efecto 3d
            val scale = requireContext().resources.displayMetrics.density
            val cameraDist = 8000 * scale
            binding.viewFrontContainer.viewFront.cameraDistance = cameraDist
            binding.viewBackContainer.viewBack.cameraDistance = cameraDist

            // recuperamos la animacion FlipOut
            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(requireContext(), R.animator.flip_out) as AnimatorSet

            flipOutAnimatorSet.setTarget(binding.viewBackContainer.viewBack)

            // recuperamos y seteamos la animacion flipIn
            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(requireContext(), R.animator.flip_in) as AnimatorSet
            flipInAnimatorSet.setTarget(binding.viewFrontContainer.viewFront)

            // iniciamos las animaciones
            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()

            flipInAnimatorSet.doOnEnd {
                binding.tvLuckyInfo.animate().alpha(1f).duration = 1500
                binding.viewBackContainer.viewBack.isVisible = false
            }
        } catch (e: java.lang.Exception) {
            Log.e("error", e.toString())
        }
    }

    private fun prepareCard() {
        val luck = randomCardsProvider.getLucky()
        binding.viewFrontContainer.ivLuckyCard.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                luck.image,
            ),
        )
        binding.tvLuckyInfo.text = getString(luck.text)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLuckyBinding.inflate(inflater, container, false)
        return binding.root
    }
}

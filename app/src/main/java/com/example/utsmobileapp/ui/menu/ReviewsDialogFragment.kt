package com.example.utsmobileapp.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utsmobileapp.adapter.ReviewAdapter
import com.example.utsmobileapp.databinding.DialogReviewsBinding
import com.example.utsmobileapp.response.ReviewsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReviewsDialogFragment : DialogFragment() {

    private var _binding: DialogReviewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reviewsJson = arguments?.getString(EXTRA_REVIEWS)
        val type = object : TypeToken<List<ReviewsItem>>() {}.type
        val reviews: List<ReviewsItem> = if (!reviewsJson.isNullOrEmpty()) {
            Gson().fromJson(reviewsJson, type) ?: emptyList()
        } else {
            emptyList()
        }

        binding.rvReviews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvReviews.adapter = ReviewAdapter(reviews)

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.9).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val EXTRA_REVIEWS = "extra_reviews"

        fun newInstance(reviewsJson: String): ReviewsDialogFragment {
            val fragment = ReviewsDialogFragment()
            val args = Bundle().apply {
                putString(EXTRA_REVIEWS, reviewsJson)
            }
            fragment.arguments = args
            return fragment
        }
    }
}

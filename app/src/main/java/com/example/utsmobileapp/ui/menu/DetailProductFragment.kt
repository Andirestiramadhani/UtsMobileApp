package com.example.utsmobileapp.ui.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utsmobileapp.adapter.ImageAdapter
import com.example.utsmobileapp.databinding.FragmentDetailProductBinding
import com.example.utsmobileapp.helpers.Config
import com.example.utsmobileapp.model.ProductsItem
import com.example.utsmobileapp.response.ReviewsItem
import com.google.gson.Gson

class DetailProductFragment : Fragment() {

    private var _binding: FragmentDetailProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = arguments?.getString(Config.EXTRA_PRODUCT)
        if (json.isNullOrEmpty()) {
            findNavController().popBackStack()
            return
        }

        val data = Gson().fromJson(json, ProductsItem::class.java)

        // Daftar gambar (images); kalau kosong pakai thumbnail
        val imageList: List<String> =
            data.images.orEmpty().filterNotNull().ifEmpty { listOfNotNull(data.thumbnail) }
        binding.rvImages.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvImages.adapter = ImageAdapter(imageList) { url -> openImage(url) }

        binding.tvTitle.text = data.title.orEmpty()
        binding.tvId.text = data.id?.toString().orEmpty()
        binding.tvCategory.text = data.category.orEmpty()
        binding.tvPrice.text = data.price?.let { "$$it" }.orEmpty()
        binding.tvRating.text = data.rating?.toString().orEmpty()
        binding.tvStock.text = data.stock?.toString().orEmpty()
        binding.tvAvailability.text = data.availabilityStatus.orEmpty()
        binding.tvTags.text = data.tags.orEmpty().filterNotNull().joinToString(", ")
        binding.tvDimensions.text = data.dimensions?.let {
            "Lebar ${it.width} x Tinggi ${it.height} x Dalam ${it.depth}"
        }.orEmpty()
        binding.tvDescription.text = data.description.orEmpty()

        val reviews = data.reviews.orEmpty().filterNotNull()
        val topReview = reviews.firstOrNull()

        if (topReview == null) {
            binding.tvReviews.text = "Belum ada review"
            binding.tvSeeAllReviews.visibility = View.GONE
        } else {
            binding.tvReviews.text = formatReview(topReview)
            if (reviews.size > 1) {
                binding.tvSeeAllReviews.visibility = View.VISIBLE
                binding.tvSeeAllReviews.text = "Lihat semua review (${reviews.size})"
                binding.tvSeeAllReviews.setOnClickListener {
                    val reviewsJson = Gson().toJson(reviews)
                    ReviewsDialogFragment.newInstance(reviewsJson)
                        .show(childFragmentManager, "ReviewsDialogFragment")
                }
            } else {
                binding.tvSeeAllReviews.visibility = View.GONE
            }
        }

        binding.btnViewImage.setOnClickListener {
            imageList.firstOrNull()?.let { openImage(it) }
        }
    }

    private fun formatReview(r: ReviewsItem): String {
        return "${r.reviewerName.orEmpty()} (${r.reviewerEmail.orEmpty()})\n" +
                "Rating: ${r.rating ?: "-"}/5\n" +
                "\"${r.comment.orEmpty()}\"\n" +
                r.date.orEmpty()
    }

    private fun openImage(url: String) {
        val intent = Intent(requireContext(), ViewImageActivity::class.java)
        intent.putExtra(Config.EXTRA_URL_IMAGE, url)
        startActivity(intent)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

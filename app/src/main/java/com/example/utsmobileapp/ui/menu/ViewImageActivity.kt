package com.example.utsmobileapp.ui.menu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.utsmobileapp.databinding.ActivityViewImageBinding
import com.example.utsmobileapp.helpers.Config

class ViewImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val urlImage = intent.getStringExtra(Config.EXTRA_URL_IMAGE)

        if (urlImage.isNullOrBlank()) {
            Toast.makeText(this, "URL gambar kosong", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        Glide.with(this)
            .load(urlImage)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_dialog_alert)
            .fitCenter()
            .into(binding.imgItemPhoto)
    }
}

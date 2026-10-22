package com.example.utsmobileapp.helpers

class Config {

    companion object {

        // Alamat API Product (DummyJSON)
        const val BASE_URL = "https://dummyjson.com/"

        // Jumlah product yang ditampilkan (soal: 25 product)
        const val LIMIT = 25
        const val SELECT = "id,title,description,category,price,stock," +
                "availabilityStatus,tags,dimensions,reviews,rating,images,thumbnail"

        const val EXTRA_PRODUCT = "extra_product"

        const val EXTRA_URL_IMAGE = "extra_url_image"
    }
}

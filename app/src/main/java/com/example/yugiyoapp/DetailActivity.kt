package com.example.yugiyoapp

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.yugiyoapp.data.RestoranData
import com.example.yugiyoapp.model.Restoran
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton

class DetailActivity : AppCompatActivity() {
    private var id: Int = 0
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        configureView()
    }

    @SuppressLint("WrongViewCast", "InflateParams")
    private fun configureView() {
        val result = intent.getSerializableExtra("intent_result") as? Restoran
        val titleText = findViewById<TextView>(R.id.title_resto)
        val imageResto = findViewById<ImageView>(R.id.image_resto)
        val minimumPrice = findViewById<TextView>(R.id.minimum_price_order)
        val shippingPrice = findViewById<TextView>(R.id.shipping_price)
        val time = findViewById<TextView>(R.id.time_delivery)
        val distance = findViewById<TextView>(R.id.distance)
        val likeButton = findViewById<MaterialButton>(R.id.like_button)
        val likeCounter = findViewById<TextView>(R.id.like_counter)
        val ratingText = findViewById<TextView>(R.id.rating_text)
        val shippingPriceReal = findViewById<TextView>(R.id.shipping_price_real)
        val dialog = BottomSheetDialog(this)
        val viewSheet = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        val btnClose = viewSheet.findViewById<Button>(R.id.konfirm_button)
        val rBar = viewSheet.findViewById<RatingBar>(R.id.rBar)
        val menuList = findViewById<LinearLayout>(R.id.list_menu)
        val menuInflater = LayoutInflater.from(this@DetailActivity)

        if (result != null) {
            id = result.id
            isFavorite = result.isFavorite

            if (!result.isRatingDone) {
                dialog.show()
            }

            ratingText.text = String.format("%.1f", result.rating)
            titleText.text = result.title
            imageResto.setImageResource(result.imageResto)
            minimumPrice.text = result.minimumPrice
            shippingPrice.text = result.shippingPrice
            time.text = result.time
            distance.text = result.distance
            likeCounter.text = result.likeCount.toString()
            shippingPriceReal.text = result.shippingPriceReal
            shippingPriceReal.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)

            if (result.isFavorite) {
                likeButton.setIconResource(R.drawable.ic_favorite_filled_)
            } else {
                likeButton.setIconResource(R.drawable.ic_favorite)
            }

            for (element in result.menu) {
                val viewItem = menuInflater.inflate(R.layout.item_menu, menuList, false)
                val itemText = viewItem.findViewById<TextView>(R.id.item_menu_text)
                itemText.text = element

                menuList.addView(viewItem)
            }

            btnClose.setOnClickListener {
                for (i in 0 until RestoranData.restorans.size) {
                    val value = RestoranData.restorans[i]
                    if (value.id == id) {
                        RestoranData.restorans[i].isRatingDone = true
                        RestoranData.restorans[i].rating = (((result.ratingCount * result.rating) + rBar.rating)/(result.ratingCount+1))
                        RestoranData.restorans[i].ratingCount += 1
                        ratingText.text = String.format("%.1f", RestoranData.restorans[i].rating)
                    }
                }
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(viewSheet)

            likeButton.setOnClickListener {
                result.isFavorite = !result.isFavorite
                if (result.isFavorite) {
                    likeButton.setIconResource(R.drawable.ic_favorite_filled_)
                    for (i in 0 until RestoranData.restorans.size) {
                        val value = RestoranData.restorans[i]
                        if (value.id == id) {
                            RestoranData.restorans[i].likeCount+= 1
                            likeCounter.text = RestoranData.restorans[i].likeCount.toString()
                        }
                    }

                } else {
                    likeButton.setIconResource(R.drawable.ic_favorite)
                    for (i in 0 until RestoranData.restorans.size) {
                        val value = RestoranData.restorans[i]
                        if (value.id == id) {
                            RestoranData.restorans[i].likeCount-= 1
                            likeCounter.text = RestoranData.restorans[i].likeCount.toString()
                        }
                    }
                }

                for (i in 0 until RestoranData.restorans.size) {
                    val value = RestoranData.restorans[i]
                    if (value.id == id) {
                        RestoranData.restorans[i].isFavorite = result.isFavorite
                    }
                }
            }

        }

    }

    fun didTapBackButton(view: View) {
        super.onBackPressed()
    }

    fun didTapShareButton(view: View) {
        Toast.makeText(
            this,
            "Shared",
            Toast.LENGTH_SHORT
        ).show()
    }
}
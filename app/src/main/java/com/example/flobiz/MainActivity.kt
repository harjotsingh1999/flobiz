package com.example.flobiz

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flobiz.adapters.ListRecyclerAdapter
import com.example.flobiz.data.SampleData
import com.example.flobiz.databinding.ActivityMainBinding
import com.example.flobiz.databinding.ItemBottomCardBinding
import com.example.flobiz.models.Photo
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var data: ArrayList<Photo>
    private lateinit var adapter: ListRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
    }

    private fun setUpBottomCard(photo: Photo) {
        if (getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).contains(SHOW_BOTTOM_CARD)) {
            binding.bottomCard.root.visibility = View.GONE
            return
        }
        val imageView = binding.bottomCard.bottomItemImageView
        val textView = binding.bottomCard.bottomItemTextView
        Picasso.get().load(photo.photoUrl).fit().into(imageView)
        textView.text = photo.title
        binding.bottomCard.removeButton.setOnClickListener {
            binding.bottomCard.root.visibility = View.GONE
            getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).edit()
                .putBoolean(SHOW_BOTTOM_CARD, false).apply()
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        data = ArrayList()
        try {
            val jsonArray = JSONArray(SampleData.sampleData)
            for (i in 0..28) {
                val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                data.add(
                    Photo(
                        jsonObject.getString("title"),
                        jsonObject.getString("url"),
                        jsonObject.getString("thumbnailUrl"),
                    )
                )
            }

            adapter = ListRecyclerAdapter(data)
            binding.recyclerView.adapter = adapter
            setUpBottomCard(data[data.size - 1])
        } catch (e: JSONException) {
            Log.e(TAG, "setUpRecyclerView: JSONException occurred $e")
        }

    }

    companion object {
        private const val TAG = "MainActivity"
        private const val SHARED_PREF = "MainActivitySharedPref"
        private const val SHOW_BOTTOM_CARD = "BottomCard"
    }

}
package com.example.youtubeoynatmalistesi

import PlaylistAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val API_KEY="AIzaSyA5fXC7g3w4wTn-qP9UH9jjUE6PF5W6pA4"
    val CHANNEL_ID="UClpEUtFu_dXbrUJ6kc3QtlA"
    var gelenVeri:PlaylistData? = null
    var oynatmaListeleri:List<PlaylistData.Items> ?= null

    var myAdapter:PlaylistAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var apiInterface=ApiClient.client?.create(ApiInterface::class.java)
       var apicall= apiInterface?.tumListeleriGetir(CHANNEL_ID,API_KEY,50)
        apicall?.enqueue(object : Callback<PlaylistData>{
            override fun onResponse(call: Call<PlaylistData>?, response: Response<PlaylistData>?) {
                Log.e("BASARILI", ""+call?.request()?.url()?.toString())

                gelenVeri=response?.body()
                oynatmaListeleri=gelenVeri?.items

                myAdapter= PlaylistAdapter(oynatmaListeleri)
                recyclerviewPlaylist.adapter=myAdapter

                var myLayoutManager=LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.VERTICAL,false)
                recyclerviewPlaylist.layoutManager=myLayoutManager


                supportActionBar?.setSubtitle("Toplam Liste :"+oynatmaListeleri?.size)

                Log.e("BASARILI","TOPLAM LİSTE SAYISI"+gelenVeri?.pageInfo?.totalResults)


            }

            override fun onFailure(call: Call<PlaylistData>?, t: Throwable?) {
                Log.e("HATA", ""+t?.printStackTrace())
            }
        })

    }
}

package com.example.youtubeoynatmalistesi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    //https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId=UClpEUtFu_dXbrUJ6kc3QtlA&key={YOUR_API_KEY}
    @GET("playlists?part=snippet")
    fun  tumListeleriGetir(@Query("channelId") channelID:String, @Query("key") apiKey:String, @Query("maxResults") limit:Int):Call<PlaylistData>


}
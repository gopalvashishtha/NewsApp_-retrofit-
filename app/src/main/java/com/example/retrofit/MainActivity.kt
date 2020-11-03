package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.adapter.adapter
//import com.example.retrofit.adapter.adapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import javax.security.auth.callback.Callback
const val BASE_URL = "https://api.currentsapi.services"

class MainActivity : AppCompatActivity() {
    lateinit var countDownTimer: CountDownTimer
    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<String>()
    private var linksList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
makeAPIRequest()
    }
private fun setUpRecyclerView(){
    rv_recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    rv_recyclerView.adapter = adapter(titlesList, descList, imagesList, linksList)
}
    private fun addToList(title:String, description:String, image:String, link:String){
        titlesList.add(title)
        descList.add(description)
        imagesList.add(image)
        linksList.add(link)
    }

    private fun makeAPIRequest() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(service::class.java)

        GlobalScope.launch (Dispatchers.IO){
            try {
                val response=api.getNews()

                for(article in response.news){
                    Log.i("MainActivity","Result = $article  ")
                addToList(article .title,article.description, article.image, article.url )
            }
                withContext(Dispatchers.Main){
                    setUpRecyclerView()
                }
            }
            catch (e:Exception){
                Log.e("MainActivity", e.toString())
        }
    }

//    private fun getNews() {
//        val news = newsService.newsInstance.get("in",1)
//
//        news.enqueue(object : retrofit2.Callback<news>{
//            override fun onFailure(call: Call<news>, t: Throwable) {
//                Log.d("tag", "fail",t)
//            }
//
//            override fun onResponse(call: Call<news>, response: Response<news>) {
//               val news = response.body()
//                if(news != null){
//                    Log.d("tag", news.toString())
//                    Adapter = adapter(this@MainActivity, news.articles)
//                    newslist.adapter=Adapter
//                    newslist.layoutManager = LinearLayoutManager(this@MainActivity)
//                }
//            }
//
//        })
    }
}



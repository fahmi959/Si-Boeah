package com.capstonebangkit.siboeah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.capstonebangkit.siboeah.ui.theme.SiboeahTheme

class PencarianResepActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SiboeahTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting2("Resep")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Pencarian $name!",
        modifier = modifier
    )

//    val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://aplikasi-fahmi-chat-default-rtdb.firebaseio.com/")
//        .client(OkHttpClient())
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val firebaseService: ApiService = retrofit.create(ApiService::class.java)
//
//    val chat = Chat("Rujak Ayam")
//    val call: Call<Void> = firebaseService.postChat(chat)
//    call.enqueue(object : RetrofitCallback<Void> {
//        override fun onFailure(call: Call<Void>, t: Throwable) {
//            // Handle failure
//            t.printStackTrace()
//        }
//
//        override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {
//            // Handle response
//            if (response.isSuccessful) {
//                // Successful POST to Firebase endpoint
//                // Perform necessary actions
//            } else {
//                // Failed POST to Firebase endpoint
//                // Handle error or unsuccessful response
//            }
//        }
//    })
}
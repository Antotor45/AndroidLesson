package fr.antotor.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val postsTextView = findViewById<TextView>(R.id.postsTextView)

        val api = RetrofitClient.instance

        val call = api.getTodos()

        call.enqueue(object : Callback<List<Todos>> {
            override fun onResponse(call: Call<List<Todos>>, response: Response<List<Todos>>) {
                if (response.isSuccessful) {
                    val todos = response.body()

                    val postsText = StringBuilder()
                    todos?.forEach { todo ->
                        postsText.appendLine("Title: ${todo.title}")
                        postsText.appendLine("Completed: ${todo.completed}")
                        postsText.appendLine()
                    }

                    postsTextView.text = postsText.toString()
                } else {
                    postsTextView.text = "Error loading posts ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Todos>>, t: Throwable) {
                postsTextView.text = "Failed to fetch posts."
            }
        })
    }
}

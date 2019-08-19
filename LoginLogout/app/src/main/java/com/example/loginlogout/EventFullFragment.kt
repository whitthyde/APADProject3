package com.example.loginlogout

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.efull_fragment.*
import kotlinx.android.synthetic.main.efull_fragment.view.*
import org.json.JSONArray
import androidx.appcompat.app.AppCompatActivity
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.json.JSONObject

/**
 * Fragment representing the login screen for Shrine.
 */
class EventFullFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.efull_fragment, container, false)
        // Set an error if the password is less than 8 characters.
        view.back_to_menu.setOnClickListener({

            doAsync{

                (activity as NavigationHost).navigateTo(MenuFragment(), false)
            }

        })

        return view
    }




    private fun LogoutAttempt(): String {
        val url = "https://dea33744.ngrok.io/logout"
        val client = OkHttpClient()

/*        val json = """
            {
            "email":"${un}",
            "password":"${pw}"
            }
            """.trimIndent()
*/
        val request = Request.Builder()
            .header("User-Agent", "Android")
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        val bodystr =  response.body().string() // this can be consumed only once

        return bodystr
    }


}

package com.example.loginlogout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.joinevent_fragment.*
import kotlinx.android.synthetic.main.joinevent_fragment.view.*
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import org.jetbrains.anko.doAsync
import org.json.JSONObject

/**
 * Fragment representing the login screen for Shrine.
 */
class JoinEventFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.joinevent_fragment, container, false)
        // Set an error if the password is less than 8 characters.
        view.submit_join.setOnClickListener({
            doAsync{
                val event_id = eventidtext.getText().toString()
                val event_name = eventnametext.getText().toString()
                val user_email = Global.getUser()
                val gotresponse = JoinAttempt(event_id,event_name,user_email)   //time-consuming HTTP request
                val jsonobj = JSONObject(gotresponse)
                if(jsonobj.get("result") == eventnametext.text.toString()){(activity as NavigationHost).navigateTo(MenuFragment(), false)}
                (activity as NavigationHost).navigateTo(EventFullFragment(), false)
            }


        })

        view.back_to_menu.setOnClickListener({

            (activity as NavigationHost).navigateTo(MenuFragment(), false)

        })





        return view
    }

    private fun JoinAttempt(id:String, en:String, email: String?): String {
        val url = "http://whydeyyanp2.appspot.com/events/joinandroid"
        val client = OkHttpClient()

        val json = """
            {
            "eventname":"${en}",
            "eventid":"${id}",
            "email":"${email}"

            }
            """.trimIndent()


        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val request = Request.Builder()
            .header("User-Agent", "Android")
            .url(url)
            .post(body)
            .build()

        val response = client.newCall(request).execute()
        val bodystr =  response.body().string() // this can be consumed only once

        return bodystr
    }
}

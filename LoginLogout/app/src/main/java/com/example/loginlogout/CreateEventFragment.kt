package com.example.loginlogout

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.create_event_fragment.*
import kotlinx.android.synthetic.main.create_event_fragment.view.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import com.squareup.okhttp.Response

import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class CreateEventFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.create_event_fragment, container, false)
        // Set an error if the password is less than 8 characters.
        view.submit_button.setOnClickListener({

            doAsync {
                val eventname = eventnametext.getText().toString()
                val hostname = hostnametext.getText().toString()
                val description = descriptiontext.getText().toString()
                val date = datetext.getText().toString()
                val timeslot = timeslottext.getText().toString()
                val currentusers = currentuserstext.getText().toString()
                val maxusers = maxuserstext.getText().toString()
                val price = pricetext.getText().toString()
                val venueid = venueidtext.getText().toString()
                val gotresponse = CreateAttempt(
                    eventname,
                    hostname,
                    description,
                    date,
                    timeslot,
                    currentusers,
                    maxusers,
                    price,
                    venueid
                )
                //time-consuming HTTP request
                val jsonobj = JSONObject(gotresponse)
                if (jsonobj.get("result") == eventnametext.text.toString()) {
                    (activity as NavigationHost).navigateTo(MenuFragment(), false)
                }
            }


        })
        view.back_to_menu.setOnClickListener({
            doAsync {
                (activity as NavigationHost).navigateTo(MenuFragment(), false)
            }
        })

        return view
    }






    //OKHTTP TRY

     fun CreateAttempt(en:String,hn:String,des: String,day:String, ts: String,cu: String,mu: String,pr: String,vi: String): String {
        val url = "http://whydeyyanp2.appspot.com/events/add"
        val client = OkHttpClient()

        val json = """
            {
            "eventname":"${en}",
            "hostname":"${hn}",
            "description":"${des}",
            "day":"${day}",
            "timeslot":"${ts}",
            "currentusers":"${cu}",
            "maxusers":"${mu}",
            "price":"${pr}",
            "venueid":"${vi}"
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

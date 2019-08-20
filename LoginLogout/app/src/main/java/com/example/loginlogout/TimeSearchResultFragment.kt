package com.example.loginlogout

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.ArrayList
import androidx.appcompat.app.AppCompatActivity
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import kotlinx.android.synthetic.main.time_listing_fragment.view.*
import kotlinx.android.synthetic.main.venue_listing_fragment.view.*
import kotlinx.android.synthetic.main.venue_listing_fragment.view.done_button
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class TimeSearchResultFragment : Fragment() {
    private val jsoncode = 1

    private var timelist: ListView? = null
    private var timeArrayList: ArrayList<String>? = null
    private var timesModelArrayList: ArrayList<Time_Model>? = null
    private var TimeAdapter: TimeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.time_listing_fragment, container, false)

        view.done_button.setOnClickListener({

            // Navigate to the next Fragment.
            (activity as NavigationHost).navigateTo(MenuFragment(), false)

        })

        timelist = view.timelist
//        userModelArrayList = getInfo(response)  // uncomment this and comment the next line if response is above
        //response = loadJSONFromAssets();
        doAsync {
            try {

                var date = Global.getDateSearchTimes()
                var venueid = Global.getVenueIdSearchTimes()
                timesModelArrayList = getTimes(date,venueid)

                //resets variables after search plox
                Global.setDateSearchTimes("default")
                Global.setVenueIdSearchTimes("default")



                // Create a Custom Adapter that gives us a way to "view" each user in the ArrayList
                TimeAdapter = TimeAdapter(view.context, timesModelArrayList!!)
                // set the custom adapter for the userlist viewing
                val handler = Handler(Looper.getMainLooper());
                handler.post({
                    try {
                        timelist!!.adapter = TimeAdapter
                    } catch (e: Exception){

                    }

                })
            } catch (e: Exception) {
                println("error in list logic" + e.toString())
            }
        }

        return view;

    }



    private fun getTimes(date:String?,vi:String?): ArrayList<Time_Model>? {

        val timeModelArrayList = ArrayList<Time_Model>()
        val url = "http://whydeyyanp2.appspot.com/events/searchandroidtimes/"+date+"/"+vi+"/"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Android")
            .build()
        val response = client.newCall(request).execute()
        val bodystr =  response.body().string() // this can be consumed only once
        val dataarray = JSONArray(bodystr)
        for (i in 0 until dataarray.length()) {
            val timeModel = Time_Model()
            val dataobj = dataarray.getJSONObject(i)
            timeModel.setTimes(dataobj.getString("time"))
            timeModel.setStatuses(dataobj.getString("status"))
            timeModelArrayList?.add(timeModel)
        }

        return timeModelArrayList
    }
}

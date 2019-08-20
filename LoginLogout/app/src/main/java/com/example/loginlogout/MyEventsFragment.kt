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
import kotlinx.android.synthetic.main.my_events_fragment.view.*
import kotlinx.android.synthetic.main.my_events_fragment.*
import kotlinx.android.synthetic.main.my_events_fragment.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.ArrayList
import androidx.appcompat.app.AppCompatActivity
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import kotlinx.android.synthetic.main.my_events_fragment.view.done_button
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class MyEventsFragment : Fragment() {
    private val jsoncode = 1

    private var eventlist: ListView? = null
    private var eventArrayList: ArrayList<String>? = null
    private var eventsModelArrayList: ArrayList<Event_Model>? = null
    private var EventAdapter: EventAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.eventlisting_fragment, container, false)

        view.done_button.setOnClickListener({

            // Navigate to the next Fragment.
            (activity as NavigationHost).navigateTo(MenuFragment(), false)

        })

        eventlist = view.eventlist
//        userModelArrayList = getInfo(response)  // uncomment this and comment the next line if response is above
        //response = loadJSONFromAssets();
        doAsync {
            try {
                var email = Global.getUser()
                eventsModelArrayList = getEvents(email)
                // Create a Custom Adapter that gives us a way to "view" each user in the ArrayList
                EventAdapter = EventAdapter(view.context, eventsModelArrayList!!)
                // set the custom adapter for the userlist viewing
                val handler = Handler(Looper.getMainLooper());
                handler.post({
                    try {
                        eventlist!!.adapter = EventAdapter
                    } catch (e: Exception){

                    }

                })
            } catch (e: Exception) {
                println("error in list logic" + e.toString())
            }
        }

        return view;

    }

    fun getInfo(response: JSONArray): ArrayList<Event_Model> {
        val eventsModelArrayList = ArrayList<Event_Model>()
        try {
            val dataArray = response
            for (i in 0 until dataArray.length()) {
                val eventsModel = Event_Model()
                val dataobj = dataArray.getJSONObject(i)
                eventsModel.seteventids(dataobj.getInt("id"))
                eventsModel.setEventNames(dataobj.getString("eventname"))
                eventsModel.setDescriptions(dataobj.getString("description"))
                eventsModel.setDates(dataobj.getString("date"))
                eventsModel.setTimeslots(dataobj.getString("timeslot"))
                eventsModel.setCurrentUsers(dataobj.getString("currentusers"))
                eventsModel.setMaxUsers(dataobj.getString("maxusers"))
                eventsModel.setPrices(dataobj.getString("price"))
                eventsModel.setVenueIDs(dataobj.getInt("venueid"))
                eventsModelArrayList.add(eventsModel)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return eventsModelArrayList
    }



    private fun getEvents(un:String?): ArrayList<Event_Model>? {

        val eventModelArrayList = ArrayList<Event_Model>()
        val url = "http://whydeyyanp2.appspot.com/account/androidevents/"+un+"/"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Android")
            .build()
        val response = client.newCall(request).execute()
        val bodystr =  response.body().string() // this can be consumed only once
        val dataarray = JSONArray(bodystr)
        for (i in 0 until dataarray.length()) {
            val eventsModel = Event_Model()
            val dataobj = dataarray.getJSONObject(i)
            eventsModel.seteventids(dataobj.getInt("id"))
            eventsModel.setEventNames(dataobj.getString("eventname"))
            eventsModel.setDescriptions(dataobj.getString("description"))
            eventsModel.setDates(dataobj.getString("date"))
            eventsModel.setTimeslots(dataobj.getString("timeslot"))
            eventsModel.setCurrentUsers(dataobj.getString("currentusers"))
            eventsModel.setMaxUsers(dataobj.getString("maxusers"))
            eventsModel.setPrices(dataobj.getString("price"))
            eventsModel.setVenueIDs(dataobj.getInt("venueid"))
            eventModelArrayList?.add(eventsModel)
        }

        return eventModelArrayList
    }
}

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
import kotlinx.android.synthetic.main.event_search_fragment.view.*
import kotlinx.android.synthetic.main.event_search_fragment.*

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.ArrayList
import androidx.appcompat.app.AppCompatActivity
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class EventSearchFragment : Fragment() {
    private val jsoncode = 1
    private var eventlist: ListView? = null
    private var eventArrayList: ArrayList<String>? = null
    private var eventsModelArrayList: ArrayList<Event_Model>? = null
    private var EventAdapter: EventAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.event_search_fragment, container, false)

        view.submit_button_a.setOnClickListener({



            val eventname = eventnametexta.getText().toString()
            Global.setEventNameSearches(eventname)
            eventname.ifEmpty { Global.setEventNameSearches("default")}


            val date = datetexta.getText().toString()
            Global.setDateSearches(date)
            date.ifEmpty { Global.setDateSearches("default")}


            val timeslot = timeslottexta.getText().toString()
            Global.setTimeslotSearches(timeslot)
            timeslot.ifEmpty { Global.setTimeslotSearches("default")}


            val venue_id = venueidtexta.getText().toString()
            Global.setVenueIdSearches(venue_id)
            venue_id.ifEmpty { Global.setVenueIdSearches("default")}


            (activity as NavigationHost).navigateTo(EventSearchResultFragment(), false)


        })

        view.back_to_menu_a.setOnClickListener({
            doAsync {
                (activity as NavigationHost).navigateTo(MenuFragment(), false)
            }
        })



        return view;

    }

}

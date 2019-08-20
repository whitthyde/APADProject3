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
import kotlinx.android.synthetic.main.venue_search_fragment.view.*

import kotlinx.android.synthetic.main.time_search_fragment.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class TimeSearchFragment : Fragment() {
    private val jsoncode = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.time_search_fragment, container, false)

        view.submit_button_b.setOnClickListener({

            val date = datetextc.getText().toString()
            Global.setDateSearchTimes(date)
            date.ifEmpty { Global.setDateSearchTimes("default")}


            val timeslot = venueidtextc.getText().toString()
            Global.setVenueIdSearchTimes(timeslot)
            timeslot.ifEmpty { Global.setVenueIdSearchTimes("default")}

            (activity as NavigationHost).navigateTo(TimeSearchResultFragment(), false)

        })

        view.back_to_menu_b.setOnClickListener({
            doAsync {
                (activity as NavigationHost).navigateTo(MenuFragment(), false)
            }
        })



        return view;

    }

}

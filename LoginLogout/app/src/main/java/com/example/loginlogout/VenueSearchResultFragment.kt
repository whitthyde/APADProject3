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
import kotlinx.android.synthetic.main.venue_listing_fragment.view.*
import kotlinx.android.synthetic.main.venue_listing_fragment.view.done_button
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class VenueSearchResultFragment : Fragment() {
    private val jsoncode = 1

    private var venuelist: ListView? = null
    private var venueArrayList: ArrayList<String>? = null
    private var venuesModelArrayList: ArrayList<Venue_Model>? = null
    private var VenueAdapter: VenueAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.venue_listing_fragment, container, false)

        view.done_button.setOnClickListener({

            // Navigate to the next Fragment.
            (activity as NavigationHost).navigateTo(MenuFragment(), false)

        })

        venuelist = view.venuelist
//        userModelArrayList = getInfo(response)  // uncomment this and comment the next line if response is above
        //response = loadJSONFromAssets();
        doAsync {
            try {

                var date = Global.getDateSearchVens()
                var times = Global.getTimeslotSearchVens()
                venuesModelArrayList = getVenues(date,times)

                //resets variables after search plox
                Global.setDateSearchVens("default")
                Global.setTimeslotSearchVens("default")



                // Create a Custom Adapter that gives us a way to "view" each user in the ArrayList
                VenueAdapter = VenueAdapter(view.context, venuesModelArrayList!!)
                // set the custom adapter for the userlist viewing
                val handler = Handler(Looper.getMainLooper());
                handler.post({
                    try {
                        venuelist!!.adapter = VenueAdapter
                    } catch (e: Exception){

                    }

                })
            } catch (e: Exception) {
                println("error in list logic" + e.toString())
            }
        }

        return view;

    }



    private fun getVenues(date:String?,ts:String?): ArrayList<Venue_Model>? {

        val venueModelArrayList = ArrayList<Venue_Model>()
        val url = "https://66fd7640.ngrok.io/events/searchandroidvenues/"+date+"/"+ts+"/"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Android")
            .build()
        val response = client.newCall(request).execute()
        val bodystr =  response.body().string() // this can be consumed only once
        val dataarray = JSONArray(bodystr)
        for (i in 0 until dataarray.length()) {
            val venueModel = Venue_Model()
            val dataobj = dataarray.getJSONObject(i)
            venueModel.setVenueids(dataobj.getInt("id"))
            venueModel.setVenuenames(dataobj.getString("venuename"))

            venueModelArrayList?.add(venueModel)
        }

        return venueModelArrayList
    }
}

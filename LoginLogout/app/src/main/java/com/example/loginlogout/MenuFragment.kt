package com.example.loginlogout

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.menu_fragment.*
import kotlinx.android.synthetic.main.menu_fragment.view.*
import kotlinx.android.synthetic.main.menu_fragment.view.*
import org.json.JSONArray
import androidx.appcompat.app.AppCompatActivity
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.json.JSONObject

/**
 * Fragment representing the login screen for Shrine.
 */
class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.menu_fragment, container, false)
        // Set an error if the password is less than 8 characters.
        view.createevent_button.setOnClickListener({

            (activity as NavigationHost).navigateTo(CreateEventFragment(), false)

        })

        view.join_event_button.setOnClickListener({

            (activity as NavigationHost).navigateTo(JoinEventFragment(), false)

        })

        view.allevents_button.setOnClickListener({

            (activity as NavigationHost).navigateTo(EventListingFragment(), false)

        })
        view.myeventsbutton.setOnClickListener({

            (activity as NavigationHost).navigateTo(MyEventsFragment(), false)

        })
        view.searcheventsbutton.setOnClickListener({

                (activity as NavigationHost).navigateTo(EventSearchFragment(), false)

        })

        view.venuesearch_button.setOnClickListener({

            (activity as NavigationHost).navigateTo(VenueSearchFragment(), false)

        })

        view.timeslotsearch_button.setOnClickListener({

            (activity as NavigationHost).navigateTo(TimeSearchFragment(), false)

        })

        view.logout_button.setOnClickListener({

            (activity as NavigationHost).navigateTo(LogoutFragment(), false)

        })


        return view
    }

    // "isPasswordValid"  method goes here
    // Currently checks for 8 characters but we could perform
    // an actual validation with a remote service like the Web version below
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }

    private fun isPasswordValidWeb(text: Editable?): Boolean {
        return true
    }

}

package com.example.loginlogout

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import com.squareup.okhttp.Response
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import com.android.volley.DefaultRetryPolicy
//import com.android.volley.Request
//import com.android.volley.Response
//import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import java.security.KeyStore




/**
 * Fragment representing the login screen for Shrine.
 */
class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        // Set an error if the password is less than 8 characters.
        view.next_button.setOnClickListener({

                // Clear the error.
                password_text_input.error = null
                // Navigate to the next Fragment.

                doAsync{
                    val useremail = emailtext.getText().toString()
                    Global.setUser(useremail)
                    val userpass = password_edit_text.getText().toString()
                    val gotresponse = LoginAttempt(useremail,userpass)   //time-consuming HTTP request
                    val jsonobj = JSONObject(gotresponse)
                    if(jsonobj.get("email") == emailtext.text.toString()){(activity as NavigationHost).navigateTo(MenuFragment(), false)}
                }



        })
        view.signup_button.setOnClickListener({
            doAsync{
                    (activity as NavigationHost).navigateTo(SignupFragment(), false)
                    }
        })

        // Clear the error once more than 8 characters are typed.
        view.password_edit_text.setOnKeyListener({ _, _, _ ->
            if (isPasswordValid(password_edit_text.text!!)) {
                // Clear the error.
                password_text_input.error = null
            }
            false
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






    //OKHTTP TRY

    private fun LoginAttempt(un: String, pw: String): String {
        val url = "http://whydeyyanp2.appspot.com/login/auth/"+un+"/"+pw
        val client = OkHttpClient()

/*        val json = """
            {
            "email":"${un}",
            "password":"${pw}"
            }
            """.trimIndent()
*/
        val formtxt = "email:{},password:{}".format(un, pw)
        val body = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), formtxt)
        val request = Request.Builder()
            .header("User-Agent", "Android")
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        val bodystr =  response.body().string() // this can be consumed only once

        return bodystr
    }




}

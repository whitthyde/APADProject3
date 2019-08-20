package com.example.loginlogout

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.signup_fragment.*
import kotlinx.android.synthetic.main.signup_fragment.view.*
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

class SignupFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.signup_fragment, container, false)
        // Set an error if the password is less than 8 characters.
        view.submit_button.setOnClickListener({

                doAsync{
                    val firstname = firstnametext.getText().toString()
                    val lastname = lastnametext.getText().toString()
                    val useremail = emailtext.getText().toString()
                    val username = usernametext.getText().toString()
                    val userpass = password_edit_text.getText().toString()
                    val gotresponse = SignupAttempt(firstname,lastname,username,useremail,userpass)   //time-consuming HTTP request
                    val jsonobj = JSONObject(gotresponse)
                    if(jsonobj.get("result") == emailtext.text.toString()){(activity as NavigationHost).navigateTo(LoginFragment(), false)}
                }



        })
        view.back_to_login.setOnClickListener({
            doAsync{
                (activity as NavigationHost).navigateTo(LoginFragment(), false)
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

    private fun SignupAttempt(fn:String,ln:String,un: String,email:String, pw: String): String {
        val url = "http://whydeyyanp2.appspot.com/signup/"
        val client = OkHttpClient()

    val json = """
            {
            "firstname":"${fn}",
            "lastname":"${ln}",
            "username":"${un}",
            "email":"${email}",
            "password":"${pw}"
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

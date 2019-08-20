package com.example.loginlogout

class Time_Model {

    var time: String? = null
    var status: String? = null

    fun getTimes(): String {
        return time.toString()
    }

    fun setTimes(str: String) {
        this.time = str
    }

    fun getStatuses(): String {
        return status.toString()
    }

    fun setStatuses(str: String) {
        this.status = str
    }

}
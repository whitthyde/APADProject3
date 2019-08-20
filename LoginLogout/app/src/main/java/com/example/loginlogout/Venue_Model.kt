package com.example.loginlogout

class Venue_Model {

    var venuename: String? = null
    var venueid: Int? = null

    fun getVenuenames(): String {
        return venuename.toString()
    }

    fun setVenuenames(name: String) {
        this.venuename = name
    }

    fun getVenueids(): String {
        return venueid.toString()
    }

    fun setVenueids(name: Int) {
        this.venueid = name
    }

}
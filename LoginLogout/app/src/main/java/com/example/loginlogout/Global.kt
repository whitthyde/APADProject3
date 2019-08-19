package com.example.loginlogout

object Global {
    //for login, logout, my events
    private var user: String? = null

    //variables for event search
    private var eventnamesearch: String? = null
    private var datesearch: String? = null
    private var timeslotsearch: String? = null
    private var venueidsearch: String? = null

    //search times available at certain venue

    private var datesearchtime: String? = null

    private var venueidsearchtime: String? = null



    //display venues at certain time
    private var datesearchven: String? = null
    private var timeslotsearchven: String? = null



    //for loging,logout,myevents
    fun getUser(): String? {
        return user
    }

    fun setUser(str: String) {
        user = str
    }


    //for event search
    fun getEventNameSearches(): String? {
        return eventnamesearch
    }

    fun setEventNameSearches(str: String) {
        eventnamesearch = str
    }
    fun getDateSearches(): String? {
        return datesearch
    }

    fun setDateSearches(str: String) {
        datesearch = str
    }
    fun getTimeslotSearches(): String? {
        return timeslotsearch
    }
    fun setTimeslotSearches(str: String) {
        timeslotsearch = str
    }
    fun getVenueIdSearches(): String? {
        return venueidsearch
    }
    fun setVenueIdSearches(str: String) {
        venueidsearch = str
    }



    //Variables for searching venues at a certain time
    fun getDateSearchTimes(): String? {
        return datesearchtime
    }
    fun setDateSearchTimes(str: String) {
        datesearchtime = str
    }
    fun getVenueIdSearchTimes(): String? {
        return venueidsearchtime
    }
    fun setVenueIdSearchTimes(str: String) {
        venueidsearchtime = str
    }

    //FINAL METHODS FOR Displaying Venues at a certain time
    fun getDateSearchVens(): String? {
        return datesearchven
    }
    fun setDateSearchVens(str: String) {
        datesearchven = str
    }
    fun getTimeslotSearchVens(): String? {
        return timeslotsearchven
    }
    fun setTimeslotSearchVens(str: String) {
        timeslotsearchven = str
    }


}
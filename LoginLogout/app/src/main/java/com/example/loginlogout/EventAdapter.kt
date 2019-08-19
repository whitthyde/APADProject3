package com.example.loginlogout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList


class EventAdapter(private val context: Context, private val eventsModelArrayList: ArrayList<Event_Model>) :
    BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return eventsModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return eventsModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.event, null, true)

            holder.eventid = convertView!!.findViewById(R.id.eventid) as TextView
            holder.eventname = convertView!!.findViewById(R.id.eventname) as TextView
            holder.hostname = convertView.findViewById(R.id.hostname) as TextView
            holder.description = convertView.findViewById(R.id.description) as TextView
            holder.day = convertView.findViewById(R.id.day) as TextView
            holder.timeslot = convertView.findViewById(R.id.timeslot) as TextView
            holder.currentusers = convertView.findViewById(R.id.currentusers) as TextView
            holder.maxusers = convertView.findViewById(R.id.maxusers) as TextView
            holder.price = convertView.findViewById(R.id.price) as TextView
            holder.venueid = convertView.findViewById(R.id.venueid) as TextView


            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.eventid!!.text = "Event ID: " + eventsModelArrayList[position].geteventids()
        holder.eventname!!.text = "Event Name: " + eventsModelArrayList[position].getEventNames()
        holder.hostname!!.text = "Host Name: " + eventsModelArrayList[position].getHostNames()
        holder.description!!.text = "Description: " + eventsModelArrayList[position].getDescriptions()
        holder.day!!.text = "Date: " + eventsModelArrayList[position].getDates()
        holder.timeslot!!.text = "Timeslot: " + eventsModelArrayList[position].getTimeslots()
        holder.currentusers!!.text = "Current Users: " + eventsModelArrayList[position].getCurrentUsers()
        holder.maxusers!!.text = "Max Users: " + eventsModelArrayList[position].getMaxUsers()
        holder.price!!.text = "Price: " + eventsModelArrayList[position].getPrices()
        holder.venueid!!.text = "VenueID: " + eventsModelArrayList[position].getVenueIDs()

        return convertView
    }

    private inner class ViewHolder {

        var eventid: TextView? = null
        var eventname: TextView? = null
        var hostname: TextView? = null
        var description: TextView? = null
        var day: TextView? = null
        var timeslot: TextView? = null
        var currentusers: TextView? = null
        var maxusers: TextView? = null
        var price: TextView? = null
        var venueid: TextView? = null

    }

}
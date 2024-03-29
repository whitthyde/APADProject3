package com.example.loginlogout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList


class VenueAdapter(private val context: Context, private val venuesModelArrayList: ArrayList<Venue_Model>) :
    BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return venuesModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return venuesModelArrayList[position]
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
            convertView = inflater.inflate(R.layout.venue, null, true)

            holder.venuename = convertView!!.findViewById(R.id.venuename) as TextView
            holder.venueid = convertView.findViewById(R.id.venueid) as TextView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.venuename!!.text = "Venue Name: " + venuesModelArrayList[position].getVenuenames()
        holder.venueid!!.text = "Venue ID: " + venuesModelArrayList[position].getVenueids()

        return convertView
    }

    private inner class ViewHolder {

        var venuename: TextView? = null
        var venueid: TextView? = null
    }

}
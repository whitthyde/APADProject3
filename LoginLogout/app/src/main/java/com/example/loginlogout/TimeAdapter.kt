package com.example.loginlogout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList

/**
 * Orignial author: Parsania Hardik on 03-Jan-17.
 * Modified by Ramesh Yerraballi on 8/12/19
 */
class TimeAdapter(private val context: Context, private val timeModelArrayList: ArrayList<Time_Model>) :
    BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return timeModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return timeModelArrayList[position]
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
            convertView = inflater.inflate(R.layout.time, null, true)

            holder.time = convertView!!.findViewById(R.id.time) as TextView
            holder.status = convertView.findViewById(R.id.status) as TextView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.time!!.text = "Time: " + timeModelArrayList[position].getTimes()
        holder.status!!.text = "Status: " + timeModelArrayList[position].getStatuses()

        return convertView
    }

    private inner class ViewHolder {

        var time: TextView? = null
        var status: TextView? = null
    }

}
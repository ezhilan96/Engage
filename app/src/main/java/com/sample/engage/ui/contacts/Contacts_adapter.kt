package com.sample.engage.ui.contacts

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.engage.R
import kotlin.random.Random

class Contacts_adapter : ListAdapter<String, Contacts_adapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var name: TextView = view.findViewById(R.id.contact_name)
        var initial: TextView = view.findViewById(R.id.initial)
        var initialBg: CardView = view.findViewById(R.id.initialCardView)
        fun bind(position: Int){
            val contactName = getItem(position)
            name.text = contactName
            initial.text = "${contactName.first()}"
            val rnd = Random
            val bubbleColor: Int = Color.argb(125, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            initialBg.setCardBackgroundColor(bubbleColor)
            initial.setTextColor(ContrastColor(Color.valueOf(bubbleColor)))
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}

fun ContrastColor(color: Color): Int {
    var d = 0

    // Counting the perceptive luminance - human eye favors green color...
    val luminance: Double = (0.299 * color.red() + 0.587 * color.green()  + 0.114 * color.blue() ) / 255

    d = if (luminance > 0.5) 0 // bright colors - black font
    else 255 // dark colors - white font


    return Color.argb(255, d,d,d)
}
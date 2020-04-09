package com.sample.engage.ui.contacts

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
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.contact_name)
        var initial: TextView = view.findViewById(R.id.initial)
        var initialBg: CardView = view.findViewById(R.id.initialCardView)
        fun bind(position: Int) {
            val contactName = getItem(position)
            name.text = contactName
            initial.text = "${contactName.first()}"
            val rnd = Random
            val bubbleColor = Color.argb(.25F, Random.nextFloat(), Random.nextFloat(), Random.nextFloat())
            initialBg.setCardBackgroundColor(bubbleColor)
            initial.setTextColor(getContrastColor(bubbleColor))
            if(position%2==0)
                name.setBackgroundColor(Color.parseColor("#F9F9F9"))
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

fun getContrastColor(color: Int): Int {
    var d = 127

    // Counting the perceptive luminance - human eye favors green color...
    val luminance =
        1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
    if (luminance < 0.5)
        d = 255

    return Color.argb(255, d, d, d)
}
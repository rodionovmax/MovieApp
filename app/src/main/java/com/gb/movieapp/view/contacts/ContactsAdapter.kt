package com.gb.movieapp.view.contacts

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.gb.movieapp.R
import com.gb.movieapp.model.Contact
import kotlinx.android.synthetic.main.fragment_contacts_recyclerview_item.view.*

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.RecyclerItemViewHolder>(){

    private var data: List<Contact> = arrayListOf()

    fun setData(data: List<Contact>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactsAdapter.RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_contacts_recyclerview_item, parent, false) as View

        )
    }

    override fun onBindViewHolder(holder: ContactsAdapter.RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Contact) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                with(itemView) {
                    contact_name.text = data.name
                }
            }
        }
    }


    private fun makeCall(context : Context, contactNumber : String) {

        val callIntent = Intent(Intent.ACTION_CALL)

        callIntent.data = Uri.parse("tel:$contactNumber")

        // on below line we are checking if the calling permissions are granted or not.
        if (context.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.CALL_PHONE
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        // TODO: Uncomment
//        startActivity(callIntent)
    }
}



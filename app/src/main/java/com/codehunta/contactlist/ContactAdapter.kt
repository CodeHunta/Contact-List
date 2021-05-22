package com.codehunta.contactlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codehunta.contactlist.databinding.ContactlistItemBinding

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private val contacts = mutableListOf<Contact>()

    inner class ContactViewHolder(private val binding: ContactlistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(contact: Contact) {
            binding.imgViewPix.setImageResource(R.drawable.ic_img_pix)
            binding.txtName.text = contact.name
            binding.txtNumber.text = contact.number
        }
    }

    fun setupContacts(contacts: List<Contact>) {
        this.contacts.addAll(contacts)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ContactlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bindItem(contact)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}
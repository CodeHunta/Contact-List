package com.codehunta.contactlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.codehunta.contactlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupData(binding)
    }

    private fun setupData(binding: ActivityMainBinding) {
        binding.recyclerContact.adapter = adapter
        binding.recyclerContact.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        val builder = AlertDialog.Builder(this)
        this.layoutInflater
        val view = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        builder.setView(view)

        val contactName = view.findViewById<TextView>(R.id.EdInputName)
        val contactNumber = view.findViewById<TextView>(R.id.EdInputNumber)
        val saveButton = view.findViewById<Button>(R.id.materialBtnSave)
        contactNumber.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                saveButton.isEnabled = s?.length == 11
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        val alertDialog = builder.create()
        saveButton.setOnClickListener {
            val contact = Contact(contactName.text.toString(), contactNumber.text.toString())
            val contacts = mutableListOf(contact)
            adapter.setupContacts(contacts)
            alertDialog.dismiss()
        }

        binding.materialFab.setOnClickListener {
            alertDialog.show()
        }
    }
}
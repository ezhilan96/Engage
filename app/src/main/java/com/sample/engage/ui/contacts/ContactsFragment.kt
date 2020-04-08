package com.sample.engage.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.engage.R

class ContactsFragment : Fragment() {

    private lateinit var contactsViewModel: ContactsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        contactsViewModel =
            ViewModelProviders.of(this).get(ContactsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_contacts, container, false)
        val contactsRecyclerView: RecyclerView = root.findViewById(R.id.contatcs_rv)
        contactsRecyclerView.layoutManager = LinearLayoutManager(context!!)
        val contactsAdapter = Contacts_adapter()
        contactsRecyclerView.adapter = contactsAdapter
        contactsAdapter.submitList(
            listOf(
                "Arthur Fleck",
                "Eric Lehnserr",
                "Ra's Al Ghul",
                "Zod",
                "Pamela Lillian Isley",
                "Eddie Brock",
                "Victor Van Doom",
                "Harvey Dent"
            )
        )

//        contactsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}

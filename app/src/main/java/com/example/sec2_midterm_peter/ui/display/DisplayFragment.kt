package com.example.sec2_midterm_peter.ui.display

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sec2_midterm_peter.R
import com.example.sec2_midterm_peter.databinding.FragmentDisplayBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.ln

class DisplayFragment : Fragment() {

    private lateinit var dashboardViewModel: DisplayViewModel
    private var _binding: FragmentDisplayBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun getDataFromDB() {
        val db = FirebaseFirestore.getInstance()
        db.collection("data").limit(1).get().addOnSuccessListener {res ->
            for (document in res) {

                var listView = view?.findViewById<ListView>(R.id.listViewDisplay)
                val arrayAdapter = ArrayAdapter(context, android.R.layout.activity_list_item, document.)
                return@addOnSuccessListener
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DisplayViewModel::class.java)

        view?.findViewById<Button>(R.id.DisplayBtn)?.setOnClickListener {
            getDataFromDB()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


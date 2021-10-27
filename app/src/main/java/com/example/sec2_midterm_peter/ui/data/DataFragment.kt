package com.example.sec2_midterm_peter.ui.data

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sec2_midterm_peter.API.DataAPIItem
import com.example.sec2_midterm_peter.APIInterface
import com.example.sec2_midterm_peter.R
import com.example.sec2_midterm_peter.databinding.FragmentDataBinding
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DataFragment : Fragment() {

    private lateinit var homeViewModel: DataViewModel
    private var _binding: FragmentDataBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(APIInterface::class.java)
    }

    private fun getAndStoreData() {
        val db = FirebaseFirestore.getInstance()
        val newUser : MutableMap<String, Any> = HashMap()
        val jsonData = retrofitBuilder.getAPIData()
        jsonData.enqueue(object: Callback<List<DataAPIItem>?> {
            override fun onResponse(
                call: Call<List<DataAPIItem>?>,
                response: Response<List<DataAPIItem>?>
            ) {
                val responseRet =  response.body()!!
                newUser["data"] = responseRet
                db.collection("data")
                    .add(newUser)
                    .addOnSuccessListener {
                        Log.d("dbfirebase", "save:")
                        Toast.makeText(activity, "Data succesfully store !", Toast.LENGTH_SHORT).show()
                        return@addOnSuccessListener
                    }
                    .addOnCanceledListener {
                    }
                for (data in responseRet){
                    println(data)
                }
            }

            override fun onFailure(call: Call<List<DataAPIItem>?>, t: Throwable) {
                Log.d("APIFAIL","message" + t.message)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.apiCall).setOnClickListener {
            getAndStoreData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(DataViewModel::class.java)

        _binding = FragmentDataBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
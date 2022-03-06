package com.example.launchmodes.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.launchmodes.R
import com.example.launchmodes.activities.UserDataActivity

class FormFragment : Fragment() {

    companion object {
        fun newInstance() = FormFragment()
    }

    private lateinit var viewModel: FormViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.blank_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as UserDataActivity).supportActionBar?.title = getString(R.string.form_title)
        viewModel = ViewModelProvider(this).get(FormViewModel::class.java)
        val name = view.findViewById<EditText>(R.id.name)
        val empId = view.findViewById<EditText>(R.id.empid)
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)
        activity?.let { viewModel.storeFormData(name, empId, email, password, it, view) }

    }


}
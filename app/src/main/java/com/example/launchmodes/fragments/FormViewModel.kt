package com.example.launchmodes.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.launchmodes.R
import com.example.launchmodes.helper.DataFlairProvider

class FormViewModel : ViewModel() {
    var isSaved: Boolean = false
    fun storeFormData(
        name: EditText,
        empId: EditText,
        email: EditText,
        password: EditText,
        context: Activity,
        view: View
    ) {
        val submit = view.findViewById<Button>(R.id.submit)
        submit.setOnClickListener {
            if (name.text.toString().isEmpty() || empId.text.toString()
                    .isEmpty() || email.text.toString().isEmpty() || password.text.toString()
                    .isEmpty()
            ) {
                isSaved = false
                Toast.makeText(context, "Please fill all details", Toast.LENGTH_SHORT).show()
            } else {
                val values = ContentValues()
                values.put(DataFlairProvider.name, name.text.toString())
                values.put(DataFlairProvider.empid, empId.text.toString())
                values.put(DataFlairProvider.email, email.text.toString())
                values.put(DataFlairProvider.password, password.text.toString())
                context.contentResolver.insert(DataFlairProvider.CONTENT_URI, values)
                isSaved = true

                val builder = AlertDialog.Builder(context)
                builder.setTitle(R.string.dialogTitle)
                builder.setMessage(R.string.dialogMessage)
                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    name.text.clear()
                    email.text.clear()
                    empId.text.clear()
                    password.text.clear()
                    context.sendBroadcast(Intent(DataFlairProvider.PROVIDER_NAME))
                }

                builder.show()

                // creating a cursor object of the
                // content URI
                val cursor = context.contentResolver.query(
                    Uri.parse(DataFlairProvider.URL),
                    null,
                    null,
                    null,
                    null
                )
            }

        }
    }
}

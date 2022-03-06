package com.example.launchmodes.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.launchmodes.R
import com.example.launchmodes.activities.UserDataActivity
import com.example.launchmodes.helper.DataFlairProvider
import com.example.launchmodes.helper.DataFlairProvider.Companion.CONTENT_URI

class UserDataFragment : Fragment() {

    companion object {
        fun newInstance() = UserDataFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_user_data, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as UserDataActivity).supportActionBar?.title = getString(R.string.data_access)
        val detailsButton = view.findViewById<Button>(R.id.button_details)
        val count = view.findViewById<TextView>(R.id.textView_count)
        val edit_count = view.findViewById<EditText>(R.id.editText)
        detailsButton.setOnClickListener {
            // creating a cursor object of the
            // content URI
            val cursor = context?.contentResolver?.query(
                CONTENT_URI,
                null,
                null,
                null,
                null
            )

            var hashmap = HashMap<String, Int>()
            if (cursor!!.moveToFirst()) {
                while (!cursor.isAfterLast) {
                    var res = cursor.getString(cursor.getColumnIndex("empid"))
                    if (hashmap.containsKey(res)) {
                        hashmap[res] = hashmap[res]?.plus(1)!!
                    } else {
                        hashmap[res] = 1
                    }

                    cursor.moveToNext()
                }
            } else {
                count.text = "No Result Found"
            }
            count.visibility = View.VISIBLE
            count.text = "This user has registered  ${hashmap[edit_count.text.toString()]} time(s)"
            edit_count.clearFocus()
            edit_count.text.clear()
        }
    }

}
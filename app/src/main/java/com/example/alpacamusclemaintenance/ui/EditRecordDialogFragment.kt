package com.example.alpacamusclemaintenance.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.example.alpacamusclemaintenance.R
import kotlinx.android.synthetic.main.activity_main.*

class EditRecordDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
//            val view = layoutInflater.inflate(R.layout.dialog_edit_record, null)
            builder.setView(R.layout.dialog_edit_record)
                    .setTitle(R.string.dialog_edit_record)
                    .setPositiveButton(R.string.save) { dialog, id ->
                        Log.d("Foo", "foo")
                    }
                    .setNegativeButton(R.string.cancel) { dialog, id ->
                        Log.d("Foo", "bar")
                    }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

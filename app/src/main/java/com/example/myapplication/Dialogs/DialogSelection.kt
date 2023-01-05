package com.example.myapplication.Dialogs

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.R

class DialogSelection {
    val messageFlag:Boolean = false

    fun showInputDialog(activity: Activity?, message: String?) {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_layout)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val textdata = dialog.findViewById<TextView>(R.id.apimessageonfailureid)
        val dialogBtn_remove = dialog.findViewById<TextView>(R.id.txtbtnyesid)
        textdata.text = message?.toString()
        dialogBtn_remove.setOnClickListener {
            dialog.dismiss()
            //activity.finish()
        }
        dialog.show()
    }
}
package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.TimePicker
import com.example.myapplication.Dialogs.DialogSelection
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    private var name:String? = null
    private var birthdate:String? = null
    private var birthtime:String? = null
    private var birthplace:String? = null
    private var height:String? = null
    private var gotra:String? = null
    private var sign:String? = null
    private var bloodgroup:String? = null
    private var contact:String? = null
    private var occupation:String? = null
    private var occupationFather:String? = null
    private var brother:String? = null
    private var sister:String? = null
    private var mamas:String? = null
    private var relative:String? = null
    private var address:String? = null
    private var map:HashMap<String,String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        //welcome Dialog

        DialogSelection().showInputDialog(this,"Namaste")
        startActivity(Intent(this,BiodataActivity::class.java))

        etBirthdate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                etBirthdate.setText(day.toString()+"/"+month.toString()+"/"+year.toString())

            }, year, month+1, day)

            dpd.show()
        }

        val mTimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        mTimePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                etBirthtime.setText(String.format("%d : %d", hourOfDay, minute))
            }
        }, hour, minute, false)


        etBirthtime.setOnClickListener { mTimePicker.show() }

        btnSubmit.setOnClickListener {
            name = etName.text.toString()
            if (name.equals(null)||name.equals("")){
                etNameLayout.error = "Please insert Person Name"
                etNameLayout.isFocusableInTouchMode = true
                etNameLayout.requestFocus();
            }else{
            //okay
            }
            birthdate = etBirthdate.text.toString()
            if (birthdate.equals(null)||birthdate.equals("")){
               etBirthDateLayout.error = "Birthdate is essential"
                etBirthDateLayout.isFocusableInTouchMode = true
                etBirthDateLayout.requestFocus();
            }else{
                //okay
            }
            birthtime = etBirthtime.text.toString()
            if (birthtime.equals(null)||birthtime.equals("")){
                etBirthtimeLayout.error = "Birthtime is essential"
                etBirthtimeLayout.isFocusableInTouchMode = true
                etBirthtimeLayout.requestFocus();
            }else{
                //okay
            }
            birthplace = etBirthplace.text.toString()
            if (birthplace.equals(null)||birthplace.equals("")){
                etBirthplaceLayout.error = "Birthplace is essential"
                etBirthplaceLayout.isFocusableInTouchMode = true
                etBirthplaceLayout.requestFocus();
            }else{
                //okay
            }
            height = etHeight.text.toString()
            if (height.equals(null)||height.equals("")){
                etBirthplaceLayout.error = "Please insert Height"
                etBirthplaceLayout.isFocusableInTouchMode = true
                etBirthplaceLayout.requestFocus()
            }else{
                //okay
            }
            gotra = etGotra.text.toString()
            if (gotra.equals(null)||gotra.equals("")){
                etGotraLayout.error = "Please Select Gotra"
                etGotraLayout.isFocusableInTouchMode = true
                etGotraLayout.requestFocus()
            }else{
                //okay
            }
            sign = etSign.text.toString()
            if (sign.equals(null)||sign.equals("")){
                etsignLayout.error = "Please Select Zodaic Sign"
                etsignLayout.isFocusableInTouchMode = true
                etsignLayout.requestFocus()
            }else{
                //okay
            }
            bloodgroup = etBloodGroup.text.toString()
            if (bloodgroup.equals(null)||bloodgroup.equals("")){
                etBloodGroupLayout.error = "Please Select Blood Group"
                etBloodGroupLayout.isFocusableInTouchMode = true
                etBloodGroupLayout.requestFocus()
            }else{
                //okay
            }
            contact = etContact.text.toString()
            if (contact.equals(null)||contact.equals("")){
                etBloodGroupLayout.error = "Please Select Blood Group"
                etBloodGroupLayout.isFocusableInTouchMode = true
                etBloodGroupLayout.requestFocus()
            }else{
                //okay
            }
            occupation = etOccupationfather.text.toString()
            if (occupation.equals(null)||occupation.equals("")){
                etOccupationfatherLayout.error = "Please insert Occupation"
                etOccupationfatherLayout.isFocusableInTouchMode = true
                etOccupationfatherLayout.requestFocus()
            }else{
                //okay
            }
            occupationFather = etOccupationfather.text.toString()
            if (occupationFather.equals(null)||occupationFather.equals("")){
                etOccupationfatherLayout.error = "Please insert Occupation"
                etOccupationfatherLayout.isFocusableInTouchMode = true
                etOccupationfatherLayout.requestFocus()
            }else{
                //okay
            }
            brother = etBrother.text.toString()
            if (brother.equals(null)||brother.equals("")){
                etBrotherLayout.error = "Please insert brother"
                etBrotherLayout.isFocusableInTouchMode = true
                etBrotherLayout.requestFocus()
            }else{
                //okay
            }
            sister = etSister.text.toString()
            if (sister.equals(null)||sister.equals("")){
                etSisterLayout.error = "Please insert Sister"
                etSisterLayout.isFocusableInTouchMode = true
                etSisterLayout.requestFocus()
            }else{
                //okay
            }
            mamas = etMamas.text.toString()
            if (mamas.equals(null)||mamas.equals("")){
                etMamasLayout.error = "Please insert MaMa's"
                etMamasLayout.isFocusableInTouchMode = true
                etMamasLayout.requestFocus()
            }else{
                //okay
            }
            relative = etRelative.text.toString()
            if (relative.equals(null)||relative.equals("")){
                etRelativeLayout.error = "Please insert Relatives"
                etRelativeLayout.isFocusableInTouchMode = true
                etRelativeLayout.requestFocus()
            }else{
                //okay
            }
            address = etAddress.text.toString()
            if (address.equals(null)||address.equals("")){
                etAddressLayout.error = "Please insert Address"
                etAddressLayout.isFocusableInTouchMode = true
                etAddressLayout.requestFocus()
            }else{
                //okay
            }

            if (!name.equals("")&&
                !birthdate.equals("")&&
                !birthtime.equals("")&&
                !birthplace.equals("")&&
                !height.equals("")&&
                !gotra.equals("")&&
                !sign.equals("")&&
                !bloodgroup.equals("")&&
                !contact.equals("")&&
                !occupation.equals("")&&
                !occupationFather.equals("")&&
                !brother.equals("")&&
                !sister.equals("")&&
                !mamas.equals("")&&
                !relative.equals("")){
                val i = Intent(this,BiodataActivity::class.java)
                i.putExtra("name",name)
                i.putExtra("birthdate",birthdate)
                i.putExtra("birthtime",birthtime)
                i.putExtra("birthplace",birthplace)
                i.putExtra("height",height)
                i.putExtra("gotra",gotra)
                i.putExtra("sign",sign)
                i.putExtra("bloodgroup",bloodgroup)
                i.putExtra("contact",contact)
                i.putExtra("occupation",occupation)
                i.putExtra("occupationFather",occupationFather)
                i.putExtra("brother",brother)
                i.putExtra("sister",sister)
                i.putExtra("mamas",mamas)
                i.putExtra("relative",relative)
                i.putExtra("address",address)
                startActivity(i)
                finish()
            }

        }
    }
    private fun submitAllData(){

    }
}
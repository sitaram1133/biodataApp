package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.android.synthetic.main.activity_biodata.*
import java.io.*
import java.util.*


class BiodataActivity : AppCompatActivity() {
    var dirpath:String =""
    private var button: Button? = null
    private var imageview: ImageView? = null
    private val GALLERY = 1
    private val CAMERA = 2



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biodata)



        txtName.text = intent.getStringExtra("name")
        txtBirthdate.text = intent.getStringExtra("birthdate")
        txtBirthtime.text = intent.getStringExtra("birthtime")
        txtBirthplace.text = intent.getStringExtra("birthplace")
        txtHeight.text = intent.getStringExtra("height")
        txtxGotra.text = intent.getStringExtra("gotra")
        txtSign.text = intent.getStringExtra("sign")
        txtBloodgroup.text = intent.getStringExtra("bloodgroup")
        txtContact.text = intent.getStringExtra("contact")
        txtOccupation.text = intent.getStringExtra("occupation")
        txtOccupation.text = intent.getStringExtra("occupationFather")
        txtBrother.text = intent.getStringExtra("brother")
        txtsister.text = intent.getStringExtra("sister")
        txtmamas.text = intent.getStringExtra("mamas")
        txtRelative.text = intent.getStringExtra("relative")
        txtAddress.text = intent.getStringExtra("address")

        imvPhoto.setOnClickListener {
            showPictureDialog()
        }

        downloadbtn.setOnClickListener {view->
            Log.d(TAG, "onCreate: download brn clicked")
            layoutToImage(view)
            imageToPDF()

        }



    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select image from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> chooseImageFromGallery()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    fun chooseImageFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data!!.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    saveImage(bitmap)
                    Toast.makeText(this, "Image Show!", Toast.LENGTH_SHORT).show()
                    imageview!!.setImageBitmap(bitmap)
                }
                catch (e: IOException)
                {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else if (requestCode == CAMERA)
        {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            imvPhoto.setImageBitmap(thumbnail)
            saveImage(thumbnail)
            Toast.makeText(this, "Photo Show!", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes)
        val wallpaperDirectory = File (
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        Log.d("fee", wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {
            wallpaperDirectory.mkdirs()
        }
        try
        {
            Log.d("heel", wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                .timeInMillis).toString() + ".png"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this, arrayOf(f.getPath()), arrayOf("image/png"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.absolutePath)

            return f.absolutePath
        }
        catch (e1: IOException){
            e1.printStackTrace()
        }
        return ""
    }


    fun layoutToImage(view: View) {
        // convert view group to bitmap
        printlayoutid.setDrawingCacheEnabled(true)
        printlayoutid.buildDrawingCache()
        val bm: Bitmap = printlayoutid.getDrawingCache()
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/jpeg"
        val bytes = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val f = File(
            Environment.getExternalStorageDirectory().toString() + File.separator + "image.jpg"
        )
        try {
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    @Throws(FileNotFoundException::class)
    fun imageToPDF() {
        try {
            val document = Document()
            dirpath = Environment.getExternalStorageDirectory().toString()
            PdfWriter.getInstance(
                document,
                FileOutputStream(dirpath + "/NewPDF.pdf")
            ) //  Change pdf's name.
            document.open()
            val img: Image = Image.getInstance(
                Environment.getExternalStorageDirectory().toString() + File.separator + "image.jpg"
            )
            val scaler: Float = (document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / img.getWidth() * 100
            img.scalePercent(scaler)
            img.setAlignment(Image.ALIGN_CENTER or Image.ALIGN_TOP)
            document.add(img)
            document.close()
            Toast.makeText(this, "PDF Generated successfully!..", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
        }
    }

    companion object {
        private const val IMAGE_DIRECTORY = "/nalhdaf"
    }
}
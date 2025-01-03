package com.fanntt.dbsql_fanntt

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fanntt.dbsql_fanntt.helper.helper
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var etNama : EditText
    private lateinit var etKampus : EditText
    private lateinit var etEmail : EditText
    private lateinit var etPhone : EditText
    private lateinit var btnSubmit : Button
    private lateinit var btnShowData : Button
    private lateinit var txtNama : TextView
    private lateinit var txtKampus : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etNama=findViewById(R.id.etNama)
        etKampus=findViewById(R.id.etNamaKampus)
        etEmail=findViewById(R.id.etEmail)
        etPhone=findViewById(R.id.etPhone)
        btnSubmit=findViewById(R.id.btnSubmit)
        btnShowData=findViewById(R.id.btnShowData)
        txtNama=findViewById(R.id.txtNama)
        txtKampus=findViewById(R.id.txtKampus)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnSubmit.setOnClickListener{

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = helper(this, null)

            // creating variables for values
            // in name and age edit texts
            val nama = etNama.text.toString()
            val kampus = etKampus.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()

            // calling method to add
            // name to our database
            db.addName(nama,kampus,email,phone )

            // Toast to message on the screen
            Toast.makeText(this, nama + " added to database", Toast.LENGTH_LONG).show()
            Toast.makeText(this, kampus + " added to database", Toast.LENGTH_LONG).show()
            Toast.makeText(this, email + " added to database", Toast.LENGTH_LONG).show()
            Toast.makeText(this, phone + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            etNama.text.clear()
            etKampus.text.clear()
            etEmail.text.clear()
            etPhone.text.clear()
        }

        btnShowData.setOnClickListener {

            // creating a DBHelper class
            // and passing context to it
            val db = helper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            txtNama.append(cursor.getString(cursor.getColumnIndex(helper.NAMA_LENGKAP)) + "\n")
            txtKampus.append(cursor.getString(cursor.getColumnIndex(helper.NAMA_KAMPUS)) + "\n")

//            // moving our cursor to next
//            // position and appending values
            while (cursor.moveToNext()) {
                txtNama.append(cursor.getString(cursor.getColumnIndex(helper.NAMA_LENGKAP)) + "\n")
                txtKampus.append(cursor.getString(cursor.getColumnIndex(helper.NAMA_KAMPUS)) + "\n")
              }

            // at last we close our cursor
            cursor.close()
        }

    }
}
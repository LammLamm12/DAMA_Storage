package pt.ipt.dama.dama_storage

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.PrintStream
import java.util.Scanner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSave01: Button = findViewById(R.id.btnSave1)
        val btnLoad01: Button = findViewById(R.id.btnLoad1)

        val btnSave02: Button = findViewById(R.id.btnSave2)
        val btnLoad02: Button = findViewById(R.id.btnLoad2)

        val btnSave03: Button = findViewById(R.id.btnSave3)
        val btnLoad03: Button = findViewById(R.id.btnLoad3)

        val btnSave04: Button = findViewById(R.id.btnSave4)
        val btnLoad04: Button = findViewById(R.id.btnLoad4)

        val btnSave05: Button = findViewById(R.id.btnSave5)
        val btnLoad05: Button = findViewById(R.id.btnLoad5)

        /*  *****************************************************************************************
         * save and read data to SharedPreferences
         */
        btnSave01.setOnClickListener{
            val sharedPreferences = getPreferences(MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("Texto", "text saved in SharedPreferences")
            editor.commit()

            Toast.makeText(this, "Shared data saved", Toast.LENGTH_SHORT).show()
        }

        btnLoad01.setOnClickListener {
            val sharedPreferences = getPreferences(MODE_PRIVATE)
            val savedText = sharedPreferences.getString("Texto", "There are no data")
            // show the text on screen
            Toast.makeText(this, "saved text: ${savedText}", Toast.LENGTH_LONG).show()
        }

        /*  *****************************************************************************************
         * save and read data to SharedPreferences, but specify the name of file to store data
         */
        btnSave02.setOnClickListener {
            // define where data is stored
            val sharedPreferences = getSharedPreferences("text.dat", MODE_PRIVATE)
            // define the editor to save the data
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("Texto", "text saved in SharedPreferences IN FILE")
            editor.commit()

            Toast.makeText(this, "Shared data saved", Toast.LENGTH_SHORT).show()
        }

        btnLoad02.setOnClickListener {
            // open the 'file' to read data on it
            val sharedPreferences = getSharedPreferences("text.dat", MODE_PRIVATE)
            val savedText = sharedPreferences.getString("Texto", "There are no data")
            // show the text on screen
            Toast.makeText(this, "saved text: ${savedText}", Toast.LENGTH_LONG).show()
        }


        /* *****************************************************************************************
         * write and read data on internal storage
         */
        btnSave03.setOnClickListener {
            val directory: File = getFilesDir()
            // define file name and location
            val file: File = File(directory, "data.txt")
            Toast.makeText(this, "location: ${file.absolutePath}", Toast.LENGTH_SHORT).show()
            // open the file
            val fostream: FileOutputStream = FileOutputStream(file)
            // prepare file for receive data
            val fprint: PrintStream = PrintStream(fostream)
            // write text at file
            fprint.println("text stored in internal storage")
            // close stream
            fprint.close()
            fostream.close()

            Toast.makeText(this, "internal storage", Toast.LENGTH_SHORT).show()
        }

        btnLoad03.setOnClickListener {
            val directory: File = getFilesDir()
            // define file name and location
            val file: File = File(directory, "data.txt")
            // access data
            try {
                // open the file
                val fi: FileInputStream = FileInputStream(file)
                // start reading content
                val sc: Scanner = Scanner(fi)
                // Read first line.
                // if there are more lines, repeat command
                val savedText: String = sc.nextLine()
                // if you do not have anymore things to read, close file
                sc.close()
                fi.close()

                // show data on screen
                Toast.makeText(this, savedText, Toast.LENGTH_SHORT).show()

            } catch (e: FileNotFoundException) {
                Toast.makeText(
                    this,
                    "There is no file at internal storage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        /*  *****************************************************************************************
         * save and read data to external storage
         */
        btnSave04.setOnClickListener {
            // read the location of external storage
            val directory: File = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS
            )
            // define file name and location
            val file: File = File(directory, "dataExternal.txt")
            Toast.makeText(this, "location: ${file.absolutePath}", Toast.LENGTH_SHORT).show()
            // open the file
            val fostream: FileOutputStream = FileOutputStream(file)
            // prepare file for receive data
            val fprint: PrintStream = PrintStream(fostream)
            // write text at file
            fprint.println("text stored in EXTERNAL storage")
            // close stream
            fprint.close()
            fostream.close()

            Toast.makeText(this, "EXTERNAL storage", Toast.LENGTH_SHORT).show()

        }

        btnLoad04.setOnClickListener {
            val directory: File =  Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS
            )
            // define file name and location
            val file: File = File(directory, "dataExternal.txt")
            // access data
            try {
                // open the file
                val fi: FileInputStream = FileInputStream(file)
                // start reading content
                val sc: Scanner = Scanner(fi)
                // Read first line.
                // if there are more lines, repeat command
                val savedText: String = sc.nextLine()
                // if you do not have anymore things to read, close file
                sc.close()
                fi.close()

                // show data on screen
                Toast.makeText(this, savedText, Toast.LENGTH_SHORT).show()

            } catch (e: FileNotFoundException) {
                Toast.makeText(
                    this,
                    "Error: There is no file at internal storage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        /* *****************************************************************************************
        * write and read data at Cache
        */
        btnSave05.setOnClickListener {
            val directory: File = getCacheDir()
            // define file name and location
            val file: File = File(directory, "dataOnCache.txt")
            Toast.makeText(this, "location: ${file.absolutePath}", Toast.LENGTH_SHORT).show()
            // open the file
            val fostream: FileOutputStream = FileOutputStream(file)
            // prepare file for receive data
            val fprint: PrintStream = PrintStream(fostream)
            // write text at file
            fprint.println("text stored at Cache")
            // close stream
            fprint.close()
            fostream.close()

            Toast.makeText(this, "cache", Toast.LENGTH_SHORT).show()
        }

        btnLoad05.setOnClickListener {
            val directory: File = getCacheDir()
            // define file name and location
            val file: File = File(directory, "dataOnCache.txt")
            // access data
            try {
                // open the file
                val fi: FileInputStream = FileInputStream(file)
                // start reading content
                val sc: Scanner = Scanner(fi)
                // Read first line.
                // if there are more lines, repeat command
                val savedText: String = sc.nextLine()
                // if you do not have anymore things to read, close file
                sc.close()
                fi.close()

                // show data on screen
                Toast.makeText(this, savedText, Toast.LENGTH_SHORT).show()

            } catch (e: FileNotFoundException) {
                Toast.makeText(
                    this,
                    "Error: There is no file at Cache",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    } // onCreate
}
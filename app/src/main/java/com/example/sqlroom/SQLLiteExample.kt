package com.example.sqlroom

import android.annotation.SuppressLint
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SQLLiteExample : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqllite_example)

        val addName: Button = findViewById(R.id.addName)
        val printName: Button = findViewById(R.id.printName)
        val enterName: EditText = findViewById(R.id.enterName)
        val enterAge: EditText = findViewById(R.id.enterAge)
        val Name: TextView = findViewById(R.id.Name)
        val Age: TextView = findViewById(R.id.Age)
        val del: Button = findViewById(R.id.delete)
        addName. setOnClickListener{

            val db=SQLLiteDBHelper(this,null)
            val name=enterName.text.toString()
            val age=enterAge.text.toString()
            db.addName(name,age)

            Toast.makeText(this,name+"added to database",Toast.LENGTH_LONG).show()
            enterName.text.clear()
            enterAge.text.clear()
        }

        printName.setOnClickListener {
            Name.text=""
            Age.text=""

            val db=SQLLiteDBHelper(this,null)
            val cursor=db.getName()



            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Name.append(cursor.getString(cursor.getColumnIndex(SQLLiteDBHelper.NAME_COL))+"\n")
                    Age.append(cursor.getString(cursor.getColumnIndex(SQLLiteDBHelper.AGE_COL))+"\n")

                } while (cursor.moveToNext())

                cursor.close()
            } else {

                Name.text = "No data found"
            }
        }
        del.setOnClickListener {
            var string=""
            val db=SQLLiteDBHelper(this,null)
            db.delALL()
            Name.setText("")
            Age.setText("")

        }

    }
}
package com.example.sqlroom

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLLiteDBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context,
    DATABASE_NAME, factory, DATABASE_VERSION){

    //below is the method for creating a database by sqlite query
    override fun onCreate(db:SQLiteDatabase){
        //below is a sqlite query, where column names
        //along with their data types is given
        val query:String=("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + "INTEGER PRIMARY KEY, " +
                NAME_COL + " TEXT," +
                AGE_COL + " TEXT" + ")")

        //we are calling sqlite
        //method for executing our query
        db.execSQL(query)

    }
    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        //this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    //this method is for adding data in out database
    fun addName(name : String, age :String){

        //below we are creating a content values variable
        val values = ContentValues()

        //we are inserting our values in the form of key-value pair
        values.put(NAME_COL, name)
        values.put(AGE_COL, age)

        //here we are creating a writable variable of our database as we want to insert value in our database
        val db :SQLiteDatabase =this.writableDatabase

        //all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        //at last we are closing our database
        db.close()
    }

    //below method is to get all data from our databse
    fun getName(): Cursor?{

        //here we are creating a readable variable of our database as we want to read value from it
        val db = this.readableDatabase

        //below code return a cursor to read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)


    }
    fun delALL()
    {
        val db = this.writableDatabase
        db.execSQL("delete from "+ TABLE_NAME);
        db.close()
    }
    companion object{
        //here we have defined variables for our database
        //below is variable for database name
        private val DATABASE_NAME="CSE_226_SQL"

        //below is the variable for databse version
        private val DATABASE_VERSION = 1

        val TABLE_NAME="my_table"
        val ID_COL="id"
        val NAME_COL="name"
        val AGE_COL="age"

    }
}
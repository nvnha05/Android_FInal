package com.example.android_final.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.android_final.Model.UserTasks

class UserDbHandler(context: Context) : SQLiteOpenHelper(context, UserDbHandler.DB_NAME, null, UserDbHandler.DB_VERSION) {

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "UserData"
        private val TABLE_NAME = "Details"
        private val ID = "Id"
        private val NAME = "Name"
        private val PHNO = "PHNO"
        private val PASSWORD = "PASSWORD"

    }

    override fun onCreate(db: SQLiteDatabase) {
        //tao bang
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $NAME TEXT,$PASSWORD TEXT,$PHNO TEXT);"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addTask(tasks: UserTasks): Boolean {
        //khoi tao database
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, tasks.name)
        values.put(PASSWORD, tasks.password)
        values.put(PHNO, tasks.phno)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedId1", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

}
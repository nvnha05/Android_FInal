package com.example.android_final.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.android_final.Model.BookingTasks
import java.util.ArrayList

class BookingsDbHandler(context: Context) : SQLiteOpenHelper(context, BookingsDbHandler.DB_NAME, null, BookingsDbHandler.DB_VERSION) {

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "MyBookings"
        private val TABLE_NAME = "Bookings"
        private val ID = "Id"
        private val NAME = "Name"
        private val DESC = "Des"
        private val RATE = "Rate"
        private val ROOMS = "Rooms"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $NAME TEXT,$DESC TEXT,$RATE TEXT,$ROOMS TEXT);"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addTask(tasks: BookingTasks): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, tasks.name)
        values.put(DESC, tasks.desc)
        values.put(RATE, tasks.rate)
//        values.put(ROOMS, tasks.rooms)


        Log.d("insert",tasks.name)
        Log.d("insert",tasks.desc)
        Log.d("insert",tasks.rate)
//        Log.d("insert",tasks.rooms)

//        values.put(COMPLETED, tasks.completed)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedIdMybooking", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    fun getTask(_id: Int): BookingTasks {
        val tasks = BookingTasks()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)

        cursor?.moveToFirst()
        tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ID)))
        tasks.name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
        tasks.desc = cursor.getString(cursor.getColumnIndexOrThrow(DESC))
        tasks.rate = cursor.getString(cursor.getColumnIndexOrThrow(RATE))

        cursor.close()
        return tasks
    }

    fun task(): List<BookingTasks> {
        val taskList = ArrayList<BookingTasks>()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val tasks = BookingTasks()
                    tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ID)))
                    tasks.name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
                    tasks.desc = cursor.getString(cursor.getColumnIndexOrThrow(DESC))
                    tasks.rate = cursor.getString(cursor.getColumnIndexOrThrow(RATE))
                    tasks.rooms = cursor.getString(cursor.getColumnIndexOrThrow(ROOMS))
//                    tasks.completed = cursor.getString(cursor.getColumnIndex(COMPLETED))
                    taskList.add(tasks)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return taskList
    }


}
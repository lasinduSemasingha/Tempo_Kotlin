package com.example.tempo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "User.db"
        private const val TABLE_NAME = "data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRSTNAME = "firstName"
        private const val COLUMN_LASTNAME = "lastName"
        private const val COLUMN_AGE = "age"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " + // Corrected AUTOINCREMENT syntax
                "$COLUMN_FIRSTNAME TEXT, " +
                "$COLUMN_LASTNAME TEXT, " +
                "$COLUMN_AGE INTEGER, " + // Changed NUMBER to INTEGER
                "$COLUMN_EMAIL TEXT, " +
                "$COLUMN_USERNAME TEXT, " + // Added missing comma
                "$COLUMN_PASSWORD TEXT)")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertUser(firstName: String, lastName: String, age: Int, email: String, username: String, password: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_FIRSTNAME, firstName)
            put(COLUMN_LASTNAME, lastName)
            put(COLUMN_AGE, age)
            put(COLUMN_EMAIL, email)
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun readUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor: Cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }
    @SuppressLint("Range")
    fun getFirstName(username: String): String {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_FIRSTNAME), selection, selectionArgs, null, null, null)
        var firstName = ""
        if (cursor != null && cursor.moveToFirst()) {
            firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME))
            cursor.close()
        }
        return firstName
    }


}

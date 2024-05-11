package com.example.tempo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskDatabase(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object{
        private const val DATABASE_NAME = "tasks"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "taskList"
        private const val ID = "id"
        private const val TASK_NAME = "taskName"
        private const val TASK_DESCRIPTION = "taskDescription"
        private const val TASK_PRIORITY = "taskPriority"
        private const val TASK_DEADLINE = "taskDeadline"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE ${TaskDatabase.TABLE_NAME} (" +
                "${TaskDatabase.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${TaskDatabase.TASK_NAME} TEXT, " +
                "${TaskDatabase.TASK_DESCRIPTION} TEXT, " +
                "${TaskDatabase.TASK_PRIORITY} TEXT, " +
                "${TaskDatabase.TASK_DEADLINE} TEXT)")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS ${TaskDatabase.TABLE_NAME}"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }
}
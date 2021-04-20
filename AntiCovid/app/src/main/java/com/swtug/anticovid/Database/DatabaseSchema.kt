package com.swtug.anticovid.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.swtug.anticovid.User

class DatabaseSchema(context: Context) : SQLiteOpenHelper(context, "AntiCovid.db", null, 1) {

    private val USER_TABLE = ("CREATE TABLE user ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT,"
            + "surname TEXT,"
            + "email TEXT,"
            + "address TEXT,"
            + "secid TEXT,"
            + "phonenumber TEXT,"
            + "password TEXT" + ")")

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS user")
        onCreate(db)
    }

    fun newUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("name", user.name)
        values.put("surname", user.surname)
        values.put("email", user.email)
        values.put("address", user.address)
        values.put("secid", user.secid)
        values.put("phonenumber", user.phonenumber)
        values.put("password", user.password)
        db.insert("user", null, values)
        db.close()
    }


    fun alreadyExistUser(email: String): Boolean {
        val query =
                "SELECT * FROM user WHERE email =  \"$email\""

        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)

        var user: User? = null

        if(cursor.moveToFirst())
        {
            db.close()
            return true
        }
        db.close()
        return false

    }

    fun getAllUser(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM user", null)
    }

}
package com.swtug.anticovid.Register

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swtug.anticovid.Database.DatabaseSchema
import com.swtug.anticovid.MainActivity
import com.swtug.anticovid.R
import com.swtug.anticovid.User
import com.swtug.anticovid.main.MainFragment

class Register(context: Context){

    val databaseSchema: DatabaseSchema = DatabaseSchema(context)

    companion object {
        fun stringValidityCheck(name: String, surname: String, email: String, address: String, secid: String,
                                phonenumber: String, password: String) : Boolean
        {
            if(name.isEmpty() || surname.isEmpty() || email.isEmpty() || address.isEmpty()
                    || secid.isEmpty() || phonenumber.isEmpty() || password.isEmpty())
            {
                return false
            }

            if(password.length < 8)
            {
                return false
            }

            return true
        }
    }

    fun register(name: String, surname: String, email: String, address: String, secid: String,
        phonenumber: String, password: String) : Boolean
    {


        val validity = stringValidityCheck(name, surname, email, address, secid, phonenumber, password)

        if(validity.equals(false))
        {
            return  false
        }

        if(databaseSchema.alreadyExistUser(email) == true)
        {
            return false
        }

        databaseSchema.newUser(User(-1, name, surname, email, address, secid, phonenumber, password));

        return true
    }
}
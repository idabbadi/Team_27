package com.swtug.anticovid.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.swtug.anticovid.models.User
import com.swtug.anticovid.R
import com.swtug.anticovid.repositories.PreferencesRepo
import com.swtug.anticovid.view.BaseFragment

class ProfileFragment : BaseFragment() {

    private lateinit var btnlogout: Button
    private lateinit var btnedit: Button
    private lateinit var editTextName: EditText
    private lateinit var editTextSurname: EditText
    private lateinit var editTextEMail: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var editTextSocialSecurityID: EditText
    private lateinit var editTextPhoneNumber: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_profile, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFields(view)
        initListeners()
        assignValues()

    }
    private fun assignValues(){
        val user = PreferencesRepo.getUser(requireContext())
        if(user != null){
            editTextName.setText(user.name)
            editTextSurname.setText(user.surname)
            editTextEMail.setText(user.email)
            editTextAddress.setText(user.address)
            editTextSocialSecurityID.setText(user.secid)
            editTextPhoneNumber.setText(user.phonenumber)
        }

    }

    private fun initListeners() {
        btnlogout.setOnClickListener {
            PreferencesRepo.deleteUser(requireContext())
            PreferencesRepo.deleteVaccination(requireContext())
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)

        }

        btnedit.setOnClickListener {
            val old_user = PreferencesRepo.getUser(requireContext())
            if(old_user!=null)
            {
                val user = User(
                    old_user.id,
                    editTextName.text.toString(),
                    editTextSurname.text.toString(),
                    editTextEMail.text.toString(),
                    editTextAddress.text.toString(),
                    editTextSocialSecurityID.text.toString(),
                    editTextPhoneNumber.text.toString(),
                    old_user.password
                )
                PreferencesRepo.saveUser(requireContext(),user)
            }



        }
    }

    private fun initFields(view: View) {
        btnlogout = view.findViewById(R.id.logoutbutton)
        btnedit = view.findViewById(R.id.button2)
        editTextName = view.findViewById(R.id.editTextProfileName)
        editTextSurname = view.findViewById(R.id.editTextProfileSurname)
        editTextEMail = view.findViewById(R.id.editTextProfileEmail)
        editTextAddress = view.findViewById(R.id.editTextProfileAddress)
        editTextSocialSecurityID = view.findViewById(R.id.editTextProfileSocialSecurityID)
        editTextPhoneNumber = view.findViewById(R.id.editTextProfilePhoneNumber)
    }
}
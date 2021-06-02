package com.ibrajix.roomdatabase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.ibrajix.roomdatabase.R
import com.ibrajix.roomdatabase.databinding.ActivityMainBinding
import com.ibrajix.roomdatabase.model.User
import com.ibrajix.roomdatabase.viewmodel.DataStoreViewModel
import com.ibrajix.roomdatabase.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val dataStoreViewModel: DataStoreViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        checkIfUserHasSavedDetails()

        makeButtonNotClickableAtFirst()



    }

    private fun checkIfUserHasSavedDetails(){

        dataStoreViewModel.savedKey.observe(this){

            if (it == true){
               //saved go to the next activity
                val intent = Intent(this, UserDetailsActivity::class.java)
                startActivity(intent)
            }

            //user hasn't saved details, show the form
            else{
                initViews()
            }

        }
    }


    private fun initViews(){
        handleClick()
    }

    /**
     * Make button unclickable to avoid empty entries into room
     */

    private fun makeButtonNotClickableAtFirst(){

        //Make button UnClickable for the first time
        binding.btnSave.isEnabled = false
        binding.btnSave.background = ContextCompat.getDrawable(
            this,
            R.drawable.btn_opaque
        )

        //make the button clickable after detecting changes in input field
        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                val nameEt = binding.etName.text.toString()
                val ageEt = binding.etAge.text.toString()
                val numberEt = binding.etNumber.text.toString()

                if (nameEt.isEmpty() || ageEt.isEmpty() || numberEt.isEmpty()) {
                    binding.btnSave.isEnabled = false
                    binding.btnSave.background = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.btn_opaque
                    )
                } else {
                    binding.btnSave.isEnabled = true
                    binding.btnSave.background = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.btn_round
                    )
                }
            }

            override fun afterTextChanged(s: Editable) {

            }

        }

        binding.etName.addTextChangedListener(watcher)
        binding.etAge.addTextChangedListener(watcher)
        binding.etNumber.addTextChangedListener(watcher)

    }

    /**
     * Handle click of save button
     */
    private fun handleClick(){

        //on click of button save
        binding.btnSave.setOnClickListener {

            //get details entered
            val name = binding.etName.text.toString()
            val age = binding.etAge.text.toString()
            val number = binding.etNumber.text.toString()


            val user = User(id = 1, name = name, age = age, number = number)

            //save the details to room database
            userViewModel.insertUserDetails(user)

            userViewModel.response.observe(this){

                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()

                //success, save key so on next visit user goes to details screen
                dataStoreViewModel.setSavedKey(true)

                //show toast message
                Toast.makeText(this, applicationContext.getString(R.string.record_saved), Toast.LENGTH_LONG).show()

                //go to next activity
                val intent = Intent(this, UserDetailsActivity::class.java)
                startActivity(intent)

            }

        }

    }


}
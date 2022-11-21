package com.packagesmaster.utilities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.packagesmaster.androidhelper.helpers.AndroidHelper
import com.packagesmaster.androidhelper.imagepicker.ImagePicker
import com.packagesmaster.utilities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowProgressDialog.setOnClickListener {
            AndroidHelper
                .with(this)
                .getUtilities()
                .showProgressDialog()
        }

        binding.btnShowImagePickerDialog.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }

    }
}
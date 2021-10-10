package com.example.santiye.main

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.santiye.R
import com.example.santiye.databinding.ActivityProductAddedBinding
import com.example.santiye.databinding.FragmentProductsBinding
import com.google.android.material.snackbar.Snackbar
import java.net.URL

class ProductAdded : AppCompatActivity() {

    private lateinit var binding: ActivityProductAddedBinding
    private lateinit var activityResultLauncher:ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    var selectPictures: Uri? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductAddedBinding.inflate(layoutInflater)

        registerLauncher()
        binding.imageView2.setOnClickListener(View.OnClickListener {view ->

            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.START_VIEW_PERMISSION_USAGE)){
                    Snackbar.make(view,"Permisson needed for galery" , Snackbar.LENGTH_INDEFINITE).setAction("Give Permisson", View.OnClickListener {
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    })
                }
                else{

                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }else{
                val intentGallery =Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentGallery)
            }

        })





        setContentView(binding.root)
    }


    private fun registerLauncher(){

        activityResultLauncher =registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {result ->
                if (result.resultCode == RESULT_OK){
                    val intenFormResult = result.data
                    if (intenFormResult != null){
                        selectPictures =intenFormResult.data
                        selectPictures.let {
                            binding.imageView2.setImageURI(it)
                        }
                    }
                }

            })

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){result ->

            if (result){
                val intentGallery =Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentGallery)
            }else{
                Toast.makeText(this,"Permisson needed!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
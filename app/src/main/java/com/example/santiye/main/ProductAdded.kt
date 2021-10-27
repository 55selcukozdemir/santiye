package com.example.santiye.main

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
import com.example.santiye.adapter.CustomSpinner
import com.example.santiye.databinding.ActivityProductAddedBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.util.*

class ProductAdded : AppCompatActivity() {

    private lateinit var binding: ActivityProductAddedBinding
    private lateinit var activityResultLauncher:ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    var selectPictures: Uri? = null

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private val TAG = "ProductAdded"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductAddedBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        registerLauncher()
        auth = Firebase.auth
        firestore = Firebase.firestore
        storage = Firebase.storage
        val layout  = binding.categoryItems

        val itemView = CustomSpinner(this)
        layout.addView(itemView.getView())



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

        binding.button4.setOnClickListener(View.OnClickListener {
            val uuid = UUID.randomUUID()
            val imageName = "$uuid.jpg"

            val reference = storage.reference
            val imageReference = reference.child("images").child(imageName)
            if (selectPictures != null) {
                imageReference.putFile(selectPictures!!).addOnSuccessListener {


                    
                    val uploadPictureReference = storage.reference.child("images").child(imageName)

                    uploadPictureReference.downloadUrl.addOnSuccessListener {

                        val downloadUrl = it.toString()

                        if (true){
                            val postMap = hashMapOf<String, Any>()
                            postMap.put("contentImage", downloadUrl)
                            postMap.put("block", itemView.getSpinner()[0].selectedItem)
                            postMap.put("floor", itemView.getSpinner()[2].selectedItem)
                            postMap.put("ticket", itemView.getSpinner()[1].selectedItem)

                            postMap.put("email", "w@mail.com")

                            //postMap.put("userEmail", auth.currentUser!!.email!!)
                            postMap.put("explanation", binding.editTextTextPersonName3.text.toString())
                            postMap.put("date", Timestamp.now())

                            firestore.collection("posts").add(postMap).addOnSuccessListener{

                                finish()
                            }.addOnFailureListener{
                                Toast.makeText(this, it.localizedMessage,Toast.LENGTH_LONG).show()
                            }
                        }

                    }

                }.addOnFailureListener{
                    Toast.makeText(this, it.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Lütfen bir görsel seçerek yükleme yapınız.", Toast.LENGTH_SHORT).show()
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
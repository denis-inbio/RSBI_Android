//package ro.rsbideveloper.rsbi.test
//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.graphics.Bitmap
//import android.graphics.drawable.BitmapDrawable
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.os.Environment
//import android.os.PersistableBundle
//import android.util.Log
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import ro.rsbideveloper.rsbi.R
//import ro.rsbideveloper.rsbi.databinding.SaveFileBinding
//import java.io.File
//import java.io.FileOutputStream
//import java.io.OutputStream
//
//class SaveFileToStorage : AppCompatActivity() {
//    private lateinit var binding: SaveFileBinding
//    private val requestCodeExternalStorage: Int = 100
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = SaveFileBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.button.setOnClickListener {
//            Log.d("FILESAVE", "Pressed the save button")
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    != PackageManager.PERMISSION_GRANTED) {
//                        Log.d("FILESAVE", "Permission requested")
//                        ActivityCompat.requestPermissions(
//                            this,
//                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                            requestCodeExternalStorage)
//                }
//            }
//
//            SaveImageViewToStorage()
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if(requestCode == requestCodeExternalStorage) {
//            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                SaveImageViewToStorage()
//            } else {
//                Toast.makeText(this, "Permission refused", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun SaveImageViewToStorage() {
//        val externalStorageState = Environment.getExternalStorageState()
//        if(externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
//            Log.d("FILESAVE", "Trying to create the file")
//
//            val storageDirectory = Environment.getExternalStorageDirectory().toString()
//            val filename = "image.jpg"
//
//            val file = File(storageDirectory, filename)
//            Log.d("FILESAVE", "File created")
//
//            try {
//                val stream: OutputStream = FileOutputStream(file)
//                val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.ic_baseline_article_24)
//                val bitmap = (drawable as BitmapDrawable).bitmap
//
//                Log.d("FILESAVE", "Writing to stream in memory")
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
//
//                Log.d("FILESAVE", "Flushing and closing")
//                stream.flush()
//                stream.close()
//
//                Toast.makeText(this, "File saved at ${Uri.parse(file.absolutePath)}", Toast.LENGTH_SHORT).show()
//            } catch(e: Exception) {
//                Log.d("SAVEFILE", "Exception: ${e.message}")
//            }
//        } else {
//            Toast.makeText(this, "Cannot access storage; not mounted ?", Toast.LENGTH_SHORT).show()
//        }
//    }
//}
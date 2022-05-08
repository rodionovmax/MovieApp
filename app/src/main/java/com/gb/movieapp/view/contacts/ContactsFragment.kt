package com.gb.movieapp.view.contacts

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import com.gb.movieapp.R
import com.gb.movieapp.databinding.FragmentContactsBinding


const val REQUEST_CODE = 42
const val MY_TAG = "my_tag"

class ContactsFragment : Fragment() {
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ContactsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        Log.d(
            MY_TAG,
            binding.containerForContacts.size.toString()
        )
//        for (i in 0 until binding.containerForContacts.size) {
//            binding.containerForContacts[i].setOnClickListener {
//                context?.let { it1 -> call(it1, "123456789") }
//            }
//        }
        binding.containerForContacts.setOnClickListener {
            context?.let { it1 -> call(it1, "123456789") }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Проверяем, разрешено ли чтение контактов
    private fun checkPermission() {
        context?.let {
            when {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    //Доступ к контактам на телефоне есть
                    getContacts()
                }
                //Опционально: если нужно пояснение перед запросом разрешений
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                    AlertDialog.Builder(it)
                        .setTitle("Доступ к контактам")
                        .setMessage("Объяснение")
                        .setPositiveButton("Предоставить доступ") { _, _ ->
                            requestPermission()
                        }
                        .setNegativeButton("Не надо") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
                else -> {
                    //Запрашиваем разрешение
                    requestPermission()
                }
            }
        }
    }

    private fun requestPermission() {
        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE)
    }

    // Обратный вызов после получения разрешений от пользователя
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                // Проверяем, дано ли пользователем разрешение по нашему запросу
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    getContacts()
                } else {
                    // Поясните пользователю, что экран останется пустым, потому что доступ к контактам не предоставлен
                    context?.let {
                        AlertDialog.Builder(it)
                            .setTitle("Доступ к контактам")
                            .setMessage("Объяснение")
                            .setNegativeButton("Закрыть") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                    }
                }
                return
            }
        }
    }

    private fun getContacts() {
        context?.let {
            val contentResolver: ContentResolver = it.contentResolver

            val cursorWithContacts: Cursor? = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )

            cursorWithContacts?.let { cursor ->
                for (i in 0..cursor.count) {
                    val pos = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                    val phoneNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER).toString()

                    if (cursor.moveToPosition(i)) {
                        // Берём из Cursor’а столбец с именем
                        val name = cursor.getString(pos)
                        addView(it, name)
                        Log.d(MY_TAG, phoneNumber)
                    }
                    drawHorizontalLine(it)
                }
            }

            cursorWithContacts?.close()
        }
    }

    private fun addView(context: Context, textToShow: String) {
        binding.containerForContacts.addView(AppCompatTextView(context).apply {
            text = textToShow
            textSize = resources.getDimension(R.dimen.main_container_text_size)
            typeface = Typeface.DEFAULT_BOLD
        })
    }

    @SuppressLint("ResourceAsColor")
    private fun drawHorizontalLine(context: Context) {
        val line = View(context)
        line.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            5
        )
        line.setBackgroundColor(R.color.dark_grey)
        binding.containerForContacts.addView(line)
    }

    private fun call(context: Context, contactNumber : String) {
        View(context).setOnClickListener {
            makeCall(contactNumber)
        }
    }

    private fun makeCall(contactNumber : String) {

        val callIntent = Intent(Intent.ACTION_CALL)

        callIntent.data = Uri.parse("tel:$contactNumber")

        // on below line we are checking if the calling permissions are granted or not.
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.CALL_PHONE
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        // at last we are starting activity.
        startActivity(callIntent)
    }
}
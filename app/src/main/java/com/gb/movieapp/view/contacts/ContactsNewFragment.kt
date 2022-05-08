package com.gb.movieapp.view.contacts

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.movieapp.databinding.FragmentContactsNewBinding
import com.gb.movieapp.model.Contact
import kotlinx.android.synthetic.main.fragment_contacts_new.*

const val NEW_REQUEST_CODE = 42
const val NEW_TAG = "new_tag"


class ContactsNewFragment : Fragment() {

    private var _binding: FragmentContactsNewBinding? = null
    private val binding get() = _binding!!

    private val adapter: ContactsAdapter by lazy { ContactsAdapter() }

            /*
            * private val viewModel: ReviewViewModel by lazy { ViewModelProvider(this).get(ReviewViewModel::class.java) }
            * */

    companion object {
        fun newInstance() = ContactsNewFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactsNewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()

        binding.contactsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val contactList: List<Contact> = getNamesAndPhones()

        binding.contactsRecyclerView.adapter = adapter
        adapter.setData(contactList)

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
//                    getContacts() // TODO: change write contacts to DB here
                    getNamesAndPhones() // TODO: change write contacts to DB here
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
        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), NEW_REQUEST_CODE)
    }

    // Обратный вызов после получения разрешений от пользователя
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            NEW_REQUEST_CODE -> {
                // Проверяем, дано ли пользователем разрешение по нашему запросу
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
//                    getContacts()
                    getNamesAndPhones()
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

    @SuppressLint("Range")
    fun getNamesAndPhones() : List<Contact> {

        val listOfContacts : MutableList<Contact> = mutableListOf()

        context?.let {
            val contentResolver: ContentResolver = it.contentResolver

            val cursorWithContacts: Cursor? = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )

            val phones: Cursor? = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                null,
                null
            )

            cursorWithContacts?.let { cursor ->
                for (i in 0..cursor.count) {
                    val contactCursor =
                        cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

                    var name : String = ""
                    if (cursor.moveToPosition(i)) {
                        // Берём из Cursor’а столбец с именем
                        name = cursor.getString(contactCursor)
                        /*val hasPhone: Int =
                            cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                        if (hasPhone > 0) {
                            val phoneNumber =
                                phones?.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                            Log.i(NEW_TAG, name)
                            if (phoneNumber != null) {
                                Log.i(NEW_TAG, phoneNumber)
                            }
                        }*/

                        Log.i(NEW_TAG, name)
                    }

                    listOfContacts.add(Contact(i, name, listOf("+13475934175")))
                }

            }
            cursorWithContacts?.close()
        }
        return listOfContacts
    }

//    @SuppressLint("Range")
//    private fun getContacts() {
//        context?.let {
//            val contentResolver: ContentResolver = it.contentResolver
//
//            val cursorWithContacts: Cursor? = contentResolver.query(
//                ContactsContract.Contacts.CONTENT_URI,
//                null,
//                null,
//                null,
//                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
//            )
//
//            cursorWithContacts?.let { cursor ->
//                for (i in 0..cursor.count) {
//                    val contactCursor = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
////                    val phoneNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER).toString()
//
//                    if (cursor.moveToPosition(i)) {
//                        // Берём из Cursor’а столбец с именем
//                        val name = cursor.getString(contactCursor)
//
//                        Log.i(NEW_TAG, name)
////                        addView(it, name)
////                        Log.d(NEW_TAG, phoneNumber)
//                        val phoneNumber =
//                            phones?.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                        phoneNumber?.let { number -> Log.i(NEW_TAG, number) }
//
//                    }
//                }
//            }
//
//            cursorWithContacts?.close()
//        }
//    }

//    private fun saveContact(context: Context, textToShow: String) {
//
//    }

//    var phones: Cursor? = null

    /*if (cursorWithContacts != null) {
        if (cursorWithContacts.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER) > 0) {
            // Query phone here. Covered next
//                    val phones: Cursor? = contentResolver.query(
            phones = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                null,
                null
            )
        }
    }*/

//    private fun addView(context: Context, textToShow: String) {
//        binding.containerForContacts.addView(AppCompatTextView(context).apply {
//            text = textToShow
//            textSize = resources.getDimension(R.dimen.main_container_text_size)
//            typeface = Typeface.DEFAULT_BOLD
//        })
//    }

    /*@SuppressLint("ResourceAsColor")
    private fun drawHorizontalLine(context: Context) {
        val line = View(context)
        line.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            5
        )
        line.setBackgroundColor(R.color.dark_grey)
        binding.containerForContacts.addView(line)
    }

    private fun call(context: Context, contactNumber : String) {
        View(context).setOnClickListener {
            makeCall(contactNumber)
        }
    }*/

//    private fun makeCall(contactNumber : String) {
//
//        val callIntent = Intent(Intent.ACTION_CALL)
//
//        callIntent.data = Uri.parse("tel:$contactNumber")
//
//        // on below line we are checking if the calling permissions are granted or not.
//        if (context?.let {
//                ActivityCompat.checkSelfPermission(
//                    it,
//                    Manifest.permission.CALL_PHONE
//                )
//            } != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//
//        // at last we are starting activity.
//        startActivity(callIntent)
//    }
}
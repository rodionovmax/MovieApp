/*
package com.gb.movieapp.view.contacts

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.os.AsyncTask
import android.os.Build
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Patterns


class FetchContacts :
    AsyncTask<Void?, Void?, ArrayList<Contact>?>() {
    private val DISPLAY_NAME =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) ContactsContract.Contacts.DISPLAY_NAME_PRIMARY else ContactsContract.Contacts.DISPLAY_NAME
    private val FILTER = "$DISPLAY_NAME NOT LIKE '%@%'"
    private val ORDER = String.format("%1\$s COLLATE NOCASE", DISPLAY_NAME)

    @SuppressLint("InlinedApi")
    private val PROJECTION = arrayOf(
        ContactsContract.Contacts._ID,
        DISPLAY_NAME,
        ContactsContract.Contacts.HAS_PHONE_NUMBER
    )

    protected override fun doInBackground(vararg params: Void): ArrayList<Contact>? {
        return try {
            val contacts: ArrayList<Contact> = ArrayList()
            val cr: ContentResolver = getContentResolver()
            val cursor: Cursor? =
                cr.query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, FILTER, null, ORDER)
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // get the contact's information
                    val id: String =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name: String = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME))
                    val hasPhone: Int =
                        cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                    // get the user's email address
                    var email: String? = null
                    val ce: Cursor? = cr.query(
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        arrayOf(id),
                        null
                    )
                    if (ce != null && ce.moveToFirst()) {
                        email =
                            ce.getString(ce.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                        ce.close()
                    }

                    // get the user's phone number
                    var phone: String? = null
                    if (hasPhone > 0) {
                        val cp: Cursor? = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            arrayOf(id),
                            null
                        )
                        if (cp != null && cp.moveToFirst()) {
                            phone =
                                cp.getString(cp.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            cp.close()
                        }
                    }

                    // if the user user has an email or phone then add it to contacts
                    if ((!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email)
                            .matches()
                                && !email.equals(name, ignoreCase = true)) || !TextUtils.isEmpty(
                            phone
                        )
                    ) {
                        val contact = Contact()
                        contact.name = name
                        contact.email = email
                        contact.phoneNumber = phone
                        contacts.add(contact)
                    }
                } while (cursor.moveToNext())

                // clean up cursor
                cursor.close()
            }
            contacts
        } catch (ex: Exception) {
            null
        }
    }

    override fun onPostExecute(contacts: ArrayList<Contact>?) {
        if (contacts != null) {
            // success
            mContacts = contacts
        } else {
            // show failure
            // syncFailed();
        }
    }
}*/

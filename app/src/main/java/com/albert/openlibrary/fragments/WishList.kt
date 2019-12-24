package com.albert.openlibrary.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.albert.openlibrary.R
import com.albert.openlibrary.beans.Wish
import io.realm.Realm
import io.realm.kotlin.deleteFromRealm
import java.util.*

/**
 * This is the class that allows the
 * user to persist and remove contents
 * from and to the Realm database.
 */
class WishList : Fragment() {

    lateinit var wish_name : EditText
    lateinit var add_wish : Button
    lateinit var wish_list : ListView
    lateinit var wish_adaptor : ArrayAdapter<String>
    lateinit var wishes : ArrayList<String>

    /**
     * This is where the layout for search is created.
     * inflater: This object creates the layout.
     * container: This is the parent class.
     * savedIntanceState: This is the set of data passed
     * to this fragment.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        Realm.init(activity)
        val realm = Realm.getDefaultInstance()

        var view =  inflater.inflate(R.layout.wish_list, container, false)

        wish_name = view.findViewById<EditText>(R.id.wish_name)
        add_wish = view.findViewById<Button>(R.id.add_wish)
        wish_list = view.findViewById<ListView>(R.id.wish_list)

        populateWishList()

        add_wish.setOnClickListener {
            if (!TextUtils.isEmpty(wish_name.text.toString())) {
                realm.beginTransaction();
                var wish = realm.createObject(Wish::class.java);
                wish.name = wish_name.text.toString()
                realm.commitTransaction();
                wish_name.setText("")
                wishes.add(wish.name)
                wish_adaptor.notifyDataSetChanged()
            }
        }

        return view
    }

    /**
     * This is the method that populates the
     * list with pre-saved data. If the
     * User saved data previous he/she
     * will be able to view them here.
     */
    fun populateWishList() {
        Realm.init(activity)
        val realm = Realm.getDefaultInstance()

        wishes = ArrayList<String>()
        val stored_wishes = realm.where(Wish::class.java).findAll()

        for (wish in stored_wishes) {
            wishes.add(wish.name)
        }

        wish_adaptor = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, wishes);

        wish_list.adapter = wish_adaptor

        wish_list.deferNotifyDataSetChanged()

        wish_list.onItemLongClickListener = AdapterView.OnItemLongClickListener { arg0, arg1, pos, id ->
            var removeBook = AlertDialog.Builder(activity)
            removeBook.setMessage("Are you sure you want to remove " + wishes.get(pos) + "?")
            removeBook.setCancelable(true)

            removeBook.setPositiveButton("Yes") { dialog, id ->
                realm.beginTransaction()
                var removeWish = realm.where(Wish::class.java).equalTo("name", wishes.get(pos)).findFirst()
                removeWish!!.deleteFromRealm()
                realm.commitTransaction()
                wishes.remove(wishes.get(pos))
                wish_adaptor.notifyDataSetChanged()
            }

            removeBook.setNegativeButton("No",null);

            removeBook.create().show()

            true
        }
    }
}
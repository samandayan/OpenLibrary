package com.albert.openlibrary.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.albert.openlibrary.backendCallers.Controller
import com.albert.openlibrary.OpenLibraryApplication
import com.albert.openlibrary.R
import com.albert.openlibrary.beans.Docs
import com.squareup.otto.Subscribe

/**
 * This is the fragment that gets returned
 * when the user selects the tab to search
 * for a book.
 */
class SearchFragment : Fragment() {

    lateinit var search : AutoCompleteTextView
    lateinit var adapter : ArrayAdapter<String?>
    var books = arrayOf<Docs>()
    var controller : Controller = Controller()
    var titles = arrayOfNulls<String>(0)

    /**
     * This is where the layout for search is created.
     * inflater: This object creates the layout.
     * container: This is the parent class.
     * savedIntanceState: This is the set of data passed
     * to this fragment.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedIntanceState :Bundle ?) : View? {
        var view = inflater.inflate(R.layout.fragment_search, container, false);

        OpenLibraryApplication.getDefaultBus().register(this)

        search = view.findViewById(R.id.search)

        search.setOnItemClickListener { p0, p1, position, p3 ->
            var showDetails = AlertDialog.Builder(activity)
            var message = "Book name: " + books[position].title + "\n"
            message += "Author Name: " + books[position].author_name + "\n"
            message += "Publish Year: " + books[position].first_publish_year
            showDetails.setMessage(message)

            showDetails.setPositiveButton("OK", null)
            showDetails.create().show()
        }

        search.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(searchCriteria: Editable?) {
                controller.start(searchCriteria.toString().replace(" ", "+"));
            }
        })

        return view;
    }

    /**
     * Using the bus the data is
     * retrieved here after the
     * Controller class publishes
     * the result.
     * This is where the data gets
     * displayed to the user.
     */
    @Subscribe
    fun getMessage(books : Array<Docs>) {
        if (books == null || books.isEmpty())
            return

        this.books = books

        titles = arrayOfNulls(books.size)

        for(i in books.indices)
            titles[i] = books[i].title

        adapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, titles)
        search.setAdapter(adapter)
    }
}
package com.albert.openlibrary.beans

/**
 * This is the top root class
 * that is returned by the backend
 * after data is received from the server.
 */
class Book {
    var docs: Array<Docs>? = null

    var start: String? = null

    var num_found: String? = null

    override fun toString(): String {
        return "ClassPojo [docs = $docs, start = $start, num_found = $num_found]"
    }
}
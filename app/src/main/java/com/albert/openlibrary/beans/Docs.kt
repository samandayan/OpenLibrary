package com.albert.openlibrary.beans

/**
 * This class is used in Book.kt which
 * uses this class to give more information
 * about the searched book.
 */
class Docs {
    var cover_i: String? = null

    var author_name: Array<String>? = null

    var edition_count: String? = null

    var first_publish_year: String? = null

    var author_key: Array<String>? = null

    var ia: Array<String>? = null

    var public_scan_b: String? = null

    var has_fulltext: String? = null

    var title: String? = null

    var key: String? = null

    override fun toString(): String {
        return "ClassPojo [cover_i = $cover_i, author_name = $author_name, edition_count = $edition_count, first_publish_year = $first_publish_year, author_key = $author_key, ia = $ia, public_scan_b = $public_scan_b, has_fulltext = $has_fulltext, name = $title, key = $key]"
    }
}
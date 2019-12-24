package com.albert.openlibrary.beans

import io.realm.RealmModel
import io.realm.annotations.RealmClass

/**
 * This is the class that
 * allows the user to store
 * a book of their choosing.
 * This is stored in the Realm
 * database, and may be removed
 * at anytime of their choosing.
 */
@RealmClass
open class Wish(_name: String) : RealmModel {

    var name = _name

    constructor() :this("")

}
package com.guidoapps.firestorerecycleradaptersample

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class ClassResponse {
    var name: String? = null
    var title: String? = null
    var dueDate: String? = null

    constructor(name: String, title: String, company: String) {
        this.name = name
        this.title = title
        this.dueDate = dueDate
    }
}
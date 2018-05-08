package com.obsidianstudios.alunoifmg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.firestore.FirebaseFirestoreException
import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.guidoapps.firestorerecycleradaptersample.ClassResponse


class MainActivity : AppCompatActivity() {

    private var db: FirebaseFirestore? = null
    private var adapter: FirestoreRecyclerAdapter<ClassResponse, FriendsHolder>? = null
    var dayOfTheWeek: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Weather Icons Reference
        // http://erikflowers.github.io/weather-icons/

        init()
        getClasses()

        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        dayOfTheWeek = sdf.format(d)

        Log.d("TAG", dayOfTheWeek)

    }

    private fun init() {
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.setHasFixedSize(true)
        db = FirebaseFirestore.getInstance()
    }

    private fun getClasses() {
        val query = db!!.collection("courses").document("sistemas-informacao").collection("classes")

        val docRef = db!!.collection("courses").document("sistemas-informacao").collection("classes").document(dayOfTheWeek)
        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document.exists()) {
                    Log.d("TAG", document.toString())
//                    Log.d(FragmentActivity.TAG, "DocumentSnapshot data: " + document.data)
                } else {
//                    Log.d(FragmentActivity.TAG, "No such document")
                }
            } else {
//                Log.d(FragmentActivity.TAG, "get failed with ", task.exception)
            }
        }

        Log.d("TAG", query.toString())

        val response = FirestoreRecyclerOptions.Builder<ClassResponse>()
                .setQuery(query!!, ClassResponse::class.java)
                .build()

        Log.d("TAG", response.toString())

        adapter = object : FirestoreRecyclerAdapter<ClassResponse, FriendsHolder>(response) {
            override fun onBindViewHolder(holder: FriendsHolder, position: Int, model: ClassResponse) {
//                studentClass.text = "Diego"
//                time.text = model.dueDate
                holder.textName?.text = model.name
//                holder.textName.text(model.getName())
//                holder.textTitle.setText(model.getTitle())
//                holder.textCompany.setText(model.getCompany())

//                holder.itemView.setOnClickListener({ v ->
//                    Snackbar.make(friendList, model.getName() + ", " + model.getTitle() + " at " + model.getCompany(), Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show()
//                })
            }

            override fun onCreateViewHolder(group: ViewGroup, i: Int): FriendsHolder {
                val view = LayoutInflater.from(group.context)
                        .inflate(R.layout.class_item, group, false)

                return FriendsHolder(view)
            }

            override fun onError(e: FirebaseFirestoreException) {
                Log.e("error", e.message)
            }

        }

        (adapter as FirestoreRecyclerAdapter<ClassResponse, FriendsHolder>).notifyDataSetChanged()
        recycler.adapter = adapter
    }

    inner class FriendsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textName: TextView? = itemView.findViewById(R.id.studentClass)
//        internal var imageView: CircleImageView? = null
//        internal var textTitle: TextView? = null
//        internal var textCompany: TextView? = null

        init {
//            ButterKnife.bind(this, itemView)
        }
    }

}

package com.craftingsoftware.androidshow.ui.users

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.craftingsoftware.androidshow.R
import com.craftingsoftware.androidshow.adapter.LocalUsersAdapter
import com.craftingsoftware.androidshow.persistence.User
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_local_users.*
import timber.log.Timber


/**
 * Created by constantin.cheptea
 * on 22/05/2018.
 */
class Users : Fragment() {

    private lateinit var viewModel: UsersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_local_users, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)

        usersRecycler.layoutManager = LinearLayoutManager(context)
        usersRecycler.setHasFixedSize(true)

        viewModel.getUsers().observe(this, Observer<List<User>> {
            usersRecycler.adapter = LocalUsersAdapter(it ?: emptyList())
        })

        addUserButton.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.view_add_new_user, null)
            val addUserDialog: AlertDialog.Builder = AlertDialog.Builder(context!!)
            addUserDialog.setView(view)
            addUserDialog.setTitle(context!!.resources.getString(R.string.add_new_user))
            addUserDialog.setPositiveButton(context!!.resources.getString(R.string.add)) { _, _ ->
                val fName: EditText = view.findViewById(R.id.user_first_name)
                val lName: EditText = view.findViewById(R.id.user_last_name)
                Single
                        .fromCallable {
                            val newUser = User(firstName = fName.text.toString(), lastName = lName.text.toString())
                            viewModel.saveUser(newUser)
                        }
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(onError = { error ->
                            Timber.e("Couldn't write User to database -> $error")
                        })
            }
            addUserDialog.setNegativeButton(context!!.resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            addUserDialog.show()
        }
    }
}
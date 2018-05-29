package com.craftingsoftware.androidshow.di

import android.app.Application
import com.craftingsoftware.androidshow.ui.github.GithubUsersViewModel
import com.craftingsoftware.androidshow.ui.users.UsersViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by constantin.cheptea
 * on 22/05/2018.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(githubUsersViewModel: GithubUsersViewModel)
    fun inject(usersViewModel: UsersViewModel)
}
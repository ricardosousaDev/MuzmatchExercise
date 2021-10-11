package com.ricardo.muzmatchexercise.di

import com.ricardo.muzmatchexercise.MuzmatchExerciseApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (AppModule::class),
        (DatabaseModule::class),
        (ActivityBindingModule::class),
        (ViewModelModule::class)
    ]
)

interface AppComponent : AndroidInjector<MuzmatchExerciseApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MuzmatchExerciseApp): Builder

        fun build(): AppComponent
    }
}

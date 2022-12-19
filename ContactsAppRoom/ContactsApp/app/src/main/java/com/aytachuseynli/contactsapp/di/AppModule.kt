package com.aytachuseynli.contactsapp.di

import android.content.Context
import androidx.room.Room
import com.aytachuseynli.contactsapp.data.datasource.PersonDataSource
import com.aytachuseynli.contactsapp.data.repo.PersonRepository
import com.aytachuseynli.contactsapp.room.MyDatabase
import com.aytachuseynli.contactsapp.room.PersonsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton

    fun providePersonRepository(pds:PersonDataSource): PersonRepository{
        return PersonRepository(pds)
    }

    @Provides
    @Singleton
    fun providePersonDataSource(pdao: PersonsDao): PersonDataSource{
        return PersonDataSource(pdao)
    }

    @Provides
    @Singleton
    fun providePersonDao(@ApplicationContext context: Context): PersonsDao{
        val db = Room.databaseBuilder(context, MyDatabase::class.java,"contacts.sqlite")
            .createFromAsset("contacts.sqlite").build()
        return db.getPersonsDao()
    }
}
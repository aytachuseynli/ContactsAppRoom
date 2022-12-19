package com.aytachuseynli.contactsapp.data.repo

import com.aytachuseynli.contactsapp.data.datasource.PersonDataSource
import com.aytachuseynli.contactsapp.data.entity.Persons

class PersonRepository(var pds: PersonDataSource) {
    suspend fun save(name: String,phone:String) = pds.save(name, phone)
    suspend fun update(id:Int, name: String,phone:String) = pds.update(id, name, phone)
    suspend fun delete(id:Int) = pds.delete(id)
    suspend fun loadPersons(): List<Persons> = pds.loadPersons()
    suspend fun search(searchText:String): List<Persons> = pds.search(searchText)

    }
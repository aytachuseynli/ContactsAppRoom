package com.aytachuseynli.contactsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.aytachuseynli.contactsapp.data.repo.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (var prepo: PersonRepository): ViewModel() {
    fun update(id:Int, name: String,phone:String){
        CoroutineScope(Dispatchers.Main).launch{
            prepo.update(id, name, phone)

    }

}
}


package com.example.aplicacion.ui

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    val warriors: List<Warrior> = getWarriors()

    private val _favoriteWarriors = mutableStateListOf<Int>()
    val favoriteWarriors: List<Int> = _favoriteWarriors

    private val _profileImageUri = mutableStateOf<Uri?>(null)
    val profileImageUri: State<Uri?> = _profileImageUri

    fun toggleFavorite(warriorId: Int) {
        if (_favoriteWarriors.contains(warriorId)) {
            _favoriteWarriors.remove(warriorId)
        } else {
            _favoriteWarriors.add(warriorId)
        }
    }

    fun setProfileImage(uri: Uri) {
        _profileImageUri.value = uri
    }
}

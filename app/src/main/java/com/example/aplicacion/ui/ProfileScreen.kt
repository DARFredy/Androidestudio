package com.example.aplicacion.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, profileViewModel: ProfileViewModel = viewModel()) {
    val imageUri = profileViewModel.profileImageUri.value

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { profileViewModel.setProfileImage(it) }
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (imageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUri),
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
                    .clickable { galleryLauncher.launch("image/*") }
            )
        } else {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "Foto de perfil por defecto",
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
                    .clickable { galleryLauncher.launch("image/*") }
            )
        }

        Spacer(Modifier.height(16.dp))
        Text("Mi Perfil", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        Button(onClick = { galleryLauncher.launch("image/*") }) {
            Text("Cambiar Foto")
        }

        Spacer(Modifier.height(24.dp))
        Text("Mis Favoritos", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        val favoriteWarriors = profileViewModel.warriors.filter { profileViewModel.favoriteWarriors.contains(it.id) }
        if (favoriteWarriors.isEmpty()) {
            Text("Aún no tienes guerreros favoritos.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(favoriteWarriors) { warrior ->
                    FavoriteWarriorCard(warrior)
                }
            }
        }
    }
}

@Composable
fun FavoriteWarriorCard(warrior: Warrior) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = warrior.imageRes),
                contentDescription = warrior.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(64.dp).clip(CircleShape)
            )
            Spacer(Modifier.width(16.dp))
            Text(warrior.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

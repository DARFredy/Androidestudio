package com.example.aplicacion.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, profileViewModel: ProfileViewModel = viewModel()) {
    WarriorsContent(modifier, profileViewModel, profileViewModel.warriors)
}

@Composable
fun WarriorsContent(modifier: Modifier = Modifier, profileViewModel: ProfileViewModel, allWarriors: List<Warrior>) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredWarriors = if (searchQuery.isEmpty()) {
        allWarriors
    } else {
        allWarriors.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    val samurais = filteredWarriors.filter { it.type == WarriorType.SAMURAI }
    val ninjas = filteredWarriors.filter { it.type == WarriorType.NINJA }

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(searchQuery) { newQuery -> searchQuery = newQuery }
        Spacer(Modifier.height(16.dp))

        if (samurais.isNotEmpty()) {
            WarriorSection("Samuráis", samurais, profileViewModel)
            Spacer(Modifier.height(16.dp))
        }

        if (ninjas.isNotEmpty()) {
            WarriorSection("Ninjas", ninjas, profileViewModel)
        }
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Icono de búsqueda") },
        placeholder = { Text("Buscar guerrero...") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun WarriorSection(title: String, warriors: List<Warrior>, profileViewModel: ProfileViewModel, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(warriors) { warrior ->
                WarriorCard(warrior, profileViewModel)
            }
        }
    }
}

@Composable
fun WarriorCard(warrior: Warrior, profileViewModel: ProfileViewModel, modifier: Modifier = Modifier) {
    val isFavorite = profileViewModel.favoriteWarriors.contains(warrior.id)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(
                painter = painterResource(id = warrior.imageRes),
                contentDescription = warrior.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            IconButton(
                onClick = { profileViewModel.toggleFavorite(warrior.id) },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Marcar como favorito",
                    tint = if (isFavorite) MaterialTheme.colorScheme.primary else Color.Gray
                )
            }
        }
        Text(
            text = warrior.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

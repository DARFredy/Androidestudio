package com.example.aplicacion.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, 
    profileViewModel: ProfileViewModel,
    onWarriorClicked: (Int) -> Unit
) {
    WarriorsContent(modifier, profileViewModel, profileViewModel.warriors, onWarriorClicked)
}

@Composable
fun WarriorsContent(
    modifier: Modifier = Modifier, 
    profileViewModel: ProfileViewModel, 
    allWarriors: List<Warrior>,
    onWarriorClicked: (Int) -> Unit
) {
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
        SearchBar(searchQuery) { newQuery -> searchQuery = newQuery }
        
        if (samurais.isNotEmpty()) {
            WarriorSection("Samuráis", samurais, profileViewModel, onWarriorClicked)
            Spacer(Modifier.height(16.dp))
        }

        if (ninjas.isNotEmpty()) {
            WarriorSection("Ninjas", ninjas, profileViewModel, onWarriorClicked)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    Box(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Icono de búsqueda", tint = Color.Gray) },
            placeholder = { Text("Buscar guerrero...", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray
            )
        )
    }
}

@Composable
fun WarriorSection(
    title: String, 
    warriors: List<Warrior>, 
    profileViewModel: ProfileViewModel, 
    onWarriorClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
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
                WarriorCard(warrior, profileViewModel, onWarriorClicked)
            }
        }
    }
}

@Composable
fun WarriorCard(
    warrior: Warrior, 
    profileViewModel: ProfileViewModel, 
    onWarriorClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val isFavorite = profileViewModel.favoriteWarriors.contains(warrior.id)

    Column(
        modifier = modifier.clickable { onWarriorClicked(warrior.id) },
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

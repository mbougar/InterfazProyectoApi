package com.es.interfazproyectoapi.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.es.interfazproyectoapi.R
import com.es.interfazproyectoapi.ui.theme.HarryP
import com.es.interfazproyectoapi.ui.theme.HarryPTypography

@Composable
fun PortadaScreen(navController: NavController) {
    Image(
        painter = painterResource(R.drawable.backgound),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Bienvenido al mundo de Harry Potter",
                style = HarryPTypography.bodyLarge,
                color = Color.White,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("api") },
            modifier = Modifier,
            border = BorderStroke(2.dp, Color.White)
        ) {
            Text(text = "Entrar")
        }
    }
}
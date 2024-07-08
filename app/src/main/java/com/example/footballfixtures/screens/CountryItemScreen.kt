package com.example.footballfixtures.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.footballfixtures.R
import com.example.footballfixtures.dataclasses.CountryItems

@Composable
fun CountryItemScreen(
    country: CountryItems,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onClick: (countryId: String,screen:Int) -> Unit
    ) {
    
    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()
            .size(250.dp)
            .clip(RoundedCornerShape(8.dp))
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.Crop
            )
            .border(1.dp, Color(0xFFEEEEEE)),


    ) {
        Column(modifier= Modifier
            .fillMaxWidth()
            .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(country.image_path)
                    .crossfade(true)
                    .build(),

                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .border(5.dp, Color.White, CircleShape)
            )

        Text(
            text = country.name,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, 10.dp),
            style = MaterialTheme.typography.bodyMedium
        )

            Text(text = country.official_name,
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(0.dp, 5.dp),
                style = MaterialTheme.typography.bodyMedium)
   
   Row (modifier = Modifier
           .padding(horizontal = 5.dp)
       .fillMaxWidth()) {
       Button(onClick ={ onClick(country.id,0)}, //navController.navigate("Teams")
           ) {
           Text(text = "Teams",Modifier.padding(start = 10.dp))
       }
       Spacer(modifier = Modifier.width(10.dp))
       Button(onClick = { onClick(country.id,1)},) {
           Text(text = "Leagues",Modifier.padding(start = 10.dp))
       }
       Spacer(modifier = Modifier.width(10.dp))
       Button(onClick ={ onClick(country.id,2)},) {
           Text(text = "Players",Modifier.padding(start = 10.dp))
       }

   }
    }
    }
    
}




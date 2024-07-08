package com.example.footballfixtures.screens

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.footballfixtures.R

import com.example.footballfixtures.dataclasses.LeaguesItem

@Composable
fun LeagueItemScreen(
    league: LeaguesItem,
    modifier: Modifier = Modifier,
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
                    .data(league.image_path)
                    .crossfade(true)
                    .build(),

                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(5.dp, Color.White, CircleShape)
            )

        Text(
            text = league.name,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, 10.dp),
            style = MaterialTheme.typography.bodyMedium
        )
            Text(text = league.sub_type,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(0.dp, 15.dp),

                style = MaterialTheme.typography.bodyMedium)
            Text(text = "Date: "+league.last_played_at.split(" ")[0],
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(0.dp, 5.dp),
                style = MaterialTheme.typography.bodyMedium)
    }
    }
}




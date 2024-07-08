package com.example.footballfixtures.screens

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.footballfixtures.R
import com.example.footballfixtures.dataclasses.FixtureItems

@Composable
fun FixtureItemScreen(
    fixture: FixtureItems,
  /*  onClick: () -> Unit,*/
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =modifier,
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id= R.color.lavender), //Card background color
            contentColor = Color.Black  //Card content color,e.g.text
        )
    ) {
        Text(
            text = fixture.name,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(all = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        Row {
            Text(text = "Date: "+fixture.starting_at.split(" ")[0],
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
                modifier =  Modifier.padding(all = 8.dp).weight(1f))
            Text(text ="Time: "+fixture.starting_at.split(" ")[1],
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
                modifier =  Modifier.padding(all = 8.dp).weight(1f))
        }

        Box(modifier = modifier,contentAlignment = Alignment.Center) {
            MySurface(modifier = Modifier.align(Alignment.Center), fixture = fixture)

        }
    }

}

@Composable
fun MySurface(modifier: Modifier, fixture: FixtureItems) {
    Surface(
       modifier = modifier
           .fillMaxWidth()
           .padding(all = 10.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color.Transparent,
        contentColor = colorResource(id = R.color.black),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(
            text = fixture.result_info,
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(all = 8.dp),

        )
    }
}



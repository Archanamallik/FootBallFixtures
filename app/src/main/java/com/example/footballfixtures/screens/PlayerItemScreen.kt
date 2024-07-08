package com.example.footballfixtures.screens

import android.graphics.drawable.shapes.OvalShape
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
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
import com.example.footballdetails.dataclass.PlayersItems
import com.example.footballfixtures.R
import com.example.footballfixtures.dataclasses.FixtureItems
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlayerItemScreen(
    player: PlayersItems,
  /*  onClick: () -> Unit,*/
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(width = 150.dp, height = 190.dp)
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
                    .data(player.image_path)
                    .crossfade(true)
                    .build(),

                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(5.dp, Color.White, CircleShape)
            )

            Text(
                text = player.common_name,

                color = Color.Black,
                modifier = Modifier.padding(0.dp, 2.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            player.gender?.let { nonNullResult ->
                Text(
                    text = nonNullResult,

                    color = Color.Black,
                    modifier = Modifier.padding(0.dp, 2.dp),

                    style = MaterialTheme.typography.bodyMedium
                )
            }
            player.date_of_birth?.let { nonNullResult ->


                Text(
                    text = "Age: " + getAge(nonNullResult),

                    color = Color.Black,
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(0.dp, 2.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
private fun getAge(dateOfBirth: String?): String {
    if(dateOfBirth==null)
        return ""
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") //1984-12-12
    val localDate = LocalDate.parse(dateOfBirth, formatter)
    val year=localDate.year
    val month=localDate.month
    val dayOfMonth=localDate.dayOfMonth
    return Period.between(
        LocalDate.of(year, month, dayOfMonth),
        LocalDate.now()
    ).years.toString()
}

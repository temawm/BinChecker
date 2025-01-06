package com.example.binchecker.presentation.bin_find_info

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.binchecker.R

@Composable
fun BinFindInfoScreen(viewModel: BinFindInfoViewModel) {

    val uiState = viewModel.uiState.collectAsState()
    val binState = viewModel.binState.collectAsState().value
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(140.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(text = "IIN/BIN", fontSize = 24.sp)
            TextField(
                value = uiState.value.binField,
                onValueChange = {
                    viewModel.updateState(it)
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .width(155.dp)
                    .height(60.dp)
                    .padding(start = 12.dp, end = 12.dp)
                    .border(
                        1.dp,
                        if (uiState.value.isError) colorResource(id = R.color.red_soft) else Color.LightGray,
                        RoundedCornerShape(18.dp)
                    ),
                textStyle = TextStyle(
                    fontSize = 24.sp
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.width(12.dp))

            if (uiState.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = colorResource(id = R.color.author)
                )
            }

        }

        Spacer(modifier = Modifier.height(60.dp))
        if (binState != null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight()
                            .padding(start = 30.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "SСHEME/NETWORK",
                            fontSize = 14.sp,
                            color = Color.Gray,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = binState.scheme ?: "?",
                            fontSize = 18.sp
                        )

                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight()
                            .padding(start = 70.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "TYPE",
                            fontSize = 14.sp,
                            color = Color.Gray,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = binState.type ?: "?",
                            fontSize = 18.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight()
                            .padding(start = 30.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "BRAND",
                            fontSize = 14.sp,
                            color = Color.Gray,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = binState.brand ?: "?",
                            fontSize = 18.sp
                        )

                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight()
                            .padding(start = 70.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "PREPAID",
                            fontSize = 14.sp,
                            color = Color.Gray,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = when (binState.prepaid) {
                                null -> "?"
                                true -> "Yes"
                                false -> "No"
                            },
                            fontSize = 18.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight()
                            .padding(start = 30.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "BANK",
                            fontSize = 14.sp,
                            color = Color.Gray,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = binState.bank?.name?.let {
                                if (it.length > 25) it.take(25) + "..." else it
                            } ?: "?",
                            fontSize = 18.sp,

                        )
                        Text(
                            text = binState.bank?.url ?: "?",
                            fontSize = 12.sp,
                            color = if (binState.bank?.url != null) colorResource(id = R.color.author) else Color.Black,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable {
                                val url = binState.bank?.url
                                if (!url.isNullOrEmpty()) {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(intent)
                                }
                            }
                        )
                        Text(
                            text = binState.bank?.phone ?: "?",
                            fontSize = 14.sp,
                            color = if (binState.bank?.phone != null) colorResource(id = R.color.author) else Color.Black,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable {
                                val phone = binState.bank?.phone
                                if (!phone.isNullOrEmpty()) {
                                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
                                    context.startActivity(intent)
                                }
                            }
                        )

                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight()
                            .padding(start = 70.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "COUNTRY",
                            fontSize = 14.sp,
                            color = Color.Gray,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = binState.country?.name?.let {
                                if (it.length > 25) it.take(25) + "..." else it
                            } ?: "?",
                            maxLines = 1,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "latitude: ${binState.country?.latitude ?: "?"}, longitude: ${binState.country?.longitude ?: "?"}",
                            fontSize = 12.sp,
                            color = if (binState.country?.latitude != null && binState.country.longitude != null) colorResource(
                                id = R.color.author
                            ) else Color.Black,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable {
                                val latitude = binState.country?.latitude
                                val longitude = binState.country?.longitude
                                if (latitude != null && longitude != null) {
                                    val uri = Uri.parse("geo:$latitude,$longitude")
                                    val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                                    mapIntent.setPackage("com.google.android.apps.maps")
                                    if (mapIntent.resolveActivity(context.packageManager) != null) {
                                        context.startActivity(mapIntent)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
        Button(
            onClick = {viewModel.sendBin(uiState.value.binField)},
            modifier = Modifier
                .height(60.dp)
                .width(130.dp)
                .background(colorResource(id = R.color.author)),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.author))
        ) {
            Text(
                text = "Lookup",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
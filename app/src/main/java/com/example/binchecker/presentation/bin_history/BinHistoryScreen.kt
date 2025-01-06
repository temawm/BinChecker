package com.example.binchecker.presentation.bin_history

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.binchecker.R

@Composable
fun BinHistoryScreen(viewModel: BinHistoryViewModel){
    val binListState = viewModel.binsList.collectAsState().value
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Row {
            Text(
                text = "HISTORY:",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        binListState.forEach{ bin ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .border(1.dp,  Color.Gray, RoundedCornerShape(32.dp)),
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
                            text = bin.scheme ?: "?",
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
                            text = bin.cardType ?: "?",
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
                            text = bin.brandName ?: "?",
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
                            text = when (bin.prepaid) {
                                "null" -> "?"
                                "true" -> "Yes"
                                "false" -> "No"
                                else -> {""}
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
                            text = bin.bankName?.let {
                                if (it.length > 25) it.take(25) + "..." else it
                            } ?: "?",
                            fontSize = 18.sp,

                            )
                        Text(
                            text = bin.bankUrl ?: "?",
                            fontSize = 12.sp,
                            color = if (bin.bankUrl != null) colorResource(id = R.color.author) else Color.Black,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable {
                                val url = bin.bankUrl
                                if (!url.isNullOrEmpty()) {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(intent)
                                }
                            }
                        )
                        Text(
                            text = bin.bankPhone ?: "?",
                            fontSize = 14.sp,
                            color = if (bin.bankPhone != null) colorResource(id = R.color.author) else Color.Black,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable {
                                val phone = bin.bankPhone
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
                            text = bin.countryName?.let {
                                if (it.length > 25) it.take(25) + "..." else it
                            } ?: "?",
                            maxLines = 1,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "latitude: ${bin.latitude ?: "?"}, longitude: ${bin.longitude ?: "?"}",
                            fontSize = 12.sp,
                            color = if (bin.latitude != null && bin.longitude != null) colorResource(
                                id = R.color.author
                            ) else Color.Black,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable {
                                val latitude = bin.latitude
                                val longitude = bin.longitude
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
        }
    }

package com.example.sougna.presentation.view.TextFeild.Product

import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImagePicker(
    selectedImages: List<Uri?>,
    onImageClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Picture of Product", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(27.dp),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(selectedImages.size) { index ->
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                            .clickable { onImageClick(index) }
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedImages[index] != null) {
                            Image(
                                painter = rememberAsyncImagePainter(selectedImages[index]),
                                contentDescription = "Selected Image",
                                modifier = Modifier.size(80.dp)
                            )
                        } else {
                            Icon(Icons.Default.Image, contentDescription = "Upload Image", tint = Color.DarkGray)
                        }
                    }
                }
            }
        }
    }
}
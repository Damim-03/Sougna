package com.example.sougna.presentation.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sougna.R
import com.example.sougna.presentation.view.TextFeild.Profile.*
import com.example.sougna.presentation.view.buttons.ProductButton.*
import com.example.sougna.presentation.view.buttons.ProfileButton.*

@Composable
fun ProfilePage(navController: NavHostController) {
    var name by remember { mutableStateOf("Imadeddine Kir") }
    var emailAddress by remember { mutableStateOf("Imadeddinekir95@gmail.com") }
    var password by remember { mutableStateOf("Helloworld") }
    var countryCode by remember { mutableStateOf("+213") }
    var phoneNumber by remember { mutableStateOf("673200104") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Section with Background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(Color(0xFFE57373), RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProfileButton(onClick = { navController.navigate("profile") })
                    AddProductButton(navController = navController)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Profile Image
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User Input Fields
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NameProfile(Name = name, onName = { name = it })
            EmailProfile(Email = emailAddress, onEmail = { emailAddress = it })
            PasswordProfile(Password = password, onPassword = { password = it })
            PhoneProfile(
                countryCode = countryCode,
                onCountryCodeChange = { countryCode = it },
                phoneNumber = phoneNumber,
                onPhoneNumberChange = { phoneNumber = it }
            )

        }

        Spacer(modifier = Modifier.height(35.dp))

        // Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CancelButton(navController = navController)
            SaveButton(onClick = { /* Handle Save Action */ })
        }
    }
}

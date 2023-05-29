package com.example.blood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.example.blood.ui.theme.BloodTheme

class RequireScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RequirementScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequirementScreen() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Require Blood", fontSize = 45.sp, color = Color.Red, fontFamily = FontFamily.Serif)

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painterResource(id = R.drawable.require),
            contentDescription = "blood",
            modifier = Modifier
                .size(width = 250.dp, height = 250.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gender") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextField(
            value = bloodGroup,
            onValueChange = { bloodGroup = it },
            label = { Text("Blood Group") },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        var showDialog by remember { mutableStateOf(false) }

        Button(
            onClick = { showDialog = true },
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            shape = RectangleShape,
            modifier = Modifier
                .size(width = 120.dp, height = 50.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Require", fontSize = 20.sp, color = Color.Black)
        }

        if (showDialog) {
            Dialog(
                onDismissRequest = { showDialog = false },
                properties = DialogProperties(
                    securePolicy = SecureFlagPolicy.Inherit
                )
            ) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        Button(
                            onClick = { showDialog = false },
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(Color.LightGray)
                        ) {
                            Text(
                                text = "Confirm",
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                    },
                    text = {
                        Text(
                            text = "Name: $name\nAge: $age\n Gender: $gender\n Blood Group: $bloodGroup\n",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                )
            }
        }
    }
}
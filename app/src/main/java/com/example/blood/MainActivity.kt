package com.example.blood

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.example.blood.ui.theme.BloodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Bank()
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainContent(padding: PaddingValues) {

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(padding)
                .consumedWindowInsets(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var isFlipped by remember { mutableStateOf(false) }

            val rotationY by animateFloatAsState(
                targetValue = if (isFlipped) 360f else 0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )


            Image(
                painterResource(id = R.drawable.blood),
                contentDescription = "blood",
                modifier = Modifier
                    .size(width = 250.dp, height = 250.dp)
                    .rotate(rotationY)
                    .clickable { isFlipped = !isFlipped }
            )

            Spacer(modifier = Modifier.height(30.dp))
            Text(
                "A DROP of your Blood is a LIFE saved.",
                color = Color.Red,
                fontFamily = FontFamily.SansSerif,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))
            //Spacer(modifier = Modifier.height(40.dp))

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                var user by remember { mutableStateOf("") }
                TextField(
                    value = user,
                    onValueChange = { user = it },
                    Modifier
                        .padding(vertical = 15.dp)
                        .fillMaxWidth(),
                    placeholder = { Text("Enter Name") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "User Icon"
                        )
                    }
                )

                var mail by remember { mutableStateOf("") }
                TextField(
                    value = mail,
                    onValueChange = { mail = it },
                    Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(),
                    placeholder = { Text("Enter email") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = "User Icon"
                        )
                    }
                )

                var showDialog by remember { mutableStateOf(false) }


                Button(
                    onClick = { showDialog = true },
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                    shape = RectangleShape,
                    modifier = Modifier
                        .size(width = 120.dp, height = 50.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Sign Up", fontSize = 20.sp, color = Color.Black)
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
                                    text = "User: $user\nEmail: $mail",
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
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bank() {

    Scaffold(
        topBar = { CustomTopBar() },
        content = { h -> MainContent(h) },
        bottomBar = { CustomBottomBar() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {

    val context = LocalContext.current
    var abc by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {

                Icon(
                    Icons.Filled.Person, contentDescription = "",
                    modifier = Modifier
                        .size(30.dp),
                )

                Text(
                    text = "Bleeding Red", color = Color.Red, textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Serif, fontSize = 30.sp,
                )

                Box(
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopEnd)
                ) {


                    IconButton(onClick = { abc = !abc }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "More",
                        )
                    }

                    DropdownMenu(
                        expanded = abc,
                        onDismissRequest = { abc = false },
                    ) {
                        DropdownMenuItem(
                            text = { Text("About") },
                            onClick = {
                                Toast.makeText(context, "About", Toast.LENGTH_SHORT).show()
                            },
                        )
                        DropdownMenuItem(
                            text = { Text("Provide") },
                            onClick = {
                                Toast.makeText(context, "Provide", Toast.LENGTH_SHORT).show()
                            },
                        )
                        DropdownMenuItem(
                            text = { Text("Contact") },
                            onClick = {
                                Toast.makeText(context, "Contact", Toast.LENGTH_SHORT).show()
                            },
                        )
                    }
                }
            }

        },

        modifier = Modifier.drawBehind {
            drawLine(
                Color.Black,
                Offset(0f, size.height),
                Offset(size.width, size.height),
                10f,
            )
        },
    )
}

@Composable
fun CustomBottomBar() {
    remember { mutableStateOf(0) }
    BottomAppBar(
        modifier = Modifier.drawBehind {
            drawLine(
                Color.Black,
                Offset(0f, 0f),
                Offset(size.width, 0f),
                4f
            )
        },
        containerColor = Color.White,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment =
            Alignment.CenterVertically
        ) {


            val context = LocalContext.current
            Button(
                onClick = { val intent = Intent(context, DonateScreen::class.java)
                    context.startActivity(intent)

                },
                modifier = Modifier.padding(vertical = 10.dp),
                colors = ButtonDefaults.buttonColors(Color.Red),
            ) {
                Text(text = "Donate", fontSize = 20.sp, color = Color.Black)
            }

            Button(
                onClick = { val intent = Intent(context, RequireScreen::class.java)
                    context.startActivity(intent)},
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text(text = "Require", fontSize = 20.sp, color = Color.Black)
            }

        }
    }

}




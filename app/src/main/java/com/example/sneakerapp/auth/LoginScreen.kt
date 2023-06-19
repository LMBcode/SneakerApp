package com.example.sneakerapp.auth

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakerapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToSignUp : () -> Unit,
    onNavigateToSignIn : () -> Unit
) {
    var email by remember {
        mutableStateOf("")
    }
    var passowrd by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 48.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Sign In",
            fontSize = 25.98.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 48.dp)
        )

        Spacer(modifier = Modifier.height(64.dp)) // Add this line

        Column {
            OutlinedTextField(
                value = email,
                label = { Text(text = "Email") },
                onValueChange = {newText ->
                    email = newText},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            OutlinedTextField(
                value = passowrd,
                label = { Text(text = "Password") },
                onValueChange = {newText ->
                    passowrd = newText},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Button(
                onClick = {onNavigateToSignIn()},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(52.93.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(size = 8.dp)

            ) {
                Text(text = "Sign In",
                    color = colorResource(id = R.color.white),
                    fontSize = 24.sp)
            }
            Text(
                text = "Forgot Password?",
                fontSize = 11.5.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Don't have an account?",
                    fontSize = 13.47.sp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )

                Text(
                    text = "Sign Up",
                    fontSize = 13.47.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 8.dp)
                        .clickable {
                            onNavigateToSignUp()
                            Log.d("TEXT","clicked")
                        }
                )
            }
        }
    }
}
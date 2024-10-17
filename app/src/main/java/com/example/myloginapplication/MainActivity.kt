package com.example.myloginapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MainScreen()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color.White,
            onPrimary = Color.Black,
            secondary = Color(0xFF6200EE),
            onSecondary = Color.White,
            background = Color.White,
            onBackground = Color.Black,
            surface = Color.White,
            onSurface = Color.Black
        )
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("Login") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (currentScreen) {
            "Login" -> LoginScreen(
                onSignUpClick = { currentScreen = "SignUp" },
                onForgotPasswordClick = { currentScreen = "ForgotPassword" },
                onLoginSuccess = { currentScreen = "Home" } // Navigate to Home after login success
            )
            "ForgotPassword" -> ForgotPasswordScreen(
                onBackClick = { currentScreen = "Login" }
            )
            "SignUp" -> SignUpScreen(
                onBackClick = { currentScreen = "Login" }
            )
            "Home" -> HomeScreen() // New Home Screen
        }
    }
}

@Composable
fun LoginScreen(
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoginSuccess: () -> Unit // New login success callback
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        InputField(value = email, onValueChange = { email = it }, label = "Email")
        InputField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true,
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onLoginSuccess() }, // Navigate to Home on login success
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            Text("Sign In", color = Color.Black, fontSize = 20.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = onSignUpClick) {
                Text(
                    "Sign Up",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            TextButton(onClick = onForgotPasswordClick) {
                Text(
                    "Forgot Password?",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Home Screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ForgotPasswordScreen(onBackClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        InputField(value = email, onValueChange = { email = it }, label = "Email")
        InputField(
            value = password,
            onValueChange = { password = it },
            label = "New Password",
            isPassword = true,
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = it }
        )
        InputField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password",
            isPassword = true,
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            Text("Reset Password", color = Color.Black, fontSize = 20.sp)
        }

        TextButton(onClick = onBackClick) {
            Text(
                "Back to Login",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun SignUpScreen(onBackClick: () -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        InputField(value = firstName, onValueChange = { firstName = it }, label = "First Name")
        InputField(value = lastName, onValueChange = { lastName = it }, label = "Last Name")
        InputField(value = email, onValueChange = { email = it }, label = "Email")
        InputField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true,
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = it }
        )
        InputField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password",
            isPassword = true,
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            Text("Sign Up", color = Color.Black, fontSize = 20.sp)
        }

        TextButton(onClick = onBackClick) {
            Text(
                "Back to Login",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordVisibilityChange: (Boolean) -> Unit = {}
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Yellow)
            .border(1.dp, Color.Gray)
            .padding(16.dp),
        singleLine = true,
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        decorationBox = { innerTextField ->
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                if (value.isEmpty()) {
                    Text(label, color = Color.Gray)
                }
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MainScreen()
    }
}

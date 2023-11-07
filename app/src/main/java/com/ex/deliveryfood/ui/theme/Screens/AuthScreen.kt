package com.ex.deliveryfood.ui.theme.Screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ex.deliveryfood.R
import com.ex.deliveryfood.ui.theme.components.TabLayout

@Composable
fun AuthScreen(navController: NavController){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)
    Image(
        modifier = Modifier
            .fillMaxSize(),
        painter = painterResource(id = R.drawable.background),
        contentDescription = "Background",
        contentScale = ContentScale.FillBounds

    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Login Image",
            modifier = Modifier
                .size(250.dp)
                .padding(16.dp)

        )
        Spacer(modifier = Modifier.height(20.dp))

        val selectedTab = remember{
            mutableIntStateOf( 0)
        }
        TabLayout(
            selectedIndex = selectedTab.intValue,
            items = listOf(
                "Sign In" to {
                    Sign_in(navController = navController,
                        sharedPreferences = sharedPreferences
                    )
                },
                "Sign Up" to {Sign_up(navController = navController,
                    sharedPreferences = sharedPreferences
                )}
            ),
            onTabClick ={
                selectedTab.intValue = it
            }

        )

    }
}

@Composable
fun Sign_in(
    navController: NavController,
    sharedPreferences: SharedPreferences
){
    val rememberMeChecked = remember {
        mutableStateOf(false)
    }
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val showPassword = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(22.dp))
        AppTextField(value = email.value,
            onValueChange ={
                email.value =it
            },
            label = "Email-Address"

            )
        Spacer(modifier = Modifier.height(22.dp))
        AppTextField(value = password.value,
            onValueChange ={
                password.value =it
            },
            label = "Password",
            visualTransformation = if(showPassword.value)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
                trailing = {
                    Icon(
                        modifier = Modifier.clickable{
                            showPassword.value = !showPassword.value
                        },
                        painter = painterResource(
                            id = if (showPassword.value)
                                R.drawable.ic_eye_close else R.drawable.ic_eye_open


                        ),
                        contentDescription = null
                    )
                }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End){
            Text(text = "Forgot Password?", color = Color.Gray,fontSize = 11.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(modifier = Modifier
            .height(40.dp)
            .fillMaxWidth(),


            onClick = {
            sharedPreferences.edit().apply(){
                putBoolean("loggedIn",true)
                putString("email",email.value)
            }
                .apply()
            navController.navigate("home"){
                popUpTo(0)
            }
            },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Login")
        }

    }
}

@Composable
fun Sign_up(
    navController: NavController,
    sharedPreferences: SharedPreferences
){
    val rememberMeChecked = remember {
        mutableStateOf(false)
    }
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val passwordRepeat = remember {
        mutableStateOf("")
    }
    val showPassword = remember {
        mutableStateOf(false)
    }
    val showPasswordRepeat = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(22.dp))
        AppTextField(value = email.value,
            onValueChange ={
                email.value =it
            },
            label = "Email-Address"

        )
        Spacer(modifier = Modifier.height(22.dp))
        AppTextField(value = password.value,
            onValueChange ={
                password.value =it
            },
            label = "Password",
            visualTransformation = if(showPassword.value)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailing = {
                Icon(
                    modifier = Modifier.clickable{
                        showPassword.value = !showPassword.value
                    },
                    painter = painterResource(
                        id = if (showPassword.value)
                            R.drawable.ic_eye_close else R.drawable.ic_eye_open


                    ),
                    contentDescription = null
                )
            }
        )

        Spacer(modifier = Modifier.height(22.dp))
        AppTextField(value = passwordRepeat.value,
            onValueChange ={
                passwordRepeat.value =it
            },
            label = "Repeat Password",
            visualTransformation = if(showPasswordRepeat.value)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailing = {
                Icon(
                    modifier = Modifier.clickable{
                        showPasswordRepeat.value = !showPasswordRepeat.value
                    },
                    painter = painterResource(
                        id = if (showPasswordRepeat.value)
                            R.drawable.ic_eye_close else R.drawable.ic_eye_open


                    ),
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(22.dp))
        Button(modifier = Modifier
            .height(40.dp)
            .fillMaxWidth(),

            onClick = {
                sharedPreferences.edit().apply(){
                    putBoolean("loggedIn",true)
                    putString("email",email.value)
                }
                    .apply()
                navController.navigate("home"){
                    popUpTo(0)
                }
            },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Sign Up")
        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String)->Unit,
    label:String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailing:(@Composable ()->Unit)? = null
){
    Column {
        Text(text = label,
            modifier = Modifier.padding(10.dp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier
            .shadow(4.dp, RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .padding(2.dp)
        ){
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                visualTransformation = visualTransformation,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent

                ),
                trailingIcon = trailing
                )
        }

    }
}



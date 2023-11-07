package com.ex.deliveryfood.ui.theme.Screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ex.deliveryfood.R
import com.ex.deliveryfood.ui.theme.components.TabLayout
import com.ex.deliveryfood.ui.theme.ubuntuFont
import com.google.accompanist.systemuicontroller.rememberSystemUiController


data class Food(
    val name:String,
    @DrawableRes val image: Int,
    val type: FoodType,
    val liked: Boolean = false,
    val price: Int = (10..100).random()
)

enum class FoodType{
    Meal,Extras
}

val food = listOf(
    Food(
        name ="Pizza ",
        image = R.drawable.pizza,
        type = FoodType.Meal
    ),
    Food(
        name ="Pasta ",
        image = R.drawable.pasta,
        type = FoodType.Meal
    ),
    Food(
        name ="Sandwich ",
        image = R.drawable.sandwiches,
        type = FoodType.Meal
    ),
    Food(
        name ="Roll",
        image = R.drawable.rolls,
        type = FoodType.Meal
    ),
    Food(
        name ="Momo",
        image = R.drawable.momo,
        type = FoodType.Meal
    ),
    Food(
        name ="Salads",
        image = R.drawable.salad,
        type = FoodType.Extras
    ),
    Food(
        name ="Chicken",
        image = R.drawable.chicken,
        type = FoodType.Meal
    ),
    Food(
        name ="Pastry",
        image = R.drawable.pastry,
        type = FoodType.Extras
    ),
    Food(
        name ="Donuts",
        image = R.drawable.donuts,
        type = FoodType.Extras
    ),
    Food(
        name ="Breakfast",
        image = R.drawable.breakfast,
        type = FoodType.Extras
    ),
    Food(
        name ="Burger",
        image = R.drawable.burger,
        type = FoodType.Meal
    ),
    Food(
        name ="Juice",
        image = R.drawable.juice,
        type = FoodType.Extras
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun foods(items:List<Food>,onLikeChange:(Food)->Unit,onTap:(Food) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            itemsIndexed(items){index,food->
                Card(
                    onClick ={
                        onTap(food)
                    },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ){
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                        contentAlignment = Alignment.TopEnd
                    ){
                        Image(
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    onLikeChange(food)
                                },
                            painter = painterResource(id = if (food.liked) R.drawable.like else R.drawable.unlike),
                            contentDescription = null

                        )
                    }
                    Column(
                        modifier =Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            painter = painterResource(id = food.image),
                            contentDescription = food.name,
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = food.name, fontSize = 15.sp, color= Color.Black)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = "${food.price}$")
                        Spacer(modifier = Modifier.height(10.dp))



                    }
                }

            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){

    val uiController = rememberSystemUiController()
    uiController.isStatusBarVisible = false

    Scaffold( topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Menu", fontFamily = ubuntuFont)
            },
            navigationIcon = {
                Row{
                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(onClick = { navController.navigate("auth") }) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                }
            })
    }

    ) {paddings->
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds
        )
        Column(modifier = Modifier.padding(paddings)){
            val selectableFoodType = remember {
                mutableIntStateOf(0)
            }
            val foodsState = remember{
                mutableStateListOf(*(food + food).toTypedArray())
            }

            val onLikeChange:(Food)->Unit = {
                foodsState[foodsState.indexOf(it)] = foodsState[foodsState.indexOf(it)].copy(liked = !it.liked)
            }
            Spacer(modifier = Modifier.height(16.dp))
            TabLayout(
                items = listOf(
                    "Meals" to {
                        foods(
                            items = foodsState.filter{it.type == FoodType.Meal},
                            onLikeChange = onLikeChange,
                            onTap = {navController.navigate("food")
                            }
                        )
                    },
                    "Side" to {
                        foods(
                            items = foodsState.filter{it.type == FoodType.Extras},
                            onLikeChange = onLikeChange,
                            onTap = {navController.navigate("food")
                            }
                        )
                    }
                ),
                selectedIndex = selectableFoodType.intValue,
                onTabClick = {
                    selectableFoodType.intValue = it
                },
                textHeight = 30.dp,
                indicatorPadding = PaddingValues(horizontal = 10.dp)
            )


        }

    }

}
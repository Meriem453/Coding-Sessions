package com.example.noteapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CategoriesScreen(){
    var showAdd by remember {
        mutableStateOf(false)
    }
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Categories",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                        color = Color.Black
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAdd=true
                },
                contentColor = Color.White,
                containerColor = Color.Black,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription ="add",
                )
            }
        }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), end = 20.dp, start = 20.dp, bottom = 20.dp)
        ){
            LazyColumn (modifier = Modifier
                .fillMaxWidth()
                ){
                val categories = listOf(
                    Category("University",0xFFFFBCBC),
                    Category("Research",0xFFFFECBC),
                    Category("University",0xFFFFBCBC),
                    Category("Research",0xFFFFECBC),
                    Category("University",0xFFFFBCBC),
                    Category("Research",0xFFFFECBC),
                )
                items(categories){ category ->
                    CategoryCard(category)

                }
            }
        }
    }
    if(showAdd){
        AddCategoryDialog{
            showAdd=false
        }
    }
}

@Composable
fun AddCategoryDialog(onDismiss: () -> Unit) {
    var name by remember {
        mutableStateOf("")
    }
    var color by remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = {
        onDismiss()
    }) {
        Column(
            Modifier.background(Color.White)
        ){
            Text(
                text = "Add Category",
                fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
                )
            TextField(
                value = name,
                onValueChange = {name=it},
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            , colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
            ),
                placeholder = {
                    Text(
                        text = "Category",
                        fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                        fontSize = 14.sp,
                        color = Color.Gray,
                    )
                }
            )
            TextField(
                value = color,
                onValueChange = {color=it},
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                , colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black,
                ),
                placeholder = {
                    Text(
                        text = "Color",
                        fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                        fontSize = 14.sp,
                        color = Color.Gray,
                    )
                }
            )
            Text(
                text = "Add",
                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(16.dp)
                    .clickable {
                        //TODO("add category")
                        onDismiss()
                    }
            )

        }
    }

}

@Composable
fun CategoryCard(category: Category) {
    Card (
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    ){
        Row (
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .size(35.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(category.color))
            )
            Text(
                text =category.name,
                fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                fontSize = 16.sp,
                color = Color.Black
            )

        }
    }
}

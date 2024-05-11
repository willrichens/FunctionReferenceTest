package com.example.functionreferencetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.functionreferencetest.ui.theme.FunctionReferenceTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunctionReferenceTestTheme {
                DiceRollerApp()
            }
        }
    }
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableIntStateOf(1) }
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { result = (1..6).random()},) {
            Text(result.toString())
        }
        SecondaryBox(value = result, onValueChanged = ::testPrint)
    }
}

@Composable
fun SecondaryBox(modifier: Modifier = Modifier, value: Int, onValueChanged: (String) -> Unit) {
    println("thisworks")
    TextField(
        value = value.toString(),
        onValueChange = onValueChanged,
        // also this doesn't work {onValueChanged(it)}, nor does this value -> onValueChanged(value)
        textStyle = TextStyle(textAlign = TextAlign.Center)
    )
}

fun testPrint(value: String) {
    println("thisdoesnotwork")
}


@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}
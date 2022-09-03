import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

var oppositeColor = mutableStateOf(Color.Black)
var themeColor = mutableStateOf(Color.White)
var borderColor = mutableStateOf(Color.Gray)

var game00text = mutableStateOf(" ")
var game01text = mutableStateOf(" ")
var game02text = mutableStateOf(" ")
var game10text = mutableStateOf(" ")
var game11text = mutableStateOf(" ")
var game12text = mutableStateOf(" ")
var game20text = mutableStateOf(" ")
var game21text = mutableStateOf(" ")
var game22text = mutableStateOf(" ")

var user1turn = mutableStateOf(true)
var roundCount = mutableStateOf(0)

var toastMessageWidthState = mutableStateOf(0.dp)
var toastMessageHeightState = mutableStateOf(0.dp)

var user1score = mutableStateOf(0)
var user2score = mutableStateOf(0)

var user1scoreText = mutableStateOf("User 1 Score : 0")
var user2scoreText = mutableStateOf("User 2 Score : 0")

var toastMessageText = mutableStateOf("Toast Message!")
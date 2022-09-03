// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Tic-Tac-Toe Jetpack Compose",
        icon = painterResource("tictactoe_icon.png")
    ) {
        App()
    }
}

fun isEmpty(clickerTitle: String): Boolean {
    return clickerTitle != "X" && clickerTitle != "O"
}

fun newText(user1turn: Boolean): String {
    return if (user1turn) "X" else "O"
}

fun checkForWin() : Boolean {
    for (i in 0..2) {
        if (fields()[i][0] == fields()[i][1]
            && fields()[i][0] == fields()[i][2]
            && fields()[i][0] != " "
        ) {
            return true
        }
    }
    for (i in 0..2) {
        if (fields()[0][i] == fields()[1][i]
            && fields()[0][i] == fields()[2][i]
            && fields()[0][i] != " "
        ) {
            return true
        }
    }
    if (fields()[0][0] == fields()[1][1]
        && fields()[0][0] == fields()[2][2]
        && fields()[0][0] != " "
    ) {
            return true
    }
    if (fields()[0][2] == fields()[1][1]
        && fields()[0][2] == fields()[2][0]
        && fields()[0][2] != " "
    ) {
            return true
    }
    return false
}

fun changeTheme(isDarkTheme: Boolean) {
    if (isDarkTheme) {
        oppositeColor.value = Color.White
        themeColor.value = Color.Black
        borderColor.value = Color.White
    } else {
        oppositeColor.value = Color.Black
        themeColor.value = Color.White
        borderColor.value = Color.Gray
    }
}

fun resetTable() {
    game00text.value = " "
    game01text.value = " "
    game02text.value = " "
    game10text.value = " "
    game11text.value = " "
    game12text.value = " "
    game20text.value = " "
    game21text.value = " "
    game22text.value = " "
    roundCount.value = 0
    user1turn.value = true
}

fun fields() : ArrayList<ArrayList<String>> {
    val firstColumn = arrayListOf(game00text.value, game01text.value, game02text.value)
    val secondColumn = arrayListOf(game10text.value, game11text.value, game12text.value)
    val thirdColumn = arrayListOf(game20text.value, game21text.value, game22text.value)
    return arrayListOf(firstColumn, secondColumn, thirdColumn)
}

fun showToastMessage() {
    GlobalScope.launch {
        toastMessageWidthState.value = 160.dp
        toastMessageHeightState.value = 50.dp
        delay(3000)
        toastMessageWidthState.value = 0.dp
        toastMessageHeightState.value = 0.dp
    }
}

fun gameButtonOnClick() {
    if (checkForWin()) {
        if (user1turn.value) {
            user1score.value++
            user1scoreText.value = "User 1 Score : ${user1score.value}"
            toastMessageText.value = "User 1 Wins!"
            showToastMessage()
        } else {
            user2score.value++
            user2scoreText.value = "User 2 Score : ${user2score.value}"
            toastMessageText.value = "User 2 Wins!"
            showToastMessage()
        }
        resetTable()
    } else if (roundCount.value == 9) {
        toastMessageText.value = "Draw!"
        showToastMessage()
        resetTable()
    } else {
        user1turn.value = !user1turn.value
    }
}
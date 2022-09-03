import androidx.compose.animation.core.animateDpAsState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*

@Composable
@Preview
fun App() {

    MaterialTheme {
        Box(
            Modifier.fillMaxSize()
                .background(color = themeColor.value)
        ) {

            Box(modifier = Modifier.align(Alignment.TopEnd).padding(20.dp)) {
                CustomSwitch()
            }

            Column(
                modifier = Modifier.fillMaxWidth(1f).align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ScoreRow()

                Column(Modifier.padding(4.dp)) {
                    GameButtonRow(game00text, game01text, game02text)
                    GameButtonRow(game10text, game11text, game12text)
                    GameButtonRow(game20text, game21text, game22text)
                }
            }

            ToastMessage(modifier = Modifier.align(Alignment.BottomCenter))

        }
    }

}

@Composable
fun GameButton(mutableText: MutableState<String>) {
    OutlinedButton(
        {
            if (isEmpty(mutableText.value)) {
                mutableText.value = newText(user1turn.value)
                roundCount.value++
                gameButtonOnClick()
            }
        },
        Modifier.padding(4.dp)
            .size(100.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = themeColor.value),
        border = BorderStroke(1.dp, borderColor.value)
    ) {
        Text(mutableText.value, fontSize = 20.sp, color = oppositeColor.value, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun GameButtonRow(
    firstText: MutableState<String>,
    secondText: MutableState<String>,
    thirdText: MutableState<String>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        GameButton(firstText)
        GameButton(secondText)
        GameButton(thirdText)
    }
}

@Composable
fun ScoreRow() {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Text(
                text = user1scoreText.value,
                fontSize = 18.sp,
                color = oppositeColor.value
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = user2scoreText.value,
                fontSize = 18.sp,
                color = oppositeColor.value
            )
        }

        Spacer(Modifier.padding(8.dp))

        Button(onClick = { resetTable() }) {
            Text(text = "Reset")
        }

    }
}

@Composable
fun ToastMessage(modifier: Modifier) {
    val toastMessageWidth by animateDpAsState(
        targetValue = toastMessageWidthState.value
    )
    val toastMessageHeight by animateDpAsState(
        targetValue = toastMessageHeightState.value
    )

    Box(
        modifier
            .padding(20.dp)
            .width(toastMessageWidth)
            .height(toastMessageHeight)
    ) {
        Card(
            elevation = 4.dp, shape = RoundedCornerShape(24.dp),
            backgroundColor = Color.Blue,
        ) {
            Column(
                Modifier.padding(10.dp)
                    .width(160.dp)
                    .height(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(toastMessageText.value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}
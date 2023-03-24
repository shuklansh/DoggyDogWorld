package com.shuklansh.doggydogworld

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shuklansh.doggydogworld.DogApi.DogAPI
import com.shuklansh.doggydogworld.DogApi.DogHelper
import com.shuklansh.doggydogworld.ui.theme.DoggyDogWorldTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {

    val apiCaller = DogHelper.getInstance().create(DogAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            var linkgetter by remember{
                mutableStateOf("")
            }
            var scopeCRT = rememberCoroutineScope()
            DoggyDogWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray //MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                    , horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top

                    ) {
                        Button(onClick = {
                            scopeCRT.async{
                                linkgetter = showCarIMGLINK()
                            }
                        },
                            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp),
                            modifier = Modifier.clip(RoundedCornerShape(50.dp)),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
                        ) {
                            Text(text = "press to see a doggo", fontSize = 20.sp, textAlign = TextAlign.Center,  // horizontal center of the text
                                modifier = Modifier.align(Alignment.CenterVertically), color = Color.Black)

                        }
                        Spacer(modifier = Modifier.height(50.dp))
                        AsyncImage(model = linkgetter, contentDescription = "cuteDog", modifier = Modifier
                            .height(400.dp)
                            .clip(
                                RoundedCornerShape(16.dp),
                            ),
                            contentScale = ContentScale.FillHeight
                        )
                    }

                }
            }
        }
    }
    suspend fun showCarIMGLINK() : String {
        var result = apiCaller.getDogImgLink()
        var dogmodelobject = result.body()
        return dogmodelobject!!.message
    }
}




//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    DoggyDogWorldTheme {
//        Greeting("Android")
//    }
//}
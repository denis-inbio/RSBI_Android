//package ro.rsbideveloper.rsbi.test
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.runtime.Composable
//
//class ComposeActivity: ComponentActivity() {
//    // <TODO> savedInstanceState vs persistentState (?!)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
////        setContentView(layout resource id)
//        // REPLACED BY
//        setContent {
//            Greeting("Hi, dude")
////            ComposeCourseYTTheme {
////                Surface(color = MaterialTheme.colors.background) {
////                    Greeting("Johnson")
////                }
////            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String) {
//        // <TODO> (*?) now how will translation be done ? well, probably in the caller... just use
//            //  getString() when calling and the ID of the string; but it could also be done
//            // in this function, for the other parts of the string template (!?)
//
//    // input vs ui
//    Text(text = "Hello $name !")    // built-in Jetpack @Composable
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Greeting("Johnson")
//}
//

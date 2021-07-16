package ro.rsbideveloper.rsbi

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class SplashscreenDirections private constructor() {
  public companion object {
    public fun actionFirstFragmentToSecondFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_FirstFragment_to_SecondFragment)
  }
}

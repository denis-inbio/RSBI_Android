package ro.rsbideveloper.rsbi.pages

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import ro.rsbideveloper.rsbi.R

public class Home_pageDirections private constructor() {
  public companion object {
    public fun actionHomepageNavToEventspage2(): NavDirections =
        ActionOnlyNavDirections(R.id.action_Homepage_Nav_to_eventspage2)
  }
}

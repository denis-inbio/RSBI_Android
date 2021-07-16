package ro.rsbideveloper.rsbi.pages

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import ro.rsbideveloper.rsbi.R

public class Events_pageDirections private constructor() {
  public companion object {
    public fun actionEventspageToWorkpage2(): NavDirections =
        ActionOnlyNavDirections(R.id.action_eventspage_to_workpage2)

    public fun actionEventsPageNavToAddEvent(): NavDirections =
        ActionOnlyNavDirections(R.id.action_Events_page_Nav_to_addEvent)
  }
}

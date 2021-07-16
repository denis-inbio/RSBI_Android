package ro.rsbideveloper.rsbi.pages

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import ro.rsbideveloper.rsbi.R

public class Work_pageDirections private constructor() {
  public companion object {
    public fun actionWorkpageToPublicpage(): NavDirections =
        ActionOnlyNavDirections(R.id.action_workpage_to_publicpage)
  }
}

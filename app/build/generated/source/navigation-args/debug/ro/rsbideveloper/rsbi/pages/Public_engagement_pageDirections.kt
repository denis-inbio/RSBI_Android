package ro.rsbideveloper.rsbi.pages

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import ro.rsbideveloper.rsbi.R

public class Public_engagement_pageDirections private constructor() {
  public companion object {
    public fun actionPublicpageToHomepageNav2(): NavDirections =
        ActionOnlyNavDirections(R.id.action_publicpage_to_Homepage_Nav2)

    public fun actionPublicEngagementPageNavToEssentialSkillsPage(): NavDirections =
        ActionOnlyNavDirections(R.id.action_Public_engagement_page_Nav_to_essential_skills_page)
  }
}

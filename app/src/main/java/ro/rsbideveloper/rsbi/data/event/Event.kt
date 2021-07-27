package ro.rsbideveloper.rsbi.data.event

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

// <TODO> there are multiple kinds of instantiation of a "Post":
    // (webinar / conference) event
    // job offer (# places, specialization, description per specialization, deadline, contract time, remote / physical / abroad move, ..?)
    // scholarship
    // news (like an article ? but difference is the communication method, and it being public)
    // article
    // "Beer and Science" event (can have photo attachments) [also a Google Maps position]; is there an online analog for this ?
    // blog
    // newsletter (somewhat like a few bundled posts, or like magazines that have "issues" / "{special} editions")
    //

// <TODO> the search mechanism will require "(`smart`) partial matching" (with adjustable "tolerance"
    // until something is found, at least a result, unless the database is empty), "filtering by tags",
    // ...

// <TODO> the search page will require something like Olx for the search filtering

// <TODO> the navigation drawer will link to specialized pages for each "category", but events of any
    // kind will also be accessible through the Home_page's search mechanism

//@Parcelize    -> it has also been deprecated together with synthetics; is this also the reason why
    // the update to the repository didn't work ? I wasn't getting the correct data through the safeargs (!)
@Entity(tableName = "event_table")
data class Event (  // this could instead be the more general Entity of "Post" (which is any
            // of the kind of Events, including the one which ahs a schedule; or it could be a Job
            // offer, or a Study offer, or an Article (!), or a "Data post" (where either data is
            // uploaded or a link to it is given), or ..?

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val published_by: String,   // <e.g.> "admin", "Monica Abrudan", "Bogdan Mirăuță", ..?
    val image_URL: String,
    val event_type: String, // <e.g.> "RSBI Event", "Future Events", "External Event", "Job offers",
                                // "Cariere", "Public engagement", "Study / Scholarship", "Science communication",
                                // "Blog", ..?;
                            // this seems to also be called a "category", and there are also "tags";
                            // language (in)consistency: this could also be "Job offers", "Cariere" (apparently this
                                // happened due to an inconsistency in using either English or Romanian, so it
                                // just makes it necessary to do extra work on the client to also translate it
                                // properly; but it's fine... if it gets corrected in the database it would be
                                // better, and for all the clients / interfaces to be standardized and to use
                                // ~ "selection tables" with pre-defined values, not "free-language writing")

    val edition: String,    // splitting the title from the web into parts
    val title: String,
    val publish_date: String,   // compute the "time ago"; also, there should be a list of "edit_date" and "edit_time"
    val publish_time: String,   // locale adjusted (!); note that there have been foreign participants as well,
                            // and the invited guests are often foreigners
    val event_date: String,
    val event_time: String,
    val format: String, // <e.g.> "online", "offline", ..?

    val content: String,    // the history / versioning should also be kept, sometimes, at
                                // least locally for the editor itself


    val registration_survey_URL: String,    // or replace this with an app-implemented
                        // survey (which can be auto-completed with the user's configurable information !)
    // <TODO> mechanism for when the Zoom link hasn't been received in time -> quick message to "contact us" !
        // but why would the server fail to do this ? then, what if this app were to request the Zoom link
        // directly from the server ? and also have it in the Google Calendar Event, with the option to jump
        // into Zoom from the Google Calendar Event
    // <TODO> could the app be used to facilitate the invited guests somehow ?
    // <TODO> could the app be used to facilitate the administrator(s) ?
    // <TODO> could the app be used to facilitate the (community) members ?

    // <TODO> share mechanisms on social media of an event, from the app ?

    // <TODO> contact mechanism to the developer's email / other
        //  endpoints (maybe have a ~ "developer's feedback endpoint" running on a server ?)

//    val comments: List<String>,   // <TODO> cannot have List<> types, only "primitive / integral" types
    // <TODO> the option to also leave a comment -> what does Akismet do to "filter spam" ?

    val schedule: String,   // define a better type; there are some events which actually have a schedule,
                                // and this contains the date-time, the speaker, the title, of the presentation
                                // the presenter's provenience, ..?

//    val attachments_URL: List<String>,   // it is necessary the posts be able to have attachments (well,
                // they actually have URLs, not actual attachments; the data itself is updated in an
                // authorized environment where the role for "uploading to database / server" is given,
                // whereas accessing the data requires either an "anonymous / public role" (for
                // publicly available data), or other specific roles for private-specific data
    val further_state: String   // or simply "state", <e.g.> "Active", "Closed / Finished", "Postponed" (such
                    // as the beer thing from 2020), "Canceled"

) // : Parcelable
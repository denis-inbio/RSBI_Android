package ro.rsbideveloper.rsbi.MVVM

import androidx.lifecycle.LiveData

class EventRepository(private val dao: EventDao) {
    val data: LiveData<List<Event>> = dao.selectAll()   // why is this not meant to be private ? (who needs access and is not an inheritor ?)

    // (*?) from here on, the names that the repository presents could be "domain specific" or
        // still "generic" (as the context / name resolution could still be sufficient)

    // (*?) so apparently adding data is done asynchronously, but requesting data is done blocking ?
    // this could be called "insert_SQL" or "insert_MongoDB", to differentiate in the diversity (?!)
    suspend fun insert(event: Event) {    // alright, this is a little annoying, because there is
            // a ~ "semantic repeat" between the @Dao and the Repository [since the repository is just
            // a generalization over diversity for the different @Dao I guess (where each database would
            // have its corresponding @Dao for an @Entity)]
        dao.insert(event)
    }
}
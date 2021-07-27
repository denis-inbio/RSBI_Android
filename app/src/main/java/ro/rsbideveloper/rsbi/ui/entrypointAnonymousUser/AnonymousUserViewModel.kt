package ro.rsbideveloper.rsbi.ui.entrypointAnonymousUser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnonymousUserViewModel : ViewModel() {
    // HTML
    // Articles
    // Images

    // Repo, Database, Dao, ..?
    // Sync data with remote server, async propagate data through app (callbacks, LiveData)

    val defaultLiveState1 = "0"
    val defaultLiveState2 = 0  // <TODO> initial state, how do you decide this ? (get it from a persistent source, otherwise use the default)
    val defaultLiveState3 = false

    val liveState1 by lazy {    // <TODO> why use lazy initialization, is it mandatory ?
        MutableLiveData<String>(defaultLiveState1) }

    val liveState2 by lazy {
        MutableLiveData<Int>(defaultLiveState2) }

    val liveState3 by lazy {
        MutableLiveData<Boolean>(defaultLiveState3) }

    // <TODO> I think that state changes should be modularized in this class, to maintain integrity; so users / consumers of
        // this ViewModel would instead have to call methods presented by this class, for changing state

    public fun changeState1() {
        liveState1.value += "0"
    }
    public fun changeState2() {
        liveState2.value = liveState2.value?.plus(1)
    }
    public fun changeState3() {
        liveState3.value = !(liveState3.value)!!
    }
}
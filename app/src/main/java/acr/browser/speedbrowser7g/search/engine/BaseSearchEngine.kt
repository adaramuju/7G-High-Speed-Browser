package acr.browser.speedbrowser7g.search.engine

import acr.browser.speedbrowser7g.database.FeedsModel
import acr.browser.speedbrowser7g.database.feeds.FeedsDatabase
import android.app.Application
import androidx.annotation.StringRes

/**
 * A class representative of a search engine.
 *
 * Contains three key pieces of information:
 *  - The icon shown for the search engine, should point to a local assets URL.
 *  - The query URL for the search engine, the query will be appended to the end.
 *  - The title string resource for the search engine.
 */
open class BaseSearchEngine(
    val iconUrl: String,
    val queryUrl: String,
    @StringRes val titleRes: Int,
    val application: Application?
) {

    operator fun component1() = iconUrl

    operator fun component2() = queryUrl

    operator fun component3() = titleRes

    operator fun component4() = feedsData

    open val feedsData: List<FeedsModel> =
            FeedsDatabase(application!!).allEntries().also { println("Initializing Feeds") }

}

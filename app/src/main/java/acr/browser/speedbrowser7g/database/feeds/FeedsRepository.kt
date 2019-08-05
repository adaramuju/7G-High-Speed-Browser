package acr.browser.speedbrowser7g.database.feeds

import acr.browser.speedbrowser7g.database.FeedsModel
import io.reactivex.Completable
import io.reactivex.Single

interface FeedsRepository {

    fun clearFeeds()
    fun feedEntry(item: FeedsModel)
    fun allEntries(): List<FeedsModel>

}
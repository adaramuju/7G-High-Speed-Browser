package acr.browser.speedbrowser7g.search.engine

import acr.browser.speedbrowser7g.R
import android.app.Application

/**
 * A custom search engine.
 */
class CustomSearch(queryUrl: String, application: Application?) : BaseSearchEngine(
    "file:///android_asset/a5gfastbrowser.png",
    queryUrl,
    R.string.search_engine_custom, application
)

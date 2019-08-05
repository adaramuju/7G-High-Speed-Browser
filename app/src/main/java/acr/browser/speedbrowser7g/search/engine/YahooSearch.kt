package acr.browser.speedbrowser7g.search.engine

import acr.browser.speedbrowser7g.R
import android.app.Application

/**
 * The Yahoo search engine.
 *
 * See http://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Yahoo%21_logo.svg/799px-Yahoo%21_logo.svg.png
 * for the icon.
 */
class YahooSearch(application: Application?) : BaseSearchEngine(
    "file:///android_asset/yahoo.png",
    "https://search.yahoo.com/search?p=",
    R.string.search_engine_yahoo, application
)

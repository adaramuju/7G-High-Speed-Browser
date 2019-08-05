package acr.browser.speedbrowser7g.database.feeds

import acr.browser.speedbrowser7g.database.FeedsModel
import acr.browser.speedbrowser7g.database.databaseDelegate
import acr.browser.speedbrowser7g.extensions.useMap
import android.app.Application
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedsDatabase @Inject constructor(application: Application) : SQLiteOpenHelper(application, DATABASE_NAME, null, DATABASE_VERSION), FeedsRepository{

    private val database: SQLiteDatabase by databaseDelegate()

    override fun onCreate(db: SQLiteDatabase) {
        val createHistoryTable = "CREATE TABLE $TABLE_NAME(" +
                " $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $KEY_URL TEXT," +
                " $KEY_TITLE TEXT," +
                " $KEY_WEBSITE TEXT," +
                " $KEY_DESCRIPTION TEXT" +
                ")"
        db.execSQL(createHistoryTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    override fun clearFeeds() {
        database.delete(TABLE_NAME, null, null)
        database.close()
    }

    override fun feedEntry(item: FeedsModel) {
        database.insert(TABLE_NAME, null, item.toContentValues())
        database.close()
    }

    override fun allEntries(): List<FeedsModel> {
        val kue = database.query(
                    TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    "$KEY_ID ASC"
            ).useMap { it.bindToHistoryEntry() }
        database.close()
        return kue
    }

    private fun FeedsModel.toContentValues() = ContentValues().apply {
        put(KEY_URL, Url)
        put(KEY_TITLE, Title)
        put(KEY_WEBSITE, Website)
        put(KEY_DESCRIPTION, Description)
    }

    private fun Cursor.bindToHistoryEntry() = FeedsModel(
            Url = getString(1),
            Title = getString(2),
            Website = getString(3),
            Description = getString(4)
    )

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "feedsManager"
        private const val TABLE_NAME = "feeds"

        private const val KEY_ID = "id"
        private const val KEY_URL = "url"
        private const val KEY_TITLE = "title"
        private const val KEY_WEBSITE = "website"
        private const val KEY_DESCRIPTION = "description"
    }
}
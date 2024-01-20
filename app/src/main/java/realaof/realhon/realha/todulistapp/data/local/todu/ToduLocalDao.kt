package realaof.realhon.realha.todulistapp.data.local.todu

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem

@Dao
abstract class ToduLocalDao {

    @Query("SELECT * FROM toduListTable")
    abstract fun getAll(): List<ToduItem>

    @Query("SELECT * FROM toduListTable WHERE completed IN (:completed)")
    abstract fun getTaskByStatus(completed: Boolean): List<ToduItem>

    @Query(
        "SELECT * FROM toduListTable WHERE " +
                "title LIKE '%' || :keyword || '%'" +
                "or userId LIKE '%' || :keyword || '%'"
    )
    abstract fun searchTask(keyword: String): List<ToduItem>

    @Transaction
    open fun updateTudoList(todus: List<ToduItem>) {
        deleteAllItem()
        insertAll(todus)
    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateTodu(todu: ToduItem)

    @Transaction
    open fun searchTask(keyword: String, completed: Boolean?): List<ToduItem> {
        val searchValue = if (keyword.isEmpty()) getAll().asIterable()
        else searchTask(keyword).asIterable()

        val filter = if (completed != null) getTaskByStatus(completed).asIterable()
        else getAll().asIterable()

        return searchValue.intersect(filter.toSet()).toList()
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(todus: List<ToduItem>)

    @Query("DELETE FROM toduListTable")
    abstract fun deleteAllItem()
}
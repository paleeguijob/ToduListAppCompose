package realaof.realhon.realha.todulistapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import realaof.realhon.realha.todulistapp.data.local.todu.ToduLocalDao
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import retrofit2.Converter

@Database(entities = [ToduItem::class], version = 1, exportSchema = false)
abstract class ToduListAppDataBase : RoomDatabase() {

    abstract fun toduLoccalDao(): ToduLocalDao

}
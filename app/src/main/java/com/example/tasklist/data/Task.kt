package com.example.tasklist.data

data class Task(var id: Int, var name: String, var done: Boolean = false, var deadline: Long) {
    companion object {
        const val TABLE_NAME = "Tasks"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_TITLE = "name"
        const val COLUMN_NAME_DONE = "done"
        const val COLUMN_NAME_DEADLINE = "deadline"


        const val SQL_CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NAME_TITLE TEXT," +
                    "$COLUMN_NAME_DONE INTEGER," +
                    "$COLUMN_NAME_DEADLINE LONG)"

        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    }
}

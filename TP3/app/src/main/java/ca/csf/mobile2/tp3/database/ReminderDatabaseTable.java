package ca.csf.mobile2.tp3.database;

public class ReminderDatabaseTable
{
    public static final String CREATE_TABLE_SQL =
            "CREATE TABLE reminders (" +
                    "id              INTEGER    PRIMARY KEY    AUTOINCREMENT," +
                    "description     TEXT," +
                    "importance      INTEGER," +
                    "utcTime         LONG)";

    public static final String DROP_TABLE_SQL = "DROP TABLE reminders";

    public static final String SELECT_ALL_SQL = "SELECT * FROM reminders";
    public static final String SELECT_SQL_FROM_UTC_TIME = "SELECT * FROM reminders WHERE utcTime >= ? AND utcTime < ? ORDER BY utcTime ASC";
    public static final String SELECT_ALL_SQL_BY_TIME = "SELECT * FROM reminders ORDER BY utcTime ASC";
    public static final String SELECT_ALL_SQL_BY_IMPORTANCE = "SELECT * FROM reminders ORDER BY importance DESC, utcTime ASC";
    public static final String INSERT_SQL = "INSERT INTO reminders (description, importance, utcTime) VALUES(?, ?, ?)";
    public static final String DELETE_SQL = "DELETE FROM reminders WHERE id = ?";


    private ReminderDatabaseTable() {

    }
}

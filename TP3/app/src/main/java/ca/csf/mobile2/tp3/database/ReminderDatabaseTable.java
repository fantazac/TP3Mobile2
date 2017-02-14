package ca.csf.mobile2.tp3.database;

public class ReminderDatabaseTable
{
    public static final String CREATE_TABLE_SQL =
            "CREATE TABLE reminders (" +
                    "id              INTEGER    PRIMARY KEY    AUTOINCREMENT," +
                    "name            TEXT)";

    public static final String DROP_TABLE_SQL = "DROP TABLE reminders";

    public static final String SELECT_ALL_SQL = "SELECT id, name FROM reminders";
    public static final String INSERT_SQL = "INSERT INTO reminders (name) VALUES(?)";
    public static final String DELETE_SQL = "DELETE FROM reminders WHERE id = ?";


    private ReminderDatabaseTable() {

    }
}

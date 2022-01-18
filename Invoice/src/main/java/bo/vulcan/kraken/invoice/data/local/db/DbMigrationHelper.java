package bo.vulcan.kraken.invoice.data.local.db;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public final class DbMigrationHelper {
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `siat_document_type` (" +
                    "`id` INTEGER, " +
                    "`classifier_code` TEXT, " +
                    "`description` TEXT, " +
                    "`branch_id` INTEGER, " +
                    "`sale_point_id` INTEGER, " +
                    "`company_id` INTEGER, " +
                    "PRIMARY KEY(`id`))");
        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `siat_payment_method_type` (" +
                    "`id` INTEGER, " +
                    "`classifier_code` TEXT, " +
                    "`description` TEXT, " +
                    "`branch_id` INTEGER, " +
                    "`sale_point_id` INTEGER, " +
                    "`company_id` INTEGER, " +
                    "PRIMARY KEY(`id`))");
        }
    };
}

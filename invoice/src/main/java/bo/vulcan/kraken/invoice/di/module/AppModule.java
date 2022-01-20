package bo.vulcan.kraken.invoice.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.LocalDate;

import javax.inject.Singleton;

import bo.vulcan.kraken.invoice.data.AppDataManager;
import bo.vulcan.kraken.invoice.data.DataManager;
import bo.vulcan.kraken.invoice.data.local.db.AppDatabase;
import bo.vulcan.kraken.invoice.data.local.db.AppDbHelper;
import bo.vulcan.kraken.invoice.data.local.db.DbHelper;
import bo.vulcan.kraken.invoice.data.local.db.DbMigrationHelper;
import bo.vulcan.kraken.invoice.data.local.prefs.AppPreferencesHelper;
import bo.vulcan.kraken.invoice.data.local.prefs.PreferencesHelper;
import bo.vulcan.kraken.invoice.data.remote.ApiHeader;
import bo.vulcan.kraken.invoice.data.remote.ApiHelper;
import bo.vulcan.kraken.invoice.data.remote.AppApiHelper;
import bo.vulcan.kraken.invoice.di.scope.DatabaseInfo;
import bo.vulcan.kraken.invoice.di.scope.PreferenceInfo;
import bo.vulcan.kraken.invoice.utils.AppConstants;
import bo.vulcan.kraken.invoice.utils.DateTimeTypeAdapter;
import bo.vulcan.kraken.invoice.utils.InstantTypeAdapter;
import bo.vulcan.kraken.invoice.utils.LocalDateTypeAdapter;
import bo.vulcan.kraken.invoice.utils.rx.AppSchedulerProvider;
import bo.vulcan.kraken.invoice.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())
                .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    /*sharePreferences*/
    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    /*Api*/
    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(preferencesHelper.getAccessToken());
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    /*DataBase*/
    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

//        return Room.databaseBuilder(context, AppDatabase.class, dbName)
//                .addMigrations(DbMigrationHelper.MIGRATION_1_2, DbMigrationHelper.MIGRATION_2_3)
//                .build();
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }


    /*Data Manager*/
    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }
}

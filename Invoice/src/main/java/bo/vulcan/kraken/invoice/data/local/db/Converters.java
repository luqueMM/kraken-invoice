package bo.vulcan.kraken.invoice.data.local.db;

import androidx.room.TypeConverter;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.LocalDate;

import java.math.BigDecimal;

import bo.vulcan.kraken.invoice.data.model.enumeration.EmissionType;
import bo.vulcan.kraken.invoice.data.model.enumeration.Environment;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceState;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceType;
import bo.vulcan.kraken.invoice.data.model.enumeration.Mode;
import bo.vulcan.kraken.invoice.data.model.enumeration.VoucherState;
import bo.vulcan.kraken.invoice.utils.DateTimeHelper;

public class Converters {

    @TypeConverter
    public static DateTime toDateTime(String value) {
        return DateTimeHelper.toDateTime(value);
    }

    @TypeConverter
    public static String fromDateTime(DateTime value) {
        return DateTimeHelper.fromDateTime(value);
    }

    @TypeConverter
    public static LocalDate toLocalDate(String value) {
        return DateTimeHelper.toLocalDate(value);
    }

    @TypeConverter
    public static String fromLocalDate(LocalDate value) {
        return DateTimeHelper.fromLocalDate(value);
    }

    @TypeConverter
    public static Instant toInstant(String value) {
        return DateTimeHelper.toInstant(value);
    }

    @TypeConverter
    public static String fromInstant(Instant value) {
        return DateTimeHelper.fromInstant(value);
    }

    @TypeConverter
    public static BigDecimal toBigDecimal(Double value) {
        return value == null ? null : new BigDecimal(value);
    }

    @TypeConverter
    public static Double fromBigDecimal(BigDecimal value) {
        return value == null ? null : value.doubleValue();
    }



    @TypeConverter
    public InvoiceState toInvoiceState(String value) {
        return value == null ? null : InvoiceState.valueOf(value);
    }

    @TypeConverter
    public String fromInvoiceState(InvoiceState value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public InvoiceType toInvoiceType(String value) {
        return value == null ? null : InvoiceType.valueOf(value);
    }

    @TypeConverter
    public String fromInvoiceType(InvoiceType value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public VoucherState toVoucherState(String value) {
        return value == null ? null : VoucherState.valueOf(value);
    }

    @TypeConverter
    public String fromVoucherState(VoucherState value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public EmissionType toEmissionType(String value) {
        return value == null ? null : EmissionType.valueOf(value);
    }

    @TypeConverter
    public String fromEmissionType(EmissionType value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public Environment toEnvironment(String value) {
        return value == null ? null : Environment.valueOf(value);
    }

    @TypeConverter
    public String fromEnvironment(Environment value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public Mode toMode(String value) {
        return value == null ? null : Mode.valueOf(value);
    }

    @TypeConverter
    public String fromMode(Mode value) {
        return value == null ? null : value.name();
    }
}

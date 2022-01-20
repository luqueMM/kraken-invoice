package bo.vulcan.kraken.invoice.utils;

import java.math.BigDecimal;
import java.util.List;

public class Validations {
    public static class Str {
        public static String notNull(String str) {
            if (str == null) {
                return "can't be null";
            }
            return null;
        }

        public static String notNullAndNotEmpty(String str) {
            final String validationNotNul = notNull(str);

            if (validationNotNul == null) {
                if (str.isEmpty()) {
                    return "may not be empty";
                }
                return null;
            }
            return validationNotNul;
        }

        public static String minLength(String str, int min) {
            if (str != null && str.length() < min) {
                return "string length must be greater than or equal to " + min;
            }
            return null;
        }

        public static String maxLength(String str, int max) {
            if (str != null && str.length() > max) {
                return "string length must be less than or equal to " + max;
            }
            return null;
        }

        public static String size(String str, int min, int max) {
            String message = null;
            final String mMin = minLength(str, min);
            final String mMax = maxLength(str, max);

            if (mMin != null) {
                message = mMin;
            }

            if (mMax != null) {
                if (message == null) {
                    message = mMax;
                } else {
                    message += " and " + mMax;
                }
            }

            return message;
        }

        public static String email(String email) {
            String ePattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(email.trim());
            if (!m.matches()) {
                return "must be a well-formed email address";
            }
            return null;
        }
    }

    public static class IntegerNumber {

        public static String notNull(Integer value) {
            if (value == null) {
                return "can't be null";
            }
            return null;
        }

        public static String min(Integer number, int min) {
            if (number != null && number < min) {
                return "must be greater than or equal to " + min;
            }
            return null;
        }

        public static String max(Integer number, int max) {
            if (number != null && number > max) {
                return "must be less than or equal to " + max;
            }
            return null;
        }

        public static String range(Integer number, int min, int max) {
            String message = null;
            final String mMin = min(number, min);
            final String mMax = max(number, max);

            if (mMin != null) {
                message = mMin;
            }

            if (mMax != null) {
                if (message == null) {
                    message = mMax;
                } else {
                    message += " and " + mMax;
                }
            }

            return message;
        }
    }

    public static class DoubleNumber {

        public static String notNull(Double value) {
            if (value == null) {
                return "can't be null";
            }
            return null;
        }

        public static String min(Double number, double min) {
            if (number != null && number < min) {
                return "must be greater than or equal to " + min;
            }
            return null;
        }

        public static String max(Double number, double max) {
            if (number != null && number > max) {
                return "must be less than or equal to " + max;
            }
            return null;
        }

        public static String range(Double number, double min, double max) {
            String message = null;
            final String mMin = min(number, min);
            final String mMax = max(number, max);

            if (mMin != null) {
                message = mMin;
            }

            if (mMax != null) {
                if (message == null) {
                    message = mMax;
                } else {
                    message += " and " + mMax;
                }
            }

            return message;
        }
    }

    public static class BigDec {

        public static String notNull(BigDecimal value) {
            if (value == null) {
                return "can't be null";
            }
            return null;
        }

        public static String min(BigDecimal number, BigDecimal min) {
            if (number != null && number.compareTo(min) < 0) {
                return "must be greater than or equal to " + min;
            }
            return null;
        }

        public static String max(BigDecimal number, BigDecimal max) {
            if (number != null && number.compareTo(max) > 0) {
                return "must be less than or equal to " + max;
            }
            return null;
        }

        public static String range(BigDecimal number, BigDecimal min, BigDecimal max) {
            String message = null;
            final String mMin = min(number, min);
            final String mMax = max(number, max);

            if (mMin != null) {
                message = mMin;
            }

            if (mMax != null) {
                if (message == null) {
                    message = mMax;
                } else {
                    message += " and " + mMax;
                }
            }

            return message;
        }
    }

    public static class Lists {

        public static String notNull(List value) {
            if (value == null) {
                return "can't be null";
            }
            return null;
        }

        public static String min(List list, int min) {
            if (list != null && list.size() < min) {
                return "must be greater than or equal to " + min;
            }
            return null;
        }

        public static String max(List list, int max) {
            if (list != null && list.size() > max) {
                return "must be less than or equal to " + max;
            }
            return null;
        }

        public static String range(List number, int min, int max) {
            String message = null;
            final String mMin = min(number, min);
            final String mMax = max(number, max);

            if (mMin != null) {
                message = mMin;
            }

            if (mMax != null) {
                if (message == null) {
                    message = mMax;
                } else {
                    message += " and " + mMax;
                }
            }

            return message;
        }
    }
}


//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ocbc.ms.notification.cn.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TypeConverter {
    private static final ThreadLocal<DecimalFormat> DECIMAL_FORMAT = ThreadLocal.withInitial(() -> {
        DecimalFormat format = new DecimalFormat("#");
        format.setMinimumIntegerDigits(1);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(2147483647);
        return format;
    });
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = ThreadLocal.withInitial(() -> {
        return new SimpleDateFormat("yyyy-MM-dd");
    });
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT_2 = ThreadLocal.withInitial(() -> {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    });
    private static final ThreadLocal<SimpleDateFormat> TIME_FORMAT = ThreadLocal.withInitial(() -> {
        return new SimpleDateFormat("HH:mm:ss");
    });
    private static final ThreadLocal<SimpleDateFormat> TIMESTAMP_FORMAT = ThreadLocal.withInitial(() -> {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    });
    private static final HashMap<TypeConverter.ConversionMapping, TypeConverter.Converter> CONV_MAP = new HashMap();

    private TypeConverter() {
    }

    public static void remove() {
        TIMESTAMP_FORMAT.remove();
        TIME_FORMAT.remove();
        DATE_FORMAT_2.remove();
        DATE_FORMAT.remove();
        DECIMAL_FORMAT.remove();
    }

    private static String formatDate(Date date) {
        if (date == null) {
            return "";
        } else {
            try {
                return ((SimpleDateFormat)DATE_FORMAT.get()).format(date);
            } catch (Exception var2) {
                return date.toString();
            }
        }
    }

    private static String formatDate2(Date date) {
        if (date == null) {
            return "";
        } else {
            try {
                return ((SimpleDateFormat)DATE_FORMAT_2.get()).format(date);
            } catch (Exception var2) {
                return date.toString();
            }
        }
    }

    private static String formatTime(Date date) {
        if (date == null) {
            return "";
        } else {
            try {
                return ((SimpleDateFormat)TIME_FORMAT.get()).format(date);
            } catch (Exception var2) {
                return date.toString();
            }
        }
    }

    private static String formatTimestamp(Date date) {
        if (date == null) {
            return "";
        } else {
            try {
                return ((SimpleDateFormat)TIMESTAMP_FORMAT.get()).format(date);
            } catch (Exception var2) {
                return date.toString();
            }
        }
    }

    public static <T> T convert(Object value, Class<T> classOfT) {
        if (value == null) {
            return null;
        } else if (classOfT == null) {
            throw new AssertionError();
        } else if (classOfT.isInstance(value)) {
            return (T) value;
        } else {
            TypeConverter.Converter c = (TypeConverter.Converter)CONV_MAP.get(new TypeConverter.ConversionMapping(value.getClass(), classOfT));
            return c == null ? null : (T) c.convert(value);
        }
    }

    static {
        TypeConverter.Converter textConverter = (value) -> {
            if (value instanceof String) {
                return value;
            } else if (value instanceof BigDecimal) {
                ((DecimalFormat)DECIMAL_FORMAT.get()).setMinimumFractionDigits(((BigDecimal)value).scale());
                return ((DecimalFormat)DECIMAL_FORMAT.get()).format(value);
            } else if (value instanceof Number) {
                ((DecimalFormat)DECIMAL_FORMAT.get()).setMinimumFractionDigits(0);
                return ((DecimalFormat)DECIMAL_FORMAT.get()).format(value);
            } else if (value instanceof java.sql.Date) {
                return formatDate((java.sql.Date)value);
            } else if (value instanceof Time) {
                return formatTime((Time)value);
            } else if (value instanceof Timestamp) {
                return formatTimestamp((Date)value);
            } else if (value instanceof Date) {
                return formatDate2((Date)value);
            } else {
                return value instanceof byte[] ? new String((byte[])value) : value.toString();
            }
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(Boolean.class, String.class), textConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Byte.class, String.class), textConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Short.class, String.class), textConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Integer.class, String.class), textConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Long.class, String.class), textConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Float.class, String.class), textConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Double.class, String.class), textConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigDecimal.class, String.class), textConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Date.class, String.class), textConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(java.sql.Date.class, String.class), textConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Time.class, String.class), textConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Timestamp.class, String.class), textConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(byte[].class, String.class), textConverter);
        TypeConverter.Converter sqlDateConverter = (value) -> {
            if (value instanceof Date) {
                return new java.sql.Date(((Date)value).getTime());
            } else if (value instanceof Number) {
                return new java.sql.Date(((Number)value).longValue());
            } else {
                if (value instanceof String) {
                    SimpleDateFormat df = new SimpleDateFormat();

                    Date date;
                    try {
                        if ("".equals(value)) {
                            return null;
                        }

                        date = df.parse((String)value);
                    } catch (ParseException var4) {
                        date = null;
                    }

                    if (date != null) {
                        return new java.sql.Date(date.getTime());
                    }
                }

                return null;
            }
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(Date.class, java.sql.Date.class), sqlDateConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Timestamp.class, java.sql.Date.class), sqlDateConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Time.class, java.sql.Date.class), sqlDateConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Long.class, java.sql.Date.class), sqlDateConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(String.class, java.sql.Date.class), sqlDateConverter);
        TypeConverter.Converter timeConverter = (value) -> {
            if (value instanceof Date) {
                return new Time(((Date)value).getTime());
            } else if (value instanceof Number) {
                return new Time(((Number)value).longValue());
            } else {
                if (value instanceof String) {
                    SimpleDateFormat df = new SimpleDateFormat();

                    Date date;
                    try {
                        if ("".equals(value)) {
                            return null;
                        }

                        date = df.parse((String)value);
                    } catch (ParseException var4) {
                        date = null;
                    }

                    if (date != null) {
                        return new Time(date.getTime());
                    }
                }

                return null;
            }
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(Timestamp.class, Time.class), timeConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(java.sql.Date.class, Time.class), timeConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Date.class, Time.class), timeConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Long.class, Time.class), timeConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(String.class, Time.class), timeConverter);
        TypeConverter.Converter timestampConverter = (value) -> {
            if (value instanceof Date) {
                return new Time(((Date)value).getTime());
            } else if (value instanceof Number) {
                return new Time(((Number)value).longValue());
            } else {
                if (value instanceof String) {
                    SimpleDateFormat df = new SimpleDateFormat();

                    Date date;
                    try {
                        if ("".equals(value)) {
                            return null;
                        }

                        date = df.parse((String)value);
                    } catch (ParseException var4) {
                        date = null;
                    }

                    if (date != null) {
                        return new Time(date.getTime());
                    }
                }

                return null;
            }
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(Time.class, Timestamp.class), timestampConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(java.sql.Date.class, Timestamp.class), timestampConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Date.class, Timestamp.class), timestampConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Long.class, Timestamp.class), timestampConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(String.class, Timestamp.class), timestampConverter);
        TypeConverter.Converter boolConverter = (value) -> {
            return value instanceof String ? Boolean.valueOf((String)value) : null;
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(String.class, Boolean.class), boolConverter);
        TypeConverter.Converter byteConverter = (value) -> {
            if (value instanceof Number) {
                return ((Number)value).byteValue();
            } else if (value instanceof String) {
                return "".equals(value) ? null : Byte.valueOf((String)value);
            } else {
                return null;
            }
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(String.class, Byte.class), byteConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Short.class, Byte.class), byteConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Integer.class, Byte.class), byteConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Long.class, Byte.class), byteConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Float.class, Byte.class), byteConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Double.class, Byte.class), byteConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigInteger.class, Byte.class), byteConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigDecimal.class, Byte.class), byteConverter);
        TypeConverter.Converter shortConverter = (value) -> {
            if (value == null) {
                return null;
            } else if (value instanceof Number) {
                return ((Number)value).shortValue();
            } else if (value instanceof String) {
                String shortValue = ((String)value).trim();
                return "".equals(shortValue) ? null : Short.valueOf(shortValue);
            } else {
                return null;
            }
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(String.class, Short.class), shortConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Byte.class, Short.class), shortConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Integer.class, Short.class), shortConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Long.class, Short.class), shortConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Float.class, Short.class), shortConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Double.class, Short.class), shortConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigInteger.class, Short.class), shortConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigDecimal.class, Short.class), shortConverter);
        TypeConverter.Converter intConverter = (value) -> {
            if (value == null) {
                return null;
            } else if (value instanceof Number) {
                return ((Number)value).intValue();
            } else if (value instanceof String) {
                String intValue = ((String)value).trim();
                return "".equals(intValue) ? null : Integer.valueOf(intValue);
            } else {
                return null;
            }
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(String.class, Integer.class), intConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Byte.class, Integer.class), intConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Short.class, Integer.class), intConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Long.class, Integer.class), intConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Float.class, Integer.class), intConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Double.class, Integer.class), intConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigInteger.class, Integer.class), intConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigDecimal.class, Integer.class), intConverter);
        TypeConverter.Converter longConverter = (value) -> {
            if (value == null) {
                return null;
            } else if (value instanceof Number) {
                return ((Number)value).longValue();
            } else if (value instanceof String) {
                String longValue = ((String)value).trim();
                return "".equals(longValue) ? null : Long.valueOf(longValue);
            } else {
                return value instanceof Date ? ((Date)value).getTime() : null;
            }
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(String.class, Long.class), longConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Byte.class, Long.class), longConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Short.class, Long.class), longConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Integer.class, Long.class), longConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Float.class, Long.class), longConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Double.class, Long.class), longConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigInteger.class, Long.class), longConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigDecimal.class, Long.class), longConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Date.class, Long.class), longConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(java.sql.Date.class, Long.class), longConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Time.class, Long.class), longConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Timestamp.class, Long.class), longConverter);
        TypeConverter.Converter floatConverter = (value) -> {
            if (value == null) {
                return null;
            } else if (value instanceof Number) {
                return ((Number)value).floatValue();
            } else if (value instanceof String) {
                String floatValue = ((String)value).trim();
                return "".equals(floatValue) ? null : Float.valueOf(floatValue);
            } else {
                return null;
            }
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(String.class, Float.class), floatConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Byte.class, Float.class), floatConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Short.class, Float.class), floatConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Integer.class, Float.class), floatConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Long.class, Float.class), floatConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Double.class, Float.class), floatConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigInteger.class, Float.class), floatConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigDecimal.class, Float.class), floatConverter);
        TypeConverter.Converter doubleConverter = (value) -> {
            if (value == null) {
                return null;
            } else if (value instanceof Number) {
                return ((Number)value).doubleValue();
            } else if (value instanceof String) {
                String doubleValue = ((String)value).trim();
                return "".equals(doubleValue) ? null : Double.valueOf(doubleValue);
            } else {
                return null;
            }
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(String.class, Double.class), doubleConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Byte.class, Double.class), doubleConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Short.class, Double.class), doubleConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Integer.class, Double.class), doubleConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Long.class, Double.class), doubleConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Float.class, Double.class), doubleConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigInteger.class, Double.class), doubleConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigDecimal.class, Double.class), doubleConverter);
        TypeConverter.Converter decimalConverter = (value) -> {
            if (value == null) {
                return null;
            } else if (!(value instanceof Byte) && !(value instanceof Short) && !(value instanceof Integer) && !(value instanceof Long)) {
                if (!(value instanceof Float) && !(value instanceof Double)) {
                    if (value instanceof BigInteger) {
                        return new BigDecimal((BigInteger)value);
                    } else if (value instanceof String) {
                        String decimalValue = ((String)value).trim();
                        return "".equals(decimalValue) ? null : new BigDecimal(decimalValue);
                    } else {
                        return null;
                    }
                } else {
                    return new BigDecimal(value.toString());
                }
            } else {
                return new BigDecimal(((Number)value).longValue());
            }
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(String.class, BigDecimal.class), decimalConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Byte.class, BigDecimal.class), decimalConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Short.class, BigDecimal.class), decimalConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Integer.class, BigDecimal.class), decimalConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Long.class, BigDecimal.class), decimalConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Float.class, BigDecimal.class), decimalConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Double.class, BigDecimal.class), decimalConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigInteger.class, BigDecimal.class), decimalConverter);
        TypeConverter.Converter bigintConverter = (value) -> {
            if (value == null) {
                return null;
            } else if (value instanceof Number) {
                return BigInteger.valueOf(((Number)value).longValue());
            } else if (value instanceof String) {
                String bigIntValue = ((String)value).trim();
                return "".equals(bigIntValue) ? null : new BigInteger(bigIntValue);
            } else {
                return null;
            }
        };
        CONV_MAP.put(new TypeConverter.ConversionMapping(String.class, BigInteger.class), bigintConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Byte.class, BigInteger.class), bigintConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Short.class, BigInteger.class), bigintConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Integer.class, BigInteger.class), bigintConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Long.class, BigInteger.class), bigintConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Float.class, BigInteger.class), bigintConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(Double.class, BigInteger.class), bigintConverter);
        CONV_MAP.put(new TypeConverter.ConversionMapping(BigDecimal.class, BigInteger.class), bigintConverter);
    }

    interface Converter {
        Object convert(Object var1);
    }

    static class ConversionMapping {
        final Class<?> from;
        final Class<?> to;
        final int hashCode;

        public ConversionMapping(Class<?> from, Class<?> to) {
            this.from = from;
            this.to = to;
            this.hashCode = from.hashCode() ^ to.hashCode() << 1;
        }

        public boolean equals(Object o) {
            if (o == null) {
                return false;
            } else if (this.getClass() != o.getClass()) {
                return false;
            } else {
                TypeConverter.ConversionMapping x = (TypeConverter.ConversionMapping)o;
                return x.from == this.from && x.to == this.to;
            }
        }

    }
}

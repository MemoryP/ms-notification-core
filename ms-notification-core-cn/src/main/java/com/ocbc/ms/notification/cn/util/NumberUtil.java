package com.ocbc.ms.notification.cn.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class NumberUtil {
    private static final int DEFAUT_DIV_SCALE = 10;
    private static final Random ran = new SecureRandom();

    private NumberUtil() {
    }

    public static double add(double v1, double v2) {
        return add(Double.toString(v1), Double.toString(v2)).doubleValue();
    }

    public static BigDecimal add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2);
    }

    public static double sub(double v1, double v2) {
        return sub(Double.toString(v1), Double.toString(v2)).doubleValue();
    }

    public static BigDecimal sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }

    public static double mul(double v1, double v2) {
        return mul(Double.toString(v1), Double.toString(v2)).doubleValue();
    }

    public static BigDecimal mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }

    public static double div(double v1, double v2) {
        return div(v1, v2, 10);
    }

    public static BigDecimal div(String v1, String v2) {
        return div(v1, v2, 10);
    }

    public static double div(double v1, double v2, int scale) {
        return div(v1, v2, scale, RoundingMode.HALF_UP);
    }

    public static BigDecimal div(String v1, String v2, int scale) {
        return div(v1, v2, scale, RoundingMode.HALF_UP);
    }

    public static double div(double v1, double v2, int scale, RoundingMode roundingMode) {
        return div(Double.toString(v1), Double.toString(v2), scale, roundingMode).doubleValue();
    }

    public static BigDecimal div(String v1, String v2, int scale, RoundingMode roundingMode) {
        if (scale < 0) {
            scale = -scale;
        }

        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, roundingMode);
    }

    public static double round(double v, int scale) {
        return round(v, scale, RoundingMode.HALF_UP);
    }

    public static double round(String numberStr, int scale) {
        return round(numberStr, scale, RoundingMode.HALF_UP);
    }

    public static double round(double v, int scale, RoundingMode roundingMode) {
        return round(Double.toString(v), scale, roundingMode);
    }

    public static double round(String numberStr, int scale, RoundingMode roundingMode) {
        BigDecimal b = new BigDecimal(numberStr);
        return b.setScale(scale, roundingMode).doubleValue();
    }

    public static String roundStr(double number, int digit) {
        String format = "%." + digit + 'f';
        return String.format(format, number);
    }

    public static String decimalFormat(String pattern, double value) {
        return (new DecimalFormat(pattern)).format(value);
    }

    public static String decimalFormat(String pattern, long value) {
        return (new DecimalFormat(pattern)).format(value);
    }

    public static boolean isNumber(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        } else {
            char[] chars = str.toCharArray();
            int sz = chars.length;
            boolean hasExp = false;
            boolean hasDecPoint = false;
            boolean allowSigns = false;
            boolean foundDigit = false;
            int start = chars[0] == '-' ? 1 : 0;
            int i;
            if (sz > start + 1 && chars[start] == '0' && chars[start + 1] == 'x') {
                i = start + 2;
                if (i == sz) {
                    return false;
                } else {
                    while(i < chars.length) {
                        if ((chars[i] < '0' || chars[i] > '9') && (chars[i] < 'a' || chars[i] > 'f') && (chars[i] < 'A' || chars[i] > 'F')) {
                            return false;
                        }

                        ++i;
                    }

                    return true;
                }
            } else {
                --sz;

                for(i = start; i < sz || i < sz + 1 && allowSigns && !foundDigit; ++i) {
                    if (chars[i] >= '0' && chars[i] <= '9') {
                        foundDigit = true;
                        allowSigns = false;
                    } else if (chars[i] == '.') {
                        if (hasDecPoint || hasExp) {
                            return false;
                        }

                        hasDecPoint = true;
                    } else if (chars[i] != 'e' && chars[i] != 'E') {
                        if (chars[i] != '+' && chars[i] != '-') {
                            return false;
                        }

                        if (!allowSigns) {
                            return false;
                        }

                        allowSigns = false;
                        foundDigit = false;
                    } else {
                        if (hasExp) {
                            return false;
                        }

                        if (!foundDigit) {
                            return false;
                        }

                        hasExp = true;
                        allowSigns = true;
                    }
                }

                if (i < chars.length) {
                    if (chars[i] >= '0' && chars[i] <= '9') {
                        return true;
                    } else if (chars[i] != 'e' && chars[i] != 'E') {
                        if (chars[i] == '.') {
                            return !hasDecPoint && !hasExp ? foundDigit : false;
                        } else if (allowSigns || chars[i] != 'd' && chars[i] != 'D' && chars[i] != 'f' && chars[i] != 'F') {
                            if (chars[i] != 'l' && chars[i] != 'L') {
                                return false;
                            } else {
                                return foundDigit && !hasExp;
                            }
                        } else {
                            return foundDigit;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return !allowSigns && foundDigit;
                }
            }
        }
    }

    public static boolean isInteger(String s) {
        return StringUtil.isNotBlank(s) ? s.matches("^\\d+$") : false;
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return s.contains(".");
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public static boolean isPrimes(int n) {
        for(int i = 2; (double)i <= Math.sqrt((double)n); ++i) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static int[] generateRandomNumber(int begin, int end, int size) {
        if (begin > end) {
            int temp = begin;
            begin = end;
            end = temp;
        }

        if (end - begin < size) {
            throw new UtilException("Size is larger than range between begin and end!");
        } else {
            int[] seed = new int[end - begin];

            for(int i = begin; i < end; seed[i - begin] = i++) {
            }

            int[] ranArr = new int[size];

            for(int i = 0; i < size; ++i) {
                int j = ran.nextInt(seed.length - i);
                ranArr[i] = seed[j];
                seed[j] = seed[seed.length - 1 - i];
            }

            return ranArr;
        }
    }

    public static Integer[] generateBySet(int begin, int end, int size) {
        if (begin > end) {
            int temp = begin;
            begin = end;
            end = temp;
        }

        if (end - begin < size) {
            throw new UtilException("Size is larger than range between begin and end!");
        } else {
            HashSet set = new HashSet();

            while(set.size() < size) {
                set.add(begin + ran.nextInt(end - begin));
            }

            return (Integer[])set.toArray(new Integer[size]);
        }
    }

    public static int[] range(int start, int stop) {
        return range(start, stop, 1);
    }

    public static int[] range(int start, int stop, int step) {
        if (start < stop) {
            step = Math.abs(step);
        } else {
            if (start <= stop) {
                return new int[]{start};
            }

            step = -Math.abs(step);
        }

        int size = Math.abs((stop - start) / step) + 1;
        int[] values = new int[size];
        int index = 0;
        int i = start;

        while(true) {
            if (step > 0) {
                if (i > stop) {
                    break;
                }
            } else if (i < stop) {
                break;
            }

            values[index] = i;
            ++index;
            i += step;
        }

        return values;
    }

    public static Collection<Integer> appendRange(int start, int stop, Collection<Integer> values) {
        return appendRange(start, stop, 1, values);
    }

    public static Collection<Integer> appendRange(int start, int stop, int step, Collection<Integer> values) {
        if (start < stop) {
            step = Math.abs(step);
        } else {
            if (start <= stop) {
                values.add(start);
                return values;
            }

            step = -Math.abs(step);
        }

        int i = start;

        while(true) {
            if (step > 0) {
                if (i > stop) {
                    break;
                }
            } else if (i < stop) {
                break;
            }

            values.add(i);
            i += step;
        }

        return values;
    }

    public static int factorial(int n) {
        return n == 1 ? 1 : n * factorial(n - 1);
    }

    public static long sqrt(long x) {
        long y = 0L;

        for(long b = 4611686018427387904L; b > 0L; b >>= 2) {
            if (x >= y + b) {
                x -= y + b;
                y >>= 1;
                y += b;
            } else {
                y >>= 1;
            }
        }

        return y;
    }

    public static int processMultiple(int selectNum, int minNum) {
        int result = mathSubnode(selectNum, minNum) / mathNode(selectNum - minNum);
        return result;
    }

    public static int divisor(int m, int n) {
        while(m % n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }

        return n;
    }

    public static int multiple(int m, int n) {
        return m * n / divisor(m, n);
    }

    public static String getBinaryStr(Number number) {
        if (number instanceof Long) {
            return Long.toBinaryString((Long)number);
        } else {
            return number instanceof Integer ? Integer.toBinaryString((Integer)number) : Long.toBinaryString(number.longValue());
        }
    }

    public static int binaryToInt(String binaryStr) {
        return Integer.parseInt(binaryStr, 2);
    }

    public static long binaryToLong(String binaryStr) {
        return Long.parseLong(binaryStr, 2);
    }

    public static int compare(char x, char y) {
        return x - y;
    }

    public static int compare(double x, double y) {
        return Double.compare(x, y);
    }

    public static int compare(int x, int y) {
        if (x == y) {
            return 0;
        } else {
            return x < y ? -1 : 1;
        }
    }

    public static int compare(long x, long y) {
        if (x == y) {
            return 0;
        } else {
            return x < y ? -1 : 1;
        }
    }

    public static int compare(short x, short y) {
        if (x == y) {
            return 0;
        } else {
            return x < y ? -1 : 1;
        }
    }

    public static int compare(byte x, byte y) {
        return x - y;
    }

    public static boolean isBlankChar(char c) {
        return isBlankChar((int)c);
    }

    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c);
    }

    public static int count(int total, int part) {
        return total % part == 0 ? total / part : total / part + 1;
    }

    private static int mathSubnode(int selectNum, int minNum) {
        return selectNum == minNum ? 1 : selectNum * mathSubnode(selectNum - 1, minNum);
    }

    private static int mathNode(int selectNum) {
        return selectNum == 0 ? 1 : selectNum * mathNode(selectNum - 1);
    }
}

package com.ocbc.ms.notification.cn.util;

import com.google.common.base.Objects;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import org.springframework.util.ObjectUtils;

public class ArrayUtil {
    public static final int INDEX_NOT_FOUND = -1;
    private static final String CLASS_LONG_STRING = "long";
    private static final String CLASS_SHORT_STRING = "short";
    private static final String CLASS_INT_STRING = "int";
    private static final String CLASS_BYTE_STRING = "byte";
    private static final String CLASS_BOOLEAN_STRING = "boolean";
    private static final String CLASS_FLOAT_STRING = "float";
    private static final String CLASS_DOUBLE_STRING = "double";
    private static final String CLASS_CHAR_STRING = "char";

    private ArrayUtil() {
    }

    public static <T> boolean isEmpty(T... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Object array) {
        return array == null || !isArray(array) || Array.getLength(array) > 0;
    }

    public static boolean isEmpty(long... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(int... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(short... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(char... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(byte... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(double... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(float... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(boolean... array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNotEmpty(T... array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(Object array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(long... array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(int... array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(short... array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(char... array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(byte... array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(double... array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(float... array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(boolean... array) {
        return array != null && array.length != 0;
    }

    public static <T> boolean hasNull(T... array) {
        if (isNotEmpty(array)) {
            Object[] var1 = array;
            int var2 = array.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                T element = (T) var1[var3];
                if (null == element) {
                    return true;
                }
            }
        }

        return false;
    }

    public static <T> T firstNonNull(T... array) {
        if (isNotEmpty(array)) {
            Object[] var1 = array;
            int var2 = array.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                T val = (T) var1[var3];
                if (null != val) {
                    return val;
                }
            }
        }

        return null;
    }

    public static <T> T[] newArray(Class<?> componentType, int newSize) {
        return (T[]) Array.newInstance(componentType, newSize);
    }

    public static Object[] cast(Class<?> type, Object arrayObj) {
        if (null == arrayObj) {
            throw new NullPointerException("Argument [arrayObj] is null !");
        } else if (!arrayObj.getClass().isArray()) {
            throw new IllegalArgumentException("Argument [arrayObj] is not array !");
        } else if (null == type) {
            return (Object[])arrayObj;
        } else {
            Class<?> componentType = type.isArray() ? type.getComponentType() : type;
            Object[] array = (Object[])arrayObj;
            Object[] result = newArray(componentType, array.length);
            System.arraycopy(array, 0, result, 0, array.length);
            return result;
        }
    }

    @SafeVarargs
    public static <T> T[] append(T[] buffer, T... newElements) {
        if (isEmpty(newElements)) {
            return buffer;
        } else {
            T[] t = resize(buffer, buffer.length + newElements.length);
            System.arraycopy(newElements, 0, t, buffer.length, newElements.length);
            return t;
        }
    }

    public static <T> T[] resize(T[] buffer, int newSize, Class<?> componentType) {
        T[] newArray = newArray(componentType, newSize);
        if (isNotEmpty(buffer)) {
            System.arraycopy(buffer, 0, newArray, 0, Math.min(buffer.length, newSize));
        }

        return newArray;
    }

    public static <T> T[] resize(T[] buffer, int newSize) {
        return resize(buffer, newSize, buffer.getClass().getComponentType());
    }

    public static Object copy(Object src, int srcPos, Object dest, int destPos, int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
        return dest;
    }

    public static Object copy(Object src, Object dest, int length) {
        System.arraycopy(src, 0, dest, 0, length);
        return dest;
    }

    public static int[] range(int excludedEnd) {
        return range(0, excludedEnd, 1);
    }

    public static int[] range(int includedStart, int excludedEnd) {
        return range(includedStart, excludedEnd, 1);
    }

    public static int[] range(int includedStart, int excludedEnd, int step) {
        int deviation;
        if (includedStart > excludedEnd) {
            deviation = includedStart;
            includedStart = excludedEnd;
            excludedEnd = deviation;
        }

        if (step <= 0) {
            step = 1;
        }

        deviation = excludedEnd - includedStart;
        int length = deviation / step;
        if (deviation % step != 0) {
            ++length;
        }

        int[] range = new int[length];

        for(int i = 0; i < length; ++i) {
            range[i] = includedStart;
            includedStart += step;
        }

        return range;
    }

    public static byte[][] split(byte[] array, int len) {
        int x = array.length / len;
        int y = array.length % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }

        byte[][] arrays = new byte[x + z][];

        for(int i = 0; i < x + z; ++i) {
            byte[] arr = new byte[len];
            if (i == x + z - 1 && y != 0) {
                System.arraycopy(array, i * len, arr, 0, y);
            } else {
                System.arraycopy(array, i * len, arr, 0, len);
            }

            arrays[i] = arr;
        }

        return arrays;
    }

    public static <K, V> Map<K, V> zip(K[] keys, V[] values, boolean isOrder) {
        if (!isEmpty(keys) && !isEmpty(values)) {
            int size = Math.min(keys.length, values.length);
            Map<K, V> map = CollectionUtil.newHashMap(size, isOrder);

            for(int i = 0; i < size; ++i) {
                map.put(keys[i], values[i]);
            }

            return map;
        } else {
            return null;
        }
    }

    public static <K, V> Map<K, V> zip(K[] keys, V[] values) {
        return zip(keys, values, false);
    }

    public static <T> int indexOf(T[] array, Object value) {
        for(int i = 0; i < array.length; ++i) {
            if (Objects.equal(value, array[i])) {
                return i;
            }
        }

        return -1;
    }

    public static <T> int lastIndexOf(T[] array, Object value) {
        for(int i = array.length - 1; i >= 0; --i) {
            if (Objects.equal(value, array[i])) {
                return i;
            }
        }

        return -1;
    }

    public static <T> boolean contains(T[] array, T value) {
        return indexOf(array, value) > -1;
    }

    public static int indexOf(long[] array, long value) {
        for(int i = 0; i < array.length; ++i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int lastIndexOf(long[] array, long value) {
        for(int i = array.length - 1; i >= 0; --i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static boolean contains(long[] array, long value) {
        return indexOf(array, value) > -1;
    }

    public static int indexOf(int[] array, int value) {
        for(int i = 0; i < array.length; ++i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int lastIndexOf(int[] array, int value) {
        for(int i = array.length - 1; i >= 0; --i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static boolean contains(int[] array, int value) {
        return indexOf(array, value) > -1;
    }

    public static int indexOf(short[] array, short value) {
        for(int i = 0; i < array.length; ++i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int lastIndexOf(short[] array, short value) {
        for(int i = array.length - 1; i >= 0; --i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static boolean contains(short[] array, short value) {
        return indexOf(array, value) > -1;
    }

    public static int indexOf(char[] array, char value) {
        for(int i = 0; i < array.length; ++i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int lastIndexOf(char[] array, char value) {
        for(int i = array.length - 1; i >= 0; --i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static boolean contains(char[] array, char value) {
        return indexOf(array, value) > -1;
    }

    public static int indexOf(byte[] array, byte value) {
        for(int i = 0; i < array.length; ++i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int lastIndexOf(byte[] array, byte value) {
        for(int i = array.length - 1; i >= 0; --i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static boolean contains(byte[] array, byte value) {
        return indexOf(array, value) > -1;
    }

    public static int indexOf(double[] array, double value) {
        for(int i = 0; i < array.length; ++i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int lastIndexOf(double[] array, double value) {
        for(int i = array.length - 1; i >= 0; --i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static boolean contains(double[] array, double value) {
        return indexOf(array, value) > -1;
    }

    public static int indexOf(float[] array, float value) {
        for(int i = 0; i < array.length; ++i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int lastIndexOf(float[] array, float value) {
        for(int i = array.length - 1; i >= 0; --i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static boolean contains(float[] array, float value) {
        return indexOf(array, value) > -1;
    }

    public static int indexOf(boolean[] array, boolean value) {
        for(int i = 0; i < array.length; ++i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int lastIndexOf(boolean[] array, boolean value) {
        for(int i = array.length - 1; i >= 0; --i) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static boolean contains(boolean[] array, boolean value) {
        return indexOf(array, value) > -1;
    }

    public static Integer[] wrap(int... values) {
        int length = values.length;
        Integer[] array = new Integer[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static int[] unWrap(Integer... values) {
        int length = values.length;
        int[] array = new int[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Long[] wrap(long... values) {
        int length = values.length;
        Long[] array = new Long[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static long[] unWrap(Long... values) {
        int length = values.length;
        long[] array = new long[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Character[] wrap(char... values) {
        int length = values.length;
        Character[] array = new Character[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static char[] unWrap(Character... values) {
        int length = values.length;
        char[] array = new char[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Byte[] wrap(byte... values) {
        int length = values.length;
        Byte[] array = new Byte[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static byte[] unWrap(Byte... values) {
        int length = values.length;
        byte[] array = new byte[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Short[] wrap(short... values) {
        int length = values.length;
        Short[] array = new Short[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static short[] unWrap(Short... values) {
        int length = values.length;
        short[] array = new short[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Float[] wrap(float... values) {
        int length = values.length;
        Float[] array = new Float[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static float[] unWrap(Float... values) {
        int length = values.length;
        float[] array = new float[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Double[] wrap(double... values) {
        int length = values.length;
        Double[] array = new Double[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static double[] unWrap(Double... values) {
        int length = values.length;
        double[] array = new double[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Boolean[] wrap(boolean... values) {
        int length = values.length;
        Boolean[] array = new Boolean[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static boolean[] unWrap(Boolean... values) {
        int length = values.length;
        boolean[] array = new boolean[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Object[] wrap(Object obj) {
        if (isArray(obj)) {
            try {
                return (Object[])obj;
            } catch (Exception var5) {
                String className = obj.getClass().getComponentType().getName();
                byte var4 = -1;
                switch(className.hashCode()) {
                    case -1325958191:
                        if (className.equals("double")) {
                            var4 = 7;
                        }
                        break;
                    case 104431:
                        if (className.equals("int")) {
                            var4 = 1;
                        }
                        break;
                    case 3039496:
                        if (className.equals("byte")) {
                            var4 = 4;
                        }
                        break;
                    case 3052374:
                        if (className.equals("char")) {
                            var4 = 3;
                        }
                        break;
                    case 3327612:
                        if (className.equals("long")) {
                            var4 = 0;
                        }
                        break;
                    case 64711720:
                        if (className.equals("boolean")) {
                            var4 = 5;
                        }
                        break;
                    case 97526364:
                        if (className.equals("float")) {
                            var4 = 6;
                        }
                        break;
                    case 109413500:
                        if (className.equals("short")) {
                            var4 = 2;
                        }
                }

                switch(var4) {
                    case 0:
                        return wrap((long[])obj);
                    case 1:
                        return wrap((int[])obj);
                    case 2:
                        return wrap((short[])obj);
                    case 3:
                        return wrap((char[])obj);
                    case 4:
                        return wrap((byte[])obj);
                    case 5:
                        return wrap((boolean[])obj);
                    case 6:
                        return wrap((float[])obj);
                    case 7:
                        return wrap((double[])obj);
                    default:
                        throw new UtilException(var5);
                }
            }
        } else {
            throw new UtilException(StringUtil.format("[{0}] is not Array!", new Object[]{obj.getClass()}));
        }
    }

    public static boolean isArray(Object obj) {
        if (null == obj) {
            throw new NullPointerException("Object check for isArray is null");
        } else {
            return obj.getClass().isArray();
        }
    }

    public static String toString(Object obj) {
        if (null == obj) {
            return null;
        } else if (isArray(obj)) {
            try {
                return Arrays.deepToString((Object[])obj);
            } catch (Exception var5) {
                String className = obj.getClass().getComponentType().getName();
                byte var4 = -1;
                switch(className.hashCode()) {
                    case -1325958191:
                        if (className.equals("double")) {
                            var4 = 7;
                        }
                        break;
                    case 104431:
                        if (className.equals("int")) {
                            var4 = 1;
                        }
                        break;
                    case 3039496:
                        if (className.equals("byte")) {
                            var4 = 4;
                        }
                        break;
                    case 3052374:
                        if (className.equals("char")) {
                            var4 = 3;
                        }
                        break;
                    case 3327612:
                        if (className.equals("long")) {
                            var4 = 0;
                        }
                        break;
                    case 64711720:
                        if (className.equals("boolean")) {
                            var4 = 5;
                        }
                        break;
                    case 97526364:
                        if (className.equals("float")) {
                            var4 = 6;
                        }
                        break;
                    case 109413500:
                        if (className.equals("short")) {
                            var4 = 2;
                        }
                }

                switch(var4) {
                    case 0:
                        return Arrays.toString((long[])obj);
                    case 1:
                        return Arrays.toString((int[])obj);
                    case 2:
                        return Arrays.toString((short[])obj);
                    case 3:
                        return Arrays.toString((char[])obj);
                    case 4:
                        return Arrays.toString((byte[])obj);
                    case 5:
                        return Arrays.toString((boolean[])obj);
                    case 6:
                        return Arrays.toString((float[])obj);
                    case 7:
                        return Arrays.toString((double[])obj);
                    default:
                        throw new UtilException(var5);
                }
            }
        } else {
            return obj.toString();
        }
    }

    public static int length(Object array) {
        return null == array ? 0 : Array.getLength(array);
    }

    public static <T> String join(T[] array, CharSequence conjunction) {
        if (null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            Object[] var4 = array;
            int var5 = array.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                T item = (T) var4[var6];
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                if (isArray(item)) {
                    sb.append(join(wrap(item), conjunction));
                } else if (item instanceof Iterable) {
                    sb.append(CollectionUtil.join((Iterable)item, conjunction));
                } else if (item instanceof Iterator) {
                    sb.append(CollectionUtil.join((Iterator)item, conjunction));
                } else {
                    sb.append(item);
                }
            }

            return sb.toString();
        }
    }

    public static String join(long[] array, String conjunction) {
        if (null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            long[] var4 = array;
            int var5 = array.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                long item = var4[var6];
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(int[] array, String conjunction) {
        if (null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            int[] var4 = array;
            int var5 = array.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                int item = var4[var6];
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(short[] array, String conjunction) {
        if (null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            short[] var4 = array;
            int var5 = array.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                short item = var4[var6];
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(char[] array, String conjunction) {
        if (null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            char[] var4 = array;
            int var5 = array.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                char item = var4[var6];
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(byte[] array, String conjunction) {
        if (null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            byte[] var4 = array;
            int var5 = array.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                byte item = var4[var6];
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(boolean[] array, String conjunction) {
        if (null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            boolean[] var4 = array;
            int var5 = array.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                boolean item = var4[var6];
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(float[] array, String conjunction) {
        if (null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            float[] var4 = array;
            int var5 = array.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                float item = var4[var6];
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(double[] array, String conjunction) {
        if (null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            double[] var4 = array;
            int var5 = array.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                double item = var4[var6];
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(Object array, CharSequence conjunction) {
        if (isArray(array)) {
            Class<?> componentType = array.getClass().getComponentType();
            if (componentType.isPrimitive()) {
                String componentTypeName = componentType.getName();
                byte var5 = -1;
                switch(componentTypeName.hashCode()) {
                    case -1325958191:
                        if (componentTypeName.equals("double")) {
                            var5 = 7;
                        }
                        break;
                    case 104431:
                        if (componentTypeName.equals("int")) {
                            var5 = 1;
                        }
                        break;
                    case 3039496:
                        if (componentTypeName.equals("byte")) {
                            var5 = 4;
                        }
                        break;
                    case 3052374:
                        if (componentTypeName.equals("char")) {
                            var5 = 3;
                        }
                        break;
                    case 3327612:
                        if (componentTypeName.equals("long")) {
                            var5 = 0;
                        }
                        break;
                    case 64711720:
                        if (componentTypeName.equals("boolean")) {
                            var5 = 5;
                        }
                        break;
                    case 97526364:
                        if (componentTypeName.equals("float")) {
                            var5 = 6;
                        }
                        break;
                    case 109413500:
                        if (componentTypeName.equals("short")) {
                            var5 = 2;
                        }
                }

                switch(var5) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        return join(array, conjunction);
                    default:
                        throw new UtilException("Unknown primitive type: [{0}]", new Object[]{componentTypeName});
                }
            } else {
                return join((Object[])array, conjunction);
            }
        } else {
            throw new UtilException(StringUtil.format("[{0}] is not a Array!", new Object[]{array.getClass()}));
        }
    }

    public static byte[] toArray(ByteBuffer bytebuffer) {
        if (!bytebuffer.hasArray()) {
            int oldPosition = bytebuffer.position();
            bytebuffer.position(0);
            int size = bytebuffer.limit();
            byte[] buffers = new byte[size];
            bytebuffer.get(buffers);
            bytebuffer.position(oldPosition);
            return buffers;
        } else {
            return Arrays.copyOfRange(bytebuffer.array(), bytebuffer.position(), bytebuffer.limit());
        }
    }

    public static <T> T[] remove(T[] array, int index) {
        return remove(array, index);
    }

    public static long[] remove(long[] array, int index) {
        return (long[])remove((Object)array, index);
    }

    public static int[] remove(int[] array, int index) {
        return (int[])remove((Object)array, index);
    }

    public static short[] remove(short[] array, int index) {
        return (short[])remove((Object)array, index);
    }

    public static char[] remove(char[] array, int index) {
        return (char[])remove((Object)array, index);
    }

    public static byte[] remove(byte[] array, int index) {
        return (byte[])remove((Object)array, index);
    }

    public static double[] remove(double[] array, int index) {
        return (double[])remove((Object)array, index);
    }

    public static float[] remove(float[] array, int index) {
        return (float[])remove((Object)array, index);
    }

    public static boolean[] remove(boolean[] array, int index) {
        return (boolean[])remove((Object)array, index);
    }

    public static Object remove(Object array, int index) {
        if (null == array) {
            return array;
        } else {
            int length = length(array);
            if (index >= 0 && index < length) {
                Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
                System.arraycopy(array, 0, result, 0, index);
                if (index < length - 1) {
                    System.arraycopy(array, index + 1, result, index, length - index - 1);
                }

                return result;
            } else {
                return array;
            }
        }
    }

    public static <T> T[] removeEle(T[] array, T element) {
        return remove(array, indexOf(array, element));
    }

    public static long[] removeEle(long[] array, long element) throws IllegalArgumentException {
        return remove(array, indexOf(array, element));
    }

    public static int[] removeEle(int[] array, int element) throws IllegalArgumentException {
        return remove(array, indexOf(array, element));
    }

    public static short[] removeEle(short[] array, short element) {
        return remove(array, indexOf(array, element));
    }

    public static char[] removeEle(char[] array, char element) {
        return remove(array, indexOf(array, element));
    }

    public static byte[] removeEle(byte[] array, byte element) {
        return remove(array, indexOf(array, element));
    }

    public static double[] removeEle(double[] array, double element) {
        return remove(array, indexOf(array, element));
    }

    public static float[] removeEle(float[] array, float element) {
        return remove(array, indexOf(array, element));
    }

    public static boolean[] removeEle(boolean[] array, boolean element) {
        return remove(array, indexOf(array, element));
    }
}

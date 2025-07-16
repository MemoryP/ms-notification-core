package com.ocbc.ms.notification.cn.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");
    public static final char C_SPACE = ' ';
    public static final char C_TAB = '\t';
    public static final char C_DOT = '.';
    public static final char C_SLASH = '/';
    public static final char C_BACKSLASH = '\\';
    public static final char C_CR = '\r';
    public static final char C_LF = '\n';
    public static final char C_UNDERLINE = '_';
    public static final char C_COMMA = ',';
    public static final char C_DELIM_START = '{';
    public static final char C_DELIM_END = '}';
    public static final char C_BRACKET_START = '[';
    public static final char C_BRACKET_END = ']';
    public static final char C_COLON = ':';
    public static final String SPACE = " ";
    public static final String TAB = "\t";
    public static final String DOT = ".";
    public static final String DOUBLE_DOT = "..";
    public static final String SLASH = "/";
    public static final String BACKSLASH = "\\";
    public static final String EMPTY = "";
    public static final String CR = "\r";
    public static final String LF = "\n";
    public static final String CRLF = "\r\n";
    public static final String UNDERLINE = "_";
    public static final String COMMA = ",";
    public static final String DELIM_START = "{";
    public static final String DELIM_END = "}";
    public static final String BRACKET_START = "[";
    public static final String BRACKET_END = "]";
    public static final String COLON = ":";
    public static final String HTML_NBSP = "&nbsp;";
    public static final String HTML_AMP = "&amp";
    public static final String HTML_QUOTE = "&quot;";
    public static final String HTML_LT = "&lt;";
    public static final String HTML_GT = "&gt;";
    public static final String EMPTY_JSON = "{}";

    private StringUtil() {
    }

    public static boolean isBlank(CharSequence str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for(int i = 0; i < length; ++i) {
                if (!NumberUtil.isBlankChar(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    public static boolean hasBlank(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return true;
        } else {
            CharSequence[] var1 = strs;
            int var2 = strs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence str = var1[var3];
                if (isBlank(str)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isAllBlank(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return true;
        } else {
            CharSequence[] var1 = strs;
            int var2 = strs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence str = var1[var3];
                if (isNotBlank(str)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    public static String nullToEmpty(CharSequence str) {
        return nullToDefault(str, "");
    }

    public static String nullToDefault(CharSequence str, String defaultStr) {
        return str == null ? defaultStr : str.toString();
    }

    public static String emptyToNull(CharSequence str) {
        return isEmpty(str) ? null : str.toString();
    }

    public static boolean hasEmpty(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return true;
        } else {
            CharSequence[] var1 = strs;
            int var2 = strs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence str = var1[var3];
                if (isEmpty(str)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isAllEmpty(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return true;
        } else {
            CharSequence[] var1 = strs;
            int var2 = strs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence str = var1[var3];
                if (isNotEmpty(str)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String trim(CharSequence str) {
        return null == str ? null : trim(str, 0);
    }

    public static void trim(String[] strs) {
        if (null != strs) {
            for(int i = 0; i < strs.length; ++i) {
                String str = strs[i];
                if (null != str) {
                    strs[i] = str.trim();
                }
            }

        }
    }

    public static String trimStart(CharSequence str) {
        return trim(str, -1);
    }

    public static String trimEnd(CharSequence str) {
        return trim(str, 1);
    }

    public static String trim(CharSequence str, int mode) {
        if (str == null) {
            return null;
        } else {
            int length = str.length();
            int start = 0;
            int end = length;
            if (mode <= 0) {
                while(start < end && NumberUtil.isBlankChar(str.charAt(start))) {
                    ++start;
                }
            }

            if (mode >= 0) {
                while(start < end && NumberUtil.isBlankChar(str.charAt(end - 1))) {
                    --end;
                }
            }

            return start <= 0 && end >= length ? str.toString() : str.toString().substring(start, end);
        }
    }

    public static boolean startWith(CharSequence str, char c) {
        return c == str.charAt(0);
    }

    public static boolean startWith(CharSequence str, CharSequence prefix, boolean isIgnoreCase) {
        return isIgnoreCase ? str.toString().toLowerCase().startsWith(prefix.toString().toLowerCase()) : str.toString().startsWith(prefix.toString());
    }

    public static boolean startWith(CharSequence str, CharSequence prefix) {
        return startWith(str, prefix, false);
    }

    public static boolean startWithIgnoreCase(String str, String prefix) {
        return startWith(str, prefix, true);
    }

    public static boolean startWithAny(CharSequence str, CharSequence... prefixes) {
        if (!isEmpty(str) && !ArrayUtil.isEmpty(prefixes)) {
            CharSequence[] var2 = prefixes;
            int var3 = prefixes.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                CharSequence suffix = var2[var4];
                if (startWith(str, suffix, false)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static boolean endWith(CharSequence str, char c) {
        return c == str.charAt(str.length() - 1);
    }

    public static boolean endWith(CharSequence str, CharSequence suffix, boolean isIgnoreCase) {
        if (!isBlank(str) && !isBlank(suffix)) {
            return isIgnoreCase ? str.toString().toLowerCase().endsWith(suffix.toString().toLowerCase()) : str.toString().endsWith(suffix.toString());
        } else {
            return false;
        }
    }

    public static boolean endWith(CharSequence str, CharSequence suffix) {
        return endWith(str, suffix, false);
    }

    public static boolean endWithIgnoreCase(CharSequence str, String suffix) {
        return endWith(str, suffix, true);
    }

    public static boolean endWithAny(CharSequence str, CharSequence... suffixes) {
        if (!isEmpty(str) && !ArrayUtil.isEmpty(suffixes)) {
            CharSequence[] var2 = suffixes;
            int var3 = suffixes.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                CharSequence suffix = var2[var4];
                if (endWith(str, suffix, false)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static boolean containsIgnoreCase(CharSequence str, CharSequence testStr) {
        if (null == str) {
            return null == testStr;
        } else {
            return str.toString().toLowerCase().contains(testStr.toString().toLowerCase());
        }
    }

    public static String getGeneralField(CharSequence getOrSetMethodName) {
        String getOrSetMethodNameStr = getOrSetMethodName.toString();
        return !getOrSetMethodNameStr.startsWith("get") && !getOrSetMethodNameStr.startsWith("set") ? null : removePreAndLowerFirst(getOrSetMethodName, 3);
    }

    public static String genSetter(CharSequence fieldName) {
        return upperFirstAndAddPre(fieldName, "set");
    }

    public static String genGetter(String fieldName) {
        return upperFirstAndAddPre(fieldName, "get");
    }

    public static String removeAll(String str, CharSequence strToRemove) {
        return str.replace(strToRemove, "");
    }

    public static String removePreAndLowerFirst(CharSequence str, int preLength) {
        if (str == null) {
            return null;
        } else if (str.length() > preLength) {
            char first = Character.toLowerCase(str.charAt(preLength));
            return str.length() > preLength + 1 ? first + str.toString().substring(preLength + 1) : String.valueOf(first);
        } else {
            return str.toString();
        }
    }

    public static String removePreAndLowerFirst(CharSequence str, CharSequence prefix) {
        return lowerFirst(removePrefix(str, prefix));
    }

    public static String upperFirstAndAddPre(CharSequence str, String preString) {
        return str != null && preString != null ? preString + upperFirst(str) : null;
    }

    public static String upperFirst(CharSequence str) {
        if (null == str) {
            return null;
        } else {
            if (str.length() > 0) {
                char firstChar = str.charAt(0);
                if (Character.isLowerCase(firstChar)) {
                    return Character.toUpperCase(firstChar) + subSuf(str, 1);
                }
            }

            return str.toString();
        }
    }

    public static String lowerFirst(CharSequence str) {
        if (null == str) {
            return null;
        } else {
            if (str.length() > 0) {
                char firstChar = str.charAt(0);
                if (Character.isUpperCase(firstChar)) {
                    return Character.toLowerCase(firstChar) + subSuf(str, 1);
                }
            }

            return str.toString();
        }
    }

    public static String removePrefix(CharSequence str, CharSequence prefix) {
        if (isEmpty(str)) {
            return null;
        } else if (isEmpty(prefix)) {
            return str.toString();
        } else {
            String str2 = str.toString();
            return str2.startsWith(prefix.toString()) ? subSuf(str2, prefix.length()) : str2;
        }
    }

    public static String removePrefixIgnoreCase(CharSequence str, String prefix) {
        if (isEmpty(str)) {
            return null;
        } else if (isEmpty(prefix)) {
            return str.toString();
        } else {
            String str2 = str.toString();
            return str2.toLowerCase().startsWith(prefix.toLowerCase()) ? subSuf(str2, prefix.length()) : str2;
        }
    }

    public static String removeSuffix(CharSequence str, CharSequence suffix) {
        if (isEmpty(str)) {
            return null;
        } else if (isEmpty(suffix)) {
            return str.toString();
        } else {
            String str2 = str.toString();
            return str2.endsWith(suffix.toString()) ? subPre(str2, str2.length() - suffix.length()) : str2;
        }
    }

    public static String removeSufAndLowerFirst(CharSequence str, CharSequence suffix) {
        return lowerFirst(removeSuffix(str, suffix));
    }

    public static String removeSuffixIgnoreCase(CharSequence str, CharSequence suffix) {
        if (isEmpty(str)) {
            return null;
        } else if (isEmpty(suffix)) {
            return str.toString();
        } else {
            String str2 = str.toString();
            return str2.toLowerCase().endsWith(suffix.toString().toLowerCase()) ? subPre(str2, str2.length() - suffix.length()) : str2;
        }
    }

    public static String addPrefixIfNot(CharSequence str, CharSequence prefix) {
        if (isEmpty(str)) {
            return null;
        } else if (isEmpty(prefix)) {
            return str.toString();
        } else {
            String str2 = str.toString();
            String prefix2 = prefix.toString();
            return !str2.startsWith(prefix2) ? prefix2.concat(str2) : str2;
        }
    }

    public static String addSuffixIfNot(CharSequence str, CharSequence suffix) {
        if (isEmpty(str)) {
            return null;
        } else if (isEmpty(suffix)) {
            return str.toString();
        } else {
            String str2 = str.toString();
            String suffix2 = suffix.toString();
            return !str2.endsWith(suffix2) ? str2.concat(suffix2) : str2;
        }
    }

    public static String cleanBlank(CharSequence str) {
        if (str == null) {
            return null;
        } else {
            int len = str.length();
            StringBuilder sb = new StringBuilder(str.length());

            for(int i = 0; i < len; ++i) {
                char c = str.charAt(i);
                if (!NumberUtil.isBlankChar(c)) {
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }

    public static String[] splitToArray(CharSequence str, char separator) {
        return splitToArray(str, separator, 0);
    }

    public static List<String> split(CharSequence str, char separator) {
        return split(str, separator, 0);
    }

    public static String[] splitToArray(CharSequence str, char separator, int limit) {
        return null == str ? new String[0] : StrSpliter.splitToArray(str.toString(), separator, limit, false, false);
    }

    public static List<String> split(CharSequence str, char separator, int limit) {
        return split(str, separator, limit, false, false);
    }

    public static List<String> split(CharSequence str, char separator, boolean isTrim, boolean ignoreEmpty) {
        return split(str, separator, 0, isTrim, ignoreEmpty);
    }

    public static List<String> split(CharSequence str, char separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        return (List)(null == str ? new ArrayList(0) : StrSpliter.split(str.toString(), separator, limit, isTrim, ignoreEmpty));
    }

    public static String[] split(CharSequence str, CharSequence separator) {
        if (str == null) {
            return new String[0];
        } else {
            String separatorStr = null == separator ? null : separator.toString();
            return StrSpliter.splitToArray(str.toString(), separatorStr, 0, false, false);
        }
    }

    public static String[] split(CharSequence str, int len) {
        return null == str ? new String[0] : StrSpliter.splitByLength(str.toString(), len);
    }

    public static String sub(CharSequence string, int fromIndex, int toIndex) {
        int len = string.length();
        if (fromIndex < 0) {
            fromIndex += len;
            if (fromIndex < 0) {
                fromIndex = 0;
            }
        } else if (fromIndex > len) {
            fromIndex = len;
        }

        if (toIndex < 0) {
            toIndex += len;
            if (toIndex < 0) {
                toIndex = len;
            }
        } else if (toIndex > len) {
            toIndex = len;
        }

        if (toIndex < fromIndex) {
            int tmp = fromIndex;
            fromIndex = toIndex;
            toIndex = tmp;
        }

        return fromIndex == toIndex ? "" : string.toString().substring(fromIndex, toIndex);
    }

    public static String subPre(CharSequence string, int toIndex) {
        return sub(string, 0, toIndex);
    }

    public static String subSuf(CharSequence string, int fromIndex) {
        return isEmpty(string) ? null : sub(string, fromIndex, string.length());
    }

    public static boolean isSurround(CharSequence str, CharSequence prefix, CharSequence suffix) {
        if (isBlank(str)) {
            return false;
        } else if (str.length() < prefix.length() + suffix.length()) {
            return false;
        } else {
            String str2 = str.toString();
            return str2.startsWith(prefix.toString()) && str2.endsWith(suffix.toString());
        }
    }

    public static boolean isSurround(CharSequence str, char prefix, char suffix) {
        if (isBlank(str)) {
            return false;
        } else if (str.length() < 2) {
            return false;
        } else {
            return str.charAt(0) == prefix && str.charAt(str.length() - 1) == suffix;
        }
    }

    public static String repeat(char c, int count) {
        if (count <= 0) {
            return "";
        } else {
            char[] result = new char[count];

            for(int i = 0; i < count; ++i) {
                result[i] = c;
            }

            return new String(result);
        }
    }

    public static String repeat(CharSequence str, int count) {
        if (null == str) {
            return null;
        } else if (count <= 0) {
            return "";
        } else if (count != 1 && str.length() != 0) {
            int len = str.length();
            long longSize = (long)len * (long)count;
            int size = (int)longSize;
            if ((long)size != longSize) {
                throw new ArrayIndexOutOfBoundsException("Required String length is too large: " + longSize);
            } else {
                char[] array = new char[size];
                str.toString().getChars(0, len, array, 0);

                int n;
                for(n = len; n < size - n; n <<= 1) {
                    System.arraycopy(array, 0, array, n, n);
                }

                System.arraycopy(array, 0, array, n, size - n);
                return new String(array);
            }
        } else {
            return str.toString();
        }
    }

    public static boolean equals(CharSequence str1, CharSequence str2) {
        if (str1 == null) {
            return str2 == null;
        } else {
            return str1.equals(str2);
        }
    }

    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        if (str1 == null) {
            return str2 == null;
        } else {
            return str1.toString().equalsIgnoreCase(str2.toString());
        }
    }

    public static String format(CharSequence template, Object... params) {
        if (null == template) {
            return null;
        } else {
            return !ArrayUtil.isEmpty(params) && !isBlank(template) ? StrFormatter.format(template.toString(), params) : template.toString();
        }
    }

    public static String indexedFormat(CharSequence pattern, Object... arguments) {
        return MessageFormat.format(pattern.toString(), arguments);
    }

    public static String format(CharSequence template, Map<?, ?> map) {
        if (null == template) {
            return null;
        } else if (null != map && !map.isEmpty()) {
            String template2 = ((CharSequence)template).toString();

            Entry entry;
            for(Iterator var3 = map.entrySet().iterator(); var3.hasNext(); template = template2.replace("{" + entry.getKey() + "}", utf8Str(entry.getValue()))) {
                entry = (Entry)var3.next();
            }

            return ((CharSequence)template).toString();
        } else {
            return ((CharSequence)template).toString();
        }
    }

    public static byte[] utf8Bytes(CharSequence str) {
        return bytes(str, CharsetUtil.CHARSET_UTF_8);
    }

    public static byte[] bytes(CharSequence str) {
        return bytes(str, Charset.defaultCharset());
    }

    public static byte[] bytes(CharSequence str, String charset) {
        return bytes(str, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    public static byte[] bytes(CharSequence str, Charset charset) {
        if (str == null) {
            return new byte[0];
        } else {
            return null == charset ? str.toString().getBytes() : str.toString().getBytes(charset);
        }
    }

    public static String utf8Str(Object obj) {
        return str(obj, CharsetUtil.CHARSET_UTF_8);
    }

    public static String str(Object obj, String charsetName) {
        return str(obj, Charset.forName(charsetName));
    }

    public static String str(Object obj, Charset charset) {
        if (null == obj) {
            return null;
        } else if (obj instanceof String) {
            return (String)obj;
        } else if (obj instanceof byte[]) {
            return str((byte[])obj, charset);
        } else if (obj instanceof Byte[]) {
            return str((Byte[])obj, charset);
        } else if (obj instanceof ByteBuffer) {
            return str((ByteBuffer)obj, charset);
        } else {
            return ArrayUtil.isArray(obj) ? ArrayUtil.toString(obj) : obj.toString();
        }
    }

    public static String str(byte[] bytes, String charset) {
        return str(bytes, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    public static String str(byte[] data, Charset charset) {
        if (data == null) {
            return null;
        } else {
            return null == charset ? new String(data) : new String(data, charset);
        }
    }

    public static String str(Byte[] bytes, String charset) {
        return str(bytes, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    public static String str(Byte[] data, Charset charset) {
        if (data == null) {
            return null;
        } else {
            byte[] bytes = new byte[data.length];

            for(int i = 0; i < data.length; ++i) {
                Byte dataByte = data[i];
                bytes[i] = null == dataByte ? -1 : dataByte;
            }

            return str(bytes, charset);
        }
    }

    public static String str(ByteBuffer data, String charset) {
        return data == null ? null : str(data, Charset.forName(charset));
    }

    public static String str(ByteBuffer data, Charset charset) {
        if (null == charset) {
            charset = Charset.defaultCharset();
        }

        return charset.decode(data).toString();
    }

    public static ByteBuffer byteBuffer(CharSequence str, String charset) {
        return ByteBuffer.wrap(bytes(str, charset));
    }

    public static String join(CharSequence conjunction, Object... objs) {
        return ArrayUtil.join(objs, conjunction);
    }

    public static String toUnderlineCase(CharSequence camelCaseStr) {
        if (camelCaseStr == null) {
            return null;
        } else {
            int length = camelCaseStr.length();
            StringBuilder sb = new StringBuilder();
            boolean isPreUpperCase = false;

            for(int i = 0; i < length; ++i) {
                char c = camelCaseStr.charAt(i);
                boolean isNextUpperCase = true;
                if (i < length - 1) {
                    isNextUpperCase = Character.isUpperCase(camelCaseStr.charAt(i + 1));
                }

                if (!Character.isUpperCase(c)) {
                    isPreUpperCase = false;
                } else {
                    if ((!isPreUpperCase || !isNextUpperCase) && i > 0) {
                        sb.append("_");
                    }

                    isPreUpperCase = true;
                }

                sb.append(Character.toLowerCase(c));
            }

            return sb.toString();
        }
    }

    public static String toCamelCase(CharSequence name) {
        if (null == name) {
            return null;
        } else {
            String name2 = name.toString();
            if (name2.contains("_")) {
                name2 = name2.toLowerCase();
                StringBuilder sb = new StringBuilder(name2.length());
                boolean upperCase = false;

                for(int i = 0; i < name2.length(); ++i) {
                    char c = name2.charAt(i);
                    if (c == '_') {
                        upperCase = true;
                    } else if (upperCase) {
                        sb.append(Character.toUpperCase(c));
                        upperCase = false;
                    } else {
                        sb.append(c);
                    }
                }

                return sb.toString();
            } else {
                return name2;
            }
        }
    }

    public static String wrap(CharSequence str, CharSequence prefix, CharSequence suffix) {
        return nullToEmpty(prefix).concat(nullToEmpty(str)).concat(nullToEmpty(suffix));
    }

    public static String wrapIfMissing(CharSequence str, CharSequence prefix, CharSequence suffix) {
        int len = 0;
        if (isNotEmpty(str)) {
            len += str.length();
        }

        if (isNotEmpty(prefix)) {
            len += str.length();
        }

        if (isNotEmpty(suffix)) {
            len += str.length();
        }

        StringBuilder sb = new StringBuilder(len);
        if (isNotEmpty(prefix) && !startWith(str, prefix)) {
            sb.append(prefix);
        }

        if (isNotEmpty(str)) {
            sb.append(str);
        }

        if (isNotEmpty(suffix) && !endWith(str, suffix)) {
            sb.append(suffix);
        }

        return sb.toString();
    }

    public static boolean isWrap(CharSequence str, String prefix, String suffix) {
        if (ArrayUtil.hasNull(new CharSequence[]{str, prefix, suffix})) {
            return false;
        } else {
            String str2 = str.toString();
            return str2.startsWith(prefix) && str2.endsWith(suffix);
        }
    }

    public static boolean isWrap(CharSequence str, String wrapper) {
        return isWrap(str, wrapper, wrapper);
    }

    public static boolean isWrap(CharSequence str, char wrapper) {
        return isWrap(str, wrapper, wrapper);
    }

    public static boolean isWrap(CharSequence str, char prefixChar, char suffixChar) {
        if (null == str) {
            return false;
        } else {
            return str.charAt(0) == prefixChar && str.charAt(str.length() - 1) == suffixChar;
        }
    }

    public static String padPre(CharSequence str, int minLength, char padChar) {
        if (null == str) {
            str = "";
        } else if (((CharSequence)str).length() >= minLength) {
            return ((CharSequence)str).toString();
        }

        return repeat(padChar, minLength - ((CharSequence)str).length()).concat(((CharSequence)str).toString());
    }

    public static String padEnd(CharSequence str, int minLength, char padChar) {
        if (null == str) {
            str = "";
        } else if (((CharSequence)str).length() >= minLength) {
            return ((CharSequence)str).toString();
        }

        return ((CharSequence)str).toString().concat(repeat(padChar, minLength - ((CharSequence)str).length()));
    }

    public static StringBuilder builder() {
        return new StringBuilder();
    }

    public static StringBuilder builder(int capacity) {
        return new StringBuilder(capacity);
    }

    public static StringBuilder builder(CharSequence... strs) {
        StringBuilder sb = new StringBuilder();
        CharSequence[] var2 = strs;
        int var3 = strs.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            CharSequence str = var2[var4];
            sb.append(str);
        }

        return sb;
    }

    public static StringReader getReader(CharSequence str) {
        return null == str ? null : new StringReader(str.toString());
    }

    public static StringWriter getWriter() {
        return new StringWriter();
    }

    public static int count(CharSequence content, CharSequence strForSearch) {
        if (!hasEmpty(content, strForSearch) && strForSearch.length() <= content.length()) {
            int count = 0;
            int idx = 0;
            String content2 = content.toString();

            for(String strForSearch2 = strForSearch.toString(); (idx = content2.indexOf(strForSearch2, idx)) > -1; idx += strForSearch.length()) {
                ++count;
            }

            return count;
        } else {
            return 0;
        }
    }

    public static int count(CharSequence content, char charForSearch) {
        int count = 0;
        if (isEmpty(content)) {
            return 0;
        } else {
            int contentLength = content.length();

            for(int i = 0; i < contentLength; ++i) {
                if (charForSearch == content.charAt(i)) {
                    ++count;
                }
            }

            return count;
        }
    }

    public static String[] cut(CharSequence str, int partLength) {
        if (null == str) {
            return new String[0];
        } else {
            int len = str.length();
            if (len < partLength) {
                return new String[]{str.toString()};
            } else {
                int part = NumberUtil.count(len, partLength);
                String[] array = new String[part];
                String str2 = str.toString();

                for(int i = 0; i < part; ++i) {
                    array[i] = str2.substring(i * partLength, i == part - 1 ? len : partLength + i * partLength);
                }

                return array;
            }
        }
    }

    public static String brief(CharSequence str, int maxLength) {
        if (null == str) {
            return null;
        } else if (str.length() + 3 <= maxLength) {
            return str.toString();
        } else {
            int w = maxLength / 2;
            int l = str.length();
            String str2 = str.toString();
            return format("{0}...{1}", str2.substring(0, maxLength - w), str2.substring(l - w));
        }
    }

    public static int compare(CharSequence str1, CharSequence str2, boolean nullIsLess) {
        if (str1 == str2) {
            return 0;
        } else if (str1 == null) {
            return nullIsLess ? -1 : 1;
        } else if (str2 == null) {
            return nullIsLess ? 1 : -1;
        } else {
            return str1.toString().compareTo(str2.toString());
        }
    }

    public static int compareIgnoreCase(CharSequence str1, CharSequence str2, boolean nullIsLess) {
        if (str1 == str2) {
            return 0;
        } else if (str1 == null) {
            return nullIsLess ? -1 : 1;
        } else if (str2 == null) {
            return nullIsLess ? 1 : -1;
        } else {
            return str1.toString().compareToIgnoreCase(str2.toString());
        }
    }

    public static int indexOf(CharSequence str, char searchChar) {
        return indexOf(str, searchChar, 0);
    }

    public static int indexOf(CharSequence str, char searchChar, int start) {
        return str instanceof String ? ((String)str).indexOf(searchChar, start) : indexOf(str, searchChar, start, -1);
    }

    public static int indexOf(CharSequence str, char searchChar, int start, int end) {
        int len = str.length();
        if (start < 0 || start > len) {
            start = 0;
        }

        if (end > len || end < 0) {
            end = len;
        }

        for(int i = start; i < end; ++i) {
            if (str.charAt(i) == searchChar) {
                return i;
            }
        }

        return -1;
    }

    public static String appendIfMissing(CharSequence str, CharSequence suffix, CharSequence... suffixes) {
        return appendIfMissing(str, suffix, false, suffixes);
    }

    public static String appendIfMissingIgnoreCase(CharSequence str, CharSequence suffix, CharSequence... suffixes) {
        return appendIfMissing(str, suffix, true, suffixes);
    }

    public static String appendIfMissing(CharSequence str, CharSequence suffix, boolean ignoreCase, CharSequence... suffixes) {
        if (str == null) {
            return null;
        } else if (!isEmpty(suffix) && !endWith(str, suffix, ignoreCase)) {
            if (suffixes != null && suffixes.length > 0) {
                CharSequence[] var4 = suffixes;
                int var5 = suffixes.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    CharSequence s = var4[var6];
                    if (endWith(str, s, ignoreCase)) {
                        return str.toString();
                    }
                }
            }

            return str.toString().concat(suffix.toString());
        } else {
            return str.toString();
        }
    }

    public static String prependIfMissing(CharSequence str, CharSequence prefix, CharSequence... prefixes) {
        return prependIfMissing(str, prefix, false, prefixes);
    }

    public static String prependIfMissingIgnoreCase(CharSequence str, CharSequence prefix, CharSequence... prefixes) {
        return prependIfMissing(str, prefix, true, prefixes);
    }

    public static String prependIfMissing(CharSequence str, CharSequence prefix, boolean ignoreCase, CharSequence... prefixes) {
        if (str == null) {
            return null;
        } else if (!isEmpty(prefix) && !startWith(str, prefix, ignoreCase)) {
            if (prefixes != null && prefixes.length > 0) {
                CharSequence[] var4 = prefixes;
                int var5 = prefixes.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    CharSequence s = var4[var6];
                    if (startWith(str, s, ignoreCase)) {
                        return str.toString();
                    }
                }
            }

            return prefix.toString().concat(str.toString());
        } else {
            return str.toString();
        }
    }

    public static boolean isEquals(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        } else {
            return s1 != null && s2 != null ? s1.equals(s2) : false;
        }
    }

    public static boolean isInteger(String str) {
        return str != null && str.length() != 0 ? INT_PATTERN.matcher(str).matches() : false;
    }

    public static int parseInteger(String str) {
        return !isInteger(str) ? 0 : Integer.parseInt(str);
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for(int i = 0; i < sz; ++i) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String format(String messageTemplate, Object... args) {
        if (messageTemplate != null && !messageTemplate.isEmpty()) {
            if (args != null && args.length != 0) {
                StringBuilder sb = new StringBuilder(messageTemplate);

                for(int i = 0; i < args.length; ++i) {
                    String fmt = String.format("{%d}", i);
                    int idx = sb.indexOf(fmt);
                    if (idx >= 0) {
                        if (args[i] != null) {
                            sb.replace(idx, idx + fmt.length(), args[i].toString());
                        } else {
                            sb.replace(idx, idx + fmt.length(), "null");
                        }
                    }
                }

                return sb.toString();
            } else {
                return messageTemplate;
            }
        } else {
            return messageTemplate;
        }
    }

    public static String dup(char c, int num) {
        if (c != 0 && num >= 1) {
            StringBuilder sb = new StringBuilder(num);

            for(int i = 0; i < num; ++i) {
                sb.append(c);
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    public static String dup(String c, int num) {
        if (c != null && num >= 1) {
            StringBuilder sb = new StringBuilder(num);

            for(int i = 0; i < num; ++i) {
                sb.append(c);
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    public static String alignRight(Object o, int width, char c) {
        String s = "";
        if (null == o) {
            s = "";
        } else {
            s = o.toString();
        }

        int len = s.length();
        return len >= width ? s : dup(c, width - len) + s;
    }

    public static String alignRight(Object o, int width, char c, String charsetName) {
        String s;
        if (null == o) {
            s = "";
        } else {
            s = o.toString();
        }

        int len = s.length();
        if (charsetName != null && !charsetName.isEmpty()) {
            try {
                len = s.getBytes(charsetName).length;
            } catch (UnsupportedEncodingException var7) {
            }
        }

        return len >= width ? s : dup(c, width - len) + s;
    }

    public static String alignLeft(Object o, int width, char c) {
        String s = "";
        if (null == o) {
            s = "";
        } else {
            s = o.toString();
        }

        int length = s.length();
        return length >= width ? s : s + dup(c, width - length);
    }

    public static String alignLeft(Object o, int width, char c, String charsetName) {
        String s = "";
        if (null == o) {
            s = "";
        } else {
            s = o.toString();
        }

        int length = s.length();
        if (charsetName != null && !charsetName.isEmpty()) {
            try {
                length = s.getBytes(charsetName).length;
            } catch (UnsupportedEncodingException var7) {
            }
        }

        return length >= width ? s : s + dup(c, width - length);
    }
}

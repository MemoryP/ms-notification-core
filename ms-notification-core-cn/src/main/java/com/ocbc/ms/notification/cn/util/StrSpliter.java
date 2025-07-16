package com.ocbc.ms.notification.cn.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrSpliter {
    private StrSpliter() {
    }

    public static List<String> splitPath(String str) {
        return splitPath(str, 0);
    }

    public static String[] splitPathToArray(String str) {
        return toArray(splitPath(str));
    }

    public static List<String> splitPath(String str, int limit) {
        return split(str, '/', limit, true, true);
    }

    public static String[] splitPathToArray(String str, int limit) {
        return toArray(splitPath(str, limit));
    }

    public static List<String> split(String str, char separator, boolean isTrim, boolean ignoreEmpty) {
        return split(str, separator, 0, isTrim, ignoreEmpty);
    }

    public static List<String> split(String str, char separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        if (StringUtil.isEmpty(str)) {
            return new ArrayList(0);
        } else if (limit == 1) {
            return addToList(new ArrayList(1), str, isTrim, ignoreEmpty);
        } else {
            ArrayList<String> list = new ArrayList(limit > 0 ? limit : 16);
            int len = str.length();
            int start = 0;

            for(int i = 0; i < len; ++i) {
                if (separator == str.charAt(i)) {
                    addToList(list, str.substring(start, i), isTrim, ignoreEmpty);
                    start = i + 1;
                    if (limit > 0 && list.size() > limit - 2) {
                        break;
                    }
                }
            }

            return addToList(list, str.substring(start, len), isTrim, ignoreEmpty);
        }
    }

    public static String[] splitToArray(String str, char separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        return toArray(split(str, separator, limit, isTrim, ignoreEmpty));
    }

    public static List<String> split(String str, String separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        if (StringUtil.isEmpty(str)) {
            return new ArrayList(0);
        } else if (limit == 1) {
            return addToList(new ArrayList(1), str, isTrim, ignoreEmpty);
        } else if (StringUtil.isEmpty(separator)) {
            return split(str, limit);
        } else if (separator.length() == 1) {
            return split(str, separator.charAt(0), limit, isTrim, ignoreEmpty);
        } else {
            ArrayList<String> list = new ArrayList();
            int len = str.length();
            int separatorLen = separator.length();
            int start = 0;
            int i = 0;

            while(i < len) {
                i = str.indexOf(separator, start);
                if (i <= -1) {
                    break;
                }

                addToList(list, str.substring(start, i), isTrim, ignoreEmpty);
                start = i + separatorLen;
                if (limit > 0 && list.size() > limit - 2) {
                    break;
                }
            }

            return addToList(list, str.substring(start, len), isTrim, ignoreEmpty);
        }
    }

    public static String[] splitToArray(String str, String separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        return toArray(split(str, separator, limit, isTrim, ignoreEmpty));
    }

    public static List<String> split(String str, int limit) {
        if (StringUtil.isEmpty(str)) {
            return new ArrayList(0);
        } else if (limit == 1) {
            return addToList(new ArrayList(1), str, true, true);
        } else {
            ArrayList<String> list = new ArrayList();
            int len = str.length();
            int start = 0;

            for(int i = 0; i < len; ++i) {
                if (NumberUtil.isBlankChar(str.charAt(i))) {
                    addToList(list, str.substring(start, i), true, true);
                    start = i + 1;
                    if (limit > 0 && list.size() > limit - 2) {
                        break;
                    }
                }
            }

            return addToList(list, str.substring(start, len), true, true);
        }
    }

    public static String[] splitToArray(String str, int limit) {
        return toArray(split(str, limit));
    }

    public static List<String> splitByRegex(String str, String separatorRegex, int limit, boolean isTrim, boolean ignoreEmpty) {
        Pattern pattern = PatternPool.get(separatorRegex);
        return split(str, pattern, limit, isTrim, ignoreEmpty);
    }

    public static List<String> split(String str, Pattern separatorPattern, int limit, boolean isTrim, boolean ignoreEmpty) {
        if (StringUtil.isEmpty(str)) {
            return new ArrayList(0);
        } else if (limit == 1) {
            return addToList(new ArrayList(1), str, isTrim, ignoreEmpty);
        } else if (null == separatorPattern) {
            return split(str, limit);
        } else {
            Matcher matcher = separatorPattern.matcher(str);
            ArrayList<String> list = new ArrayList();
            int len = str.length();
            int start = 0;

            while(matcher.find()) {
                addToList(list, str.substring(start, matcher.start()), isTrim, ignoreEmpty);
                start = matcher.end();
                if (limit > 0 && list.size() > limit - 2) {
                    break;
                }
            }

            return addToList(list, str.substring(start, len), isTrim, ignoreEmpty);
        }
    }

    public static String[] splitToArray(String str, Pattern separatorPattern, int limit, boolean isTrim, boolean ignoreEmpty) {
        return toArray(split(str, separatorPattern, limit, isTrim, ignoreEmpty));
    }

    public static String[] splitByLength(String str, int len) {
        int partCount = str.length() / len;
        int lastPartCount = str.length() % len;
        int fixPart = 0;
        if (lastPartCount != 0) {
            fixPart = 1;
        }

        String[] strs = new String[partCount + fixPart];

        for(int i = 0; i < partCount + fixPart; ++i) {
            if (i == partCount + fixPart - 1 && lastPartCount != 0) {
                strs[i] = str.substring(i * len, i * len + lastPartCount);
            } else {
                strs[i] = str.substring(i * len, i * len + len);
            }
        }

        return strs;
    }

    private static List<String> addToList(List<String> list, String part, boolean isTrim, boolean ignoreEmpty) {
        if (isTrim) {
            part = part.trim();
        }

        if (!ignoreEmpty || !part.isEmpty()) {
            list.add(part);
        }

        return list;
    }

    private static String[] toArray(List<String> list) {
        return (String[])list.toArray(new String[list.size()]);
    }
}

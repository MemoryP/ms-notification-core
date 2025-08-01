package com.ocbc.ms.notification.cn.util;

import java.util.regex.Pattern;

public class PatternPool {
    public static final Pattern GENERAL = Pattern.compile("^\\w+$");
    public static final Pattern NUMBERS = Pattern.compile("\\d+");
    public static final Pattern CHINESE = Pattern.compile("[一-\u9fff]");
    public static final Pattern CHINESES = Pattern.compile("[一-\u9fff]+");
    public static final Pattern GROUP_VAR = Pattern.compile("\\$(\\d+)");
    public static final Pattern IPV4 = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
    public static final Pattern MONEY = Pattern.compile("^(\\d+(?:\\.\\d+)?)$");
    public static final Pattern EMAIL = Pattern.compile("(\\w|.)+@\\w+(\\.\\w+){1,2}");
    public static final Pattern MOBILE = Pattern.compile("1\\d{10}");
    public static final Pattern CITIZEN_ID = Pattern.compile("[1-9]\\d{5}[1-2]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}(\\d|X|x)");
    public static final Pattern ZIP_CODE = Pattern.compile("\\d{6}");
    public static final Pattern BIRTHDAY = Pattern.compile("^(\\d{2,4})([/\\-\\.年]?)(\\d{1,2})([/\\-\\.月]?)(\\d{1,2})日?$");
    public static final Pattern URL = Pattern.compile("(https://|http://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
    public static final Pattern GENERAL_WITH_CHINESE = Pattern.compile("^[一-\u9fff\\w]+$");
    public static final Pattern UUID = Pattern.compile("^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$");
    public static final Pattern UUID_SIMPLE = Pattern.compile("^[0-9a-z]{32}$");
    public static final Pattern PLATE_NUMBER = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$");
    public static final Pattern MAC_ADDRESS = Pattern.compile("((?:[A-F0-9]{1,2}[:-]){5}[A-F0-9]{1,2})|(?:0x)(\\d{12})(?:.+ETHER)", 2);
    private static final SimpleCache<PatternPool.RegexWithFlag, Pattern> POOL = new SimpleCache();

    private PatternPool() {
    }

    public static Pattern get(String regex) {
        return get(regex, 0);
    }

    public static Pattern get(String regex, int flags) {
        PatternPool.RegexWithFlag regexWithFlag = new PatternPool.RegexWithFlag(regex, flags);
        Pattern pattern = (Pattern)POOL.get(regexWithFlag);
        if (null == pattern) {
            pattern = Pattern.compile(regex, flags);
            POOL.put(regexWithFlag, pattern);
        }

        return pattern;
    }

    public static Pattern remove(String regex, int flags) {
        return (Pattern)POOL.remove(new PatternPool.RegexWithFlag(regex, flags));
    }

    public static void clear() {
        POOL.clear();
    }

    private static class RegexWithFlag {
        private String regex;
        private int flag;

        public RegexWithFlag(String regex, int flag) {
            this.regex = regex;
            this.flag = flag;
        }

    }
}

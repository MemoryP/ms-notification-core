package com.ocbc.ms.notification.cn.util;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.FastByteArrayOutputStream;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static String getMessage(Throwable e) {
        return StringUtil.format("{0}: {1}", new Object[]{e.getClass().getSimpleName(), e.getMessage()});
    }

    public static RuntimeException wrapRuntime(Throwable throwable) {
        return throwable instanceof RuntimeException ? (RuntimeException)throwable : new RuntimeException(throwable);
    }

    public static Throwable unwrap(Throwable wrapped) {
        Throwable unwrapped = wrapped;

        while(true) {
            while(!(unwrapped instanceof InvocationTargetException)) {
                if (!(unwrapped instanceof UndeclaredThrowableException)) {
                    return unwrapped;
                }

                unwrapped = ((UndeclaredThrowableException)unwrapped).getUndeclaredThrowable();
            }

            unwrapped = ((InvocationTargetException)unwrapped).getTargetException();
        }
    }

    public static StackTraceElement[] getStackElements() {
        return (new Throwable()).getStackTrace();
    }

    public static String stacktraceToOneLineString(Throwable throwable) {
        return stacktraceToOneLineString(throwable, 3000);
    }

    public static String stacktraceToOneLineString(Throwable throwable, int limit) {
        Map<Character, String> replaceCharToStrMap = new HashMap();
        replaceCharToStrMap.put('\r', " ");
        replaceCharToStrMap.put('\n', " ");
        replaceCharToStrMap.put('\t', " ");
        return stacktraceToString(throwable, limit, replaceCharToStrMap);
    }

    public static String stacktraceToString(Throwable throwable, int limit, Map<Character, String> replaceCharToStrMap) {
        FastByteArrayOutputStream baos = new FastByteArrayOutputStream();
        throwable.printStackTrace(new PrintStream(baos));
        String exceptionStr = baos.toString();
        int length = exceptionStr.length();
        if (limit > 0 && limit < length) {
            length = limit;
        }

        if (CollectionUtil.isNotEmpty(replaceCharToStrMap)) {
            StringBuilder sb = StringUtil.builder();

            for(int i = 0; i < length; ++i) {
                char c = exceptionStr.charAt(i);
                String value = (String)replaceCharToStrMap.get(c);
                if (null != value) {
                    sb.append(value);
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return exceptionStr;
        }
    }
}

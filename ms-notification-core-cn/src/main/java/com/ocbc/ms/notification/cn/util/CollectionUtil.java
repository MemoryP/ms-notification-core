package com.ocbc.ms.notification.cn.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CollectionUtil {
    private CollectionUtil() {
    }

    public static <T> Collection<T> union(Collection<T> coll1, Collection<T> coll2) {
        ArrayList<T> list = new ArrayList();
        if (isEmpty(coll1)) {
            list.addAll(coll2);
        } else if (isEmpty(coll2)) {
            list.addAll(coll1);
        } else {
            Map<T, Integer> map1 = countMap(coll1);
            Map<T, Integer> map2 = countMap(coll2);
            Set<T> elts = newHashSet(coll2);
            Iterator var6 = elts.iterator();

            while(var6.hasNext()) {
                T t = (T) var6.next();
                int i = 0;

                for(int m = Math.max(Integer.parseInt(String.valueOf(map1.get(t))), Integer.parseInt(String.valueOf(map2.get(t)))); i < m; ++i) {
                    list.add(t);
                }
            }
        }

        return list;
    }

    public static <T> Collection<T> intersection(Collection<T> coll1, Collection<T> coll2) {
        ArrayList<T> list = new ArrayList();
        if (isNotEmpty(coll1) && isNotEmpty(coll2)) {
            Map<T, Integer> map1 = countMap(coll1);
            Map<T, Integer> map2 = countMap(coll2);
            Set<T> elts = newHashSet(coll2);
            Iterator var6 = elts.iterator();

            while(var6.hasNext()) {
                T t = (T) var6.next();
                int i = 0;

                for(int m = Math.min(Integer.parseInt(String.valueOf(map1.get(t))), Integer.parseInt(String.valueOf(map2.get(t)))); i < m; ++i) {
                    list.add(t);
                }
            }
        }

        return list;
    }


    public static <T> Map<T, Integer> countMap(Collection<T> collection) {
        HashMap<T, Integer> countMap = new HashMap();
        Iterator var3 = collection.iterator();

        while(var3.hasNext()) {
            T t = (T) var3.next();
            Integer count = (Integer)countMap.get(t);
            if (null == count) {
                countMap.put(t, 1);
            } else {
                countMap.put(t, count + 1);
            }
        }

        return countMap;
    }

    public static <T> String join(Iterable<T> iterable, CharSequence conjunction) {
        return null == iterable ? null : join(iterable.iterator(), conjunction);
    }

    public static <T> String join(Iterator<T> iterator, CharSequence conjunction) {
        if (null == iterator) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;

            while(iterator.hasNext()) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                T item = iterator.next();
                if (ArrayUtil.isArray(item)) {
                    sb.append(ArrayUtil.join(ArrayUtil.wrap(item), conjunction));
                } else if (item instanceof Iterable) {
                    sb.append(join((Iterable)item, conjunction));
                } else if (item instanceof Iterator) {
                    sb.append(join((Iterator)item, conjunction));
                } else {
                    sb.append(item);
                }
            }

            return sb.toString();
        }
    }

    public static List<Entry<Long, Long>> sortEntrySetToList(Set<Entry<Long, Long>> set) {
        List<Entry<Long, Long>> list = new LinkedList(set);
        Collections.sort(list, (o1, o2) -> {
            if ((Long)o1.getValue() > (Long)o2.getValue()) {
                return 1;
            } else {
                return (Long)o1.getValue() < (Long)o2.getValue() ? -1 : 0;
            }
        });
        return list;
    }

    public static <T> List<T> popPart(Deque<T> surplusAlaDatas, int partSize) {
        if (isEmpty((Collection)surplusAlaDatas)) {
            return new ArrayList();
        } else {
            List<T> currentAlaDatas = new ArrayList();
            int size = surplusAlaDatas.size();
            int i;
            if (size > partSize) {
                for(i = 0; i < partSize; ++i) {
                    currentAlaDatas.add(surplusAlaDatas.pop());
                }
            } else {
                for(i = 0; i < size; ++i) {
                    currentAlaDatas.add(surplusAlaDatas.pop());
                }
            }

            return currentAlaDatas;
        }
    }

    public static <K, V> Map<K, V> newHashMap(int size, boolean isOrder) {
        int initialCapacity = (int)((double)size / 0.75D);
        return (Map)(isOrder ? new LinkedHashMap(initialCapacity) : new HashMap(initialCapacity));
    }

    @SafeVarargs
    public static <T> Set<T> newHashSet(boolean isSorted, T... ts) {
        if (null == ts) {
            return (Set)(isSorted ? new LinkedHashSet() : new HashSet());
        } else {
            int initialCapacity = Math.max((int)((float)ts.length / 0.75F) + 1, 16);
            HashSet<T> set = isSorted ? new LinkedHashSet(initialCapacity) : new HashSet(initialCapacity);
            ((HashSet)set).addAll(Arrays.asList(ts));
            return (Set)set;
        }
    }

    public static <T> Set<T> newHashSet(Collection<T> collection) {
        return newHashSet(false, collection);
    }

    public static <T> Set<T> newHashSet(boolean isSorted, Collection<T> collection) {
        return (Set)(isSorted ? new LinkedHashSet(collection) : new HashSet(collection));
    }

    public static <T> List<T> sub(List<T> list, int start, int end) {
        if (list != null && !list.isEmpty()) {
            if (start < 0) {
                start = 0;
            }

            if (end < 0) {
                end = 0;
            }

            int size;
            if (start > end) {
                size = start;
                start = end;
                end = size;
            }

            size = list.size();
            if (end > size) {
                if (start >= size) {
                    return new ArrayList();
                }

                end = size;
            }

            return list.subList(start, end);
        } else {
            return new ArrayList();
        }
    }

    public static <T> List<T> sub(Collection<T> list, int start, int end) {
        return (List)(list != null && !list.isEmpty() ? sub((List)(new ArrayList(list)), start, end) : new ArrayList());
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        return null == iterable || isEmpty(iterable.iterator());
    }

    public static boolean isEmpty(Iterator<?> iterator) {
        return null == iterator || !iterator.hasNext();
    }

    public static boolean isEmpty(Enumeration<?> enumeration) {
        return null == enumeration || !enumeration.hasMoreElements();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isNotEmpty(Iterable<?> iterable) {
        return null != iterable && isNotEmpty(iterable.iterator());
    }

    public static boolean isNotEmpty(Iterator<?> iterator) {
        return null != iterator && iterator.hasNext();
    }


    public static Map<String, String> zip(String keys, String values, String delimiter, boolean isOrder) {
        return ArrayUtil.zip(StringUtil.split(keys, delimiter), StringUtil.split(values, delimiter), isOrder);
    }

    public static Map<String, String> zip(String keys, String values, String delimiter) {
        return zip(keys, values, delimiter, false);
    }


    public static <T> Collection<T> addAll(Collection<T> collection, Iterator<T> iterator) {
        if (null != collection && null != iterator) {
            while(iterator.hasNext()) {
                collection.add(iterator.next());
            }
        }

        return collection;
    }


    public static <T> T getFirst(Iterator<T> iterator) {
        return null != iterator && iterator.hasNext() ? iterator.next() : null;
    }

    public static Class<?> getElementType(Iterable<?> iterable) {
        if (null != iterable) {
            Iterator<?> iterator = iterable.iterator();
            return getElementType(iterator);
        } else {
            return null;
        }
    }

    public static Class<?> getElementType(Iterator<?> iterator) {
        if (null != iterator) {
            while(iterator.hasNext()) {
                Object t = iterator.next();
                if (null != t) {
                    return t.getClass();
                }
            }
        }

        return null;
    }


    public interface KVConsumer<K, V> {
        void accept(K var1, V var2, int var3);
    }

    public interface Consumer<T> {
        void accept(T var1, int var2);
    }
}

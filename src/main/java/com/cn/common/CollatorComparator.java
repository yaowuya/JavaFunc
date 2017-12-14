package com.cn.common;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Comparator;

/**
 * Created by 钟锐锋 on 2017/12/14.
 * TreeMap中文排序
 *
 *   TreeMap map = new TreeMap();修改为
 *   CollatorComparator comparator = new CollatorComparator();
 *   TreeMap map = new TreeMap(comparator);
 *   反向排序就将return key1.compareTo(key2);
 *   修改成return -key1.compareTo(key2);，
 *   加了个负号，这里你可以直接加个符号看看效果
 */
public class CollatorComparator implements Comparator {
    Collator collator=Collator.getInstance();
    @Override
    public int compare(Object o1, Object o2) {
        CollationKey key1=collator.getCollationKey(o1.toString());
        CollationKey key2=collator.getCollationKey(o2.toString());
        return key1.compareTo(key2);
    }
}

package org.example.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HashTableTest {
    @Test
    public void testHash() {
        HashTable table = new HashTable();
        //先放入几个值
        table.put(0,"zhangsan","张三");
        table.put(1,"li","李四");
        table.put(16,"wang","王六");
        assertEquals(3,table.size);
        assertEquals("张三",table.table[0].value);
        assertEquals("王六",table.table[0].next.value);
        assertEquals("李四",table.table[1].value);
        table.put(0,"zhangsan","三三");
        table.remove(0,"zhangsan");
        assertNull(table.get(6,"sansan"));
        assertEquals("王六",table.table[0].value);
    }
}


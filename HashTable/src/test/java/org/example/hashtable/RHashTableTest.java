package org.example.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RHashTableTest {
    @Test
    public void testHash() {
        ResizeHashTable table = new ResizeHashTable();
        //先放入几个值
        table.put(0,"zhangsan","张三");
        table.put(1,"li","李四");
        table.put(17,"wang","王六");
//        assertEquals(3,table.size);
//        assertEquals("张三",table.table[0].value);
//        assertEquals("王六",table.table[8].value);
        assertEquals("张三", (table.get(0, "zhangsan")));  // 索引0
        assertEquals("李四", table.get(1, "li"));       // 索引1
        assertEquals("王六", table.get(17, "wang"));     // 索引9（不是8！）

//        assertEquals("王六",table.table[8].value);
//        assertEquals("李四",table.table[1].value);
//        table.put(0,"zhangsan","三三");
//        table.remove(0,"zhangsan");
        assertNull(table.get(9,"sansan"));
//        assertEquals("王六",table.table[0].value);
    }
}

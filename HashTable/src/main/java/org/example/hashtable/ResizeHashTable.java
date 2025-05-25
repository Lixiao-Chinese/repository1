package org.example.hashtable;

public class ResizeHashTable {

    //节点类
    static class REntry{
        int hash;//哈希值
        Object key;//键
        Object value;//值
        REntry next;//用于同一个索引下的链表指针

        //构造方法
        public REntry(int hash,Object key,Object value){
            this.hash=hash;
            this.key=key;
            this.value=value;
        }
    }

    //创建一个节点类型的数组
    REntry[] table=new REntry[8];
    //这里的数组长度被要求是2的n次方，他是模运算代替位运算的前提,我们通过模运算(我们用二进制码会发现规律，用位运算取代模运算位运算)找到哈希值跟key之间的关系

    int size=0;//元素个数
    float loadFactor=0.75f;
    int threshold=(int) (loadFactor*(table.length-1));

    //通过hash,key获取value
    Object get(int hash, Object key){
        int index=hash & (table.length-1);//先通过hash码找到对应的索引index
        //判断数组中当前位置是否为空
        if(table[index]==null) {
            //为空，返回空
            return null;
        }
        else {
            //不为空，找到链表的首节点
          REntry p=table[index];
            //遍历链表直到找到，返回value;
            while(p!=null){
                if(p.key.equals(key)){
                    return p.value;
                }
                p=p.next;
            }
            //如果没有对应的值，返回空
            return null;
        }
    }
    //向哈希表中添加一个元素
    void put(int hash,Object key,Object value){
        //先找到对应索引
        int index= hash & (table.length-1);
        //判断改索引是否为空
        //为空的话，创建一个节点
        //不为空的话，将先找到对应节点，然后判断是否有相同key，有的话相当于更新，没有的话，新添
        if(table[index]==null){
            //这里需要创建节点吗，直接放进去就可以了吧
           REntry p=new REntry(hash,key,value);
            table[index]=p;
        }
        else{

            REntry p=table[index];
            while(true) {
                if (p.key.equals(key)) {
                    p.value = value;//更新
                    return;
                }
                if(p.next==null){
                    break;

                }
                p=p.next;

            }
            p.next=new REntry(hash,key,value);
//            p.next.next=null;
        }
        size++;
        if(size>threshold){
            resize();
        }
    }
    private void resize(){
//        先扩容
        REntry[] newTable =new REntry[table.length << 1];
//        循环将旧数组放入新数组中
        for(int i=0;i<table.length;i++) {
//            找到链头
            REntry p = table[i];
            if(p!=null) {
                //拆分链表头,分别是两个链表
                REntry a=null;
                REntry b=null;
                //两个链表的头指针
                REntry aHead=null;
                REntry bHead=null;
                while(p!=null) {
                    //哈希值与旧数组的取模为0，那么就放入a链表中
                    if ((p.hash & table.length) == 0) {
                        if (a == null) {
                            a = p;
                            aHead = p;
                        }
                        else {
                            newTable[i] = aHead;
                            a.next = p;
                        }
                    }
                    else {
                        if (b == null) {
                            bHead = p;
                            b = p;
                        }
                        else {
                            newTable[i + table.length] = bHead;
                            b.next = p;
                        }
                    }
                    p = p.next;
                }
                if(a!=null){
                    a.next=null;
                }
                if(b!=null){
                    b.next=null;
                }
            }
        }
        table=newTable;
        threshold=(int)(table.length*loadFactor);
    }
    //通过哈希码以及Key,删除元素，并返回value
    Object remove(int hash,Object key){
        int index=hash & (table.length-1);
        REntry prev=null;//前指针
        if(table[index]==null){
            return null;
        }
        else{
            REntry p=table[index];
            while(p!=null){
                if(p.key.equals(key)){
                    //删除节点，要先找到他的前节点

                    //如果是头指针
                    if(prev==null){
                        table[index]=p.next;
                    }
                    else{
                        prev.next=p.next;
                    }
                    return p.value;
                }
                prev=p.next;
                p=p.next;

            }
            size--;
        }
        return null;
    }
}

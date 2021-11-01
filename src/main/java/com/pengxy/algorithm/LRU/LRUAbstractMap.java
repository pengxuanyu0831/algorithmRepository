package com.pengxy.algorithm.LRU;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;


/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/11/01 09:26
 */


public class LRUAbstractMap extends java.util.AbstractMap{
    private final static Logger LOGGER = LoggerFactory.getLogger(LRUAbstractMap.class);
    /**
     * 检查是否超期
     */
    private ExecutorService executorService;

    /**
     * Map最大size
     */
    private final static Integer MAX_SIZE = 1024;

    private final static ArrayBlockingQueue<Node> QUEUE = new ArrayBlockingQueue<>();

    private class Node{
        private Node next;
        private Node pre;
        private Object key;
        private Object value;
        private Long updateTime;

        public Node(Node pre, Node next, Object key, Object value) {
            this.pre = pre;
            this.key = key;
            this.next = next;
            this.value = value;
            this.updateTime = System.currentTimeMillis();
        }
    }
}

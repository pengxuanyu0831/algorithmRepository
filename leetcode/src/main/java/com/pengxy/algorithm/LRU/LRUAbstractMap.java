/*
package com.pengxy.algorithm.LRU;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;


import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


*/
/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/11/01 09:26
 *//*



public class LRUAbstractMap extends java.util.AbstractMap{
    private final static Logger LOGGER = LoggerFactory.getLogger(LRUAbstractMap.class);
    */
/**
     * 检查是否超期
     *//*

    private ExecutorService executorService;

    */
/**
     * Map最大size
     *//*

    private final static Integer MAX_SIZE = 1024;

    private final static ArrayBlockingQueue<Node> QUEUE = new ArrayBlockingQueue<>();

    private final static int DEFAULT_ARRAY_SIZE = 1024;

    private int arraySize;

    private Object[] arrays;

    private volatile boolean flag = false;

    private final static Long EXPIRE_TIME = 60 * 60 * 1000L;

    private volatile AtomicInteger size;

    public LRUAbstractMap() {
        arraySize = DEFAULT_ARRAY_SIZE;
        arrays = new Object[arraySize];


    }

    private void execCheckTime() {
        ThreadFactory factory = new CustomizableThreadFactory("DameThread-pool-");

        executorService = new ThreadPoolExecutor(1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),
                factory,
                new ThreadPoolExecutor.AbortPolicy());
        executorService.execute(new CheckTimeThread());
    }

    private class CheckTimeThread implements Runnable {
        @Override
        public void run() {
            while (flag) {
                try {
                    Node node = QUEUE.poll();
                    if (node == null) {
                        continue;
                    }
                    Long updateTime = node.updateTime;
                    if ((updateTime - System.currentTimeMillis()) >= EXPIRE_TIME) {
                        remove(node.key);
                    }
                } catch (Exception e) {
                    LOGGER.error("INNNNNNNn");
                }
            }
        }
    }




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
*/

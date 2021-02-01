package com.pengxy.algorithm.Tree;

/**
 * @program algorithm
 * @description: 双亲表示法表示一棵树
 * @author: pengxuanyu
 * @create: 2021/02/01 21:56
 */
public class ParentTree<E> {
    private static final int DEFAULT_CAPACITY = 100;
    private int size;
    private Node[] nodes;

    private class Node(){
        // 节点数据
        E data;
        // 节点的父节点
        int parent;

        Node(E data,int parent){
            this.data = data;
            this.parent = parent;
        }

    }

    public ParentTree(){
        nodes = new ParentTree.Node[DEFAULT_CAPACITY];
    }
}

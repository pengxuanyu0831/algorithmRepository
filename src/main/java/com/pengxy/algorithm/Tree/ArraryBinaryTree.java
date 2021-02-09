package com.pengxy.algorithm.Tree;

import java.util.ArrayList;

/**
 * @program algorithm
 * @description: 二叉树的实现
 * @author: pengxuanyu
 * @create: 2021/02/09 21:31
 */
public class ArraryBinaryTree<E> {
    // 默认深度
    private static final int DEFAULT_DEPTH = 5;
    // 数据区
    private E[] datas;
    private int size = 0;

    ArraryBinaryTree(){
        this(DEFAULT_DEPTH);
    }
    @SuppressWarnings("unchecked")
    ArraryBinaryTree(int depth){
        // 两次的强制类型转换
        datas = (E[]) new Object[(int) Math.pow(2,depth)];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public E getRoots(){
        // 二叉树不用0，从1开始
        return datas[1];
    }
    // 判断下标存不存在，存在就接着走
    private void checkIndex(int index){
        if(index <= 0 || index >= datas.length){
            throw  new IndexOutOfBoundsException();
        }
    }

    public E getParent(int index){
        checkIndex(index);
        if(index  == 1){
            throw new RuntimeException("根节点不存在父节点");
        }
        return datas[2/index];
    }
    // 左子节点
    public E getLeft(int index){
        checkIndex(2 * index);
        return datas[2 * index];
    }
    // 右子节点
    public E getRight(int index){
        checkIndex(2 * index + 1);
        return datas[2 * index + 1];
    }

    public int getIndex(E data){
        if(data == null){
            throw new NullPointerException();
        }else {
            for(int i=0 ;i < datas.length;i++){
                if(data.equals(datas[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    public void add(E element){
        checkIndex(size + 1 );
        datas[size + 1 ] = element;
        size++;
    }











}

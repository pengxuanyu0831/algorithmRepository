

/**
 * @program algorithm
 * @description:
 * @author: pengxuanyu
 * @create: 2021/02/10 13:53
 */
public class BubbleSort {
    public static void bubbleSort(int[] datas){
        int temp = 0;
        int size = datas.length;
        for(int i = 0;i < size - 1 ;i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (datas[j] > datas[j + 1]) {
                    temp = datas[j];
                    datas[j] = datas[j + 1];
                    datas[j + 1] = temp;
                }
            }
        }
    }

    public static void printResult(int[] datas){
        for(int i = 0;i < datas.length;i++){
            System.out.print(datas[i] + ",");
        }
    }

    public static void main(String[] args) {
        int[] datas = {1,4,3,55,23,555,-33,33};
        System.out.print("待排序数组为" + datas);

        long currentTime = System.currentTimeMillis();
        bubbleSort(datas);
        long spentTime = System.currentTimeMillis() - currentTime;
        System.out.print("耗费时间:" + spentTime);
        System.out.print("冒泡排序后的数组为:" );
        printResult(datas);
    }

}

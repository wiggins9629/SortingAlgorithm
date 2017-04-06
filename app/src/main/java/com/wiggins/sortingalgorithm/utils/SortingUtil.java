package com.wiggins.sortingalgorithm.utils;

import com.wiggins.sortingalgorithm.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 排序工具类
 * @Author 一花一世界
 */

public class SortingUtil {

    public static final int TYPE_1 = 1;//原始数据
    public static final int TYPE_2 = 2;//排序数据
    public static StringBuilder stringBuilder;

    /**
     * @Description 直接插入排序
     */
    public static String insertSort() {
        int array[] = {49, 38, 65, 97, 76, 13, 27, 54, 56, 17, 18, 23};

        getSortData(false, TYPE_1, array);//获取原始数据

        int temp = 0;
        //第0位独自作为有序数列，从第1位开始向后遍历
        for (int i = 1; i < array.length; i++) {
            //[0,i-1]都是有序的，如果待插入元素比arr[i-1]还大则无需再与[i-1]前面的元素进行比较了，反之则进入if语句
            if (array[i] < array[i - 1]) {
                int j = i - 1;
                //保存第i位的值
                temp = array[i];
                //从第i-1位向前遍历并移位，直至找到小于第i位值停止
                for (; j >= 0 && temp < array[j]; j--) {
                    //把比temp大或相等的元素全部往后移动一个位置
                    array[j + 1] = array[j];
                }
                //把待排序的元素temp插入腾出位置的(j+1)
                array[j + 1] = temp;
            }
        }

        return UIUtils.getString(R.string.direct_insertion_sort) + ":\n" + getSortData(true, TYPE_2, array);
    }

    /**
     * @Description 希尔排序（最小增量排序）
     */
    public static String shellSort() {
        int array[] = {4, 54, 6, 39, 25, 34, 12, 45, 56, 10};

        getSortData(false, TYPE_1, array);//获取原始数据

        double d1 = array.length;
        int temp = 0;
        while (true) {
            d1 = Math.ceil(d1 / 2);
            int d = (int) d1;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < array.length; i += d) {
                    int j = i - d;
                    temp = array[i];
                    for (; j >= 0 && temp < array[j]; j -= d) {
                        array[j + d] = array[j];
                    }
                    array[j + d] = temp;
                }
            }
            if (d == 1)
                break;
        }

        return UIUtils.getString(R.string.hill_sort) + ":\n" + getSortData(true, TYPE_2, array);
    }

    /**
     * @Description 简单选择排序
     */
    public static String selectSort() {
        int array[] = {1, 54, 46, 73, 78, 34, 12, 45};

        getSortData(false, TYPE_1, array);//获取原始数据

        int position = 0;
        for (int i = 0; i < array.length; i++) {
            int j = i + 1;
            position = i;
            int temp = array[i];
            for (; j < array.length; j++) {
                if (array[j] < temp) {
                    temp = array[j];
                    position = j;
                }
            }
            array[position] = array[i];
            array[i] = temp;
        }

        return UIUtils.getString(R.string.simple_sorting) + ":\n" + getSortData(true, TYPE_2, array);
    }

    /**
     * @Description 堆排序
     */
    public static String heapSort() {
        int array[] = {49, 38, 65, 97, 76, 13, 27, 78, 34, 12, 64, 25, 53, 51};

        getSortData(false, TYPE_1, array);//获取原始数据

        int arrayLength = array.length;
        //循环建堆  
        for (int i = 0; i < arrayLength - 1; i++) {
            //建堆（对data数组从0到lastIndex建大顶堆）
            buildMaxHeap(array, arrayLength - 1 - i);
            //交换堆顶和最后一个元素  
            swap(array, 0, arrayLength - 1 - i);
            LogUtil.e(Constant.LOG_TAG, UIUtils.getString(R.string.heap_sort) + " A:" + Arrays.toString(array));
        }

        return UIUtils.getString(R.string.heap_sort) + ":\n" + getSortData(true, TYPE_2, array);
    }

    public static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k保存正在判断的节点  
            int k = i;
            //如果当前k节点的子节点存在  
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引  
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在  
                if (biggerIndex < lastIndex) {
                    //若果右子节点的值较大  
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        //biggerIndex总是记录较大子节点的索引  
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值  
                if (data[k] < data[biggerIndex]) {
                    //交换他们  
                    swap(data, k, biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值  
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * @Description 冒泡排序
     */
    public static String bubbleSort() {
        int array[] = {49, 38, 65, 97, 76, 13, 12, 64, 34, 15, 35, 25, 53, 51};

        getSortData(false, TYPE_1, array);//获取原始数据

        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return UIUtils.getString(R.string.bubble_sort) + ":\n" + getSortData(true, TYPE_2, array);
    }

    /**
     * @Description 快速排序
     */
    public static String quickSort() {
        int array[] = {69, 38, 76, 13, 27, 49, 78, 15, 35, 25, 53, 51};

        getSortData(false, TYPE_1, array);//获取原始数据

        quick(array);

        return UIUtils.getString(R.string.quick_sort) + ":\n" + getSortData(true, TYPE_2, array);
    }

    public static int getMiddle(int[] list, int low, int high) {
        int tmp = list[low];    //数组的第一个作为中轴
        while (low < high) {
            while (low < high && list[high] >= tmp) {
                high--;
            }
            list[low] = list[high];   //比中轴小的记录移到低端
            while (low < high && list[low] <= tmp) {
                low++;
            }
            list[high] = list[low];   //比中轴大的记录移到高端
        }
        list[low] = tmp;              //中轴记录到尾
        return low;                   //返回中轴的位置
    }

    public static void _quickSort(int[] list, int low, int high) {
        if (low < high) {
            int middle = getMiddle(list, low, high);  //将list数组进行一分为二
            _quickSort(list, low, middle - 1);        //对低字表进行递归排序
            _quickSort(list, middle + 1, high);       //对高字表进行递归排序
        }
    }

    public static void quick(int[] a2) {
        //查看数组是否为空
        if (a2.length > 0) {
            _quickSort(a2, 0, a2.length - 1);
        }
    }

    /**
     * @Description 归并排序
     */
    public static String mergingSort() {
        int array[] = {49, 34, 12, 64, 62, 98, 54, 56, 17, 18, 23, 34, 15, 51};

        getSortData(false, TYPE_1, array);//获取原始数据

        sort(array, 0, array.length - 1);

        return UIUtils.getString(R.string.merge_sort) + ":\n" + getSortData(true, TYPE_2, array);
    }

    public static void sort(int[] data, int left, int right) {
        if (left < right) {
            //找出中间索引
            int center = (left + right) / 2;
            //对左边数组进行递归
            sort(data, left, center);
            //对右边数组进行递归
            sort(data, center + 1, right);
            //合并
            merge(data, left, center, right);
        }
    }

    public static void merge(int[] data, int left, int center, int right) {
        int[] tmpArr = new int[data.length];
        int mid = center + 1;
        //third记录中间数组的索引
        int third = left;
        int tmp = left;
        while (left <= center && mid <= right) {
            //从两个数组中取出最小的放入中间数组
            if (data[left] <= data[mid]) {
                tmpArr[third++] = data[left++];
            } else {
                tmpArr[third++] = data[mid++];
            }
        }
        //剩余部分依次放入中间数组
        while (mid <= right) {
            tmpArr[third++] = data[mid++];
        }
        while (left <= center) {
            tmpArr[third++] = data[left++];
        }
        //将中间数组中的内容复制回原数组
        while (tmp <= right) {
            data[tmp] = tmpArr[tmp++];
        }
        LogUtil.e(Constant.LOG_TAG, UIUtils.getString(R.string.merge_sort) + " A:" + Arrays.toString(data));
    }

    /**
     * @Description 基数排序
     */
    public static String radixSort() {
        int array[] = {65, 13, 27, 49, 78, 12, 64, 62, 99, 54, 17, 18, 23, 34};

        getSortData(false, TYPE_1, array);//获取原始数据

        //首先确定排序的趟数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int time = 0;
        //判断位数
        while (max > 0) {
            max /= 10;
            time++;
        }
        //建立10个队列
        List<ArrayList> queue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> queue1 = new ArrayList<>();
            queue.add(queue1);
        }
        //进行time次分配和收集
        for (int i = 0; i < time; i++) {
            //分配数组元素
            for (int j = 0; j < array.length; j++) {
                //得到数字的第time+1位数
                int x = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(array[j]);
                queue.set(x, queue2);
            }
            //元素计数器
            int count = 0;
            //收集队列元素
            for (int k = 0; k < 10; k++) {
                while (queue.get(k).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(k);
                    array[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }

        return UIUtils.getString(R.string.base_sort) + ":\n" + getSortData(true, TYPE_2, array);
    }

    /**
     * @Description 排序数据
     */
    public static String getSortData(boolean isEmpty, int type, int array[]) {
        if (isEmpty == false) {
            stringBuilder = new StringBuilder();
        }
        if (type == TYPE_1) {
            for (int i = 0; i < array.length; i++) {
                if (i == 0) {
                    stringBuilder.append("原始数据: [ " + array[i]);
                } else if (i == array.length - 1) {
                    stringBuilder.append("," + array[i] + " ]\n");
                } else {
                    stringBuilder.append("," + array[i]);
                }
            }
        } else if (type == TYPE_2) {
            for (int i = 0; i < array.length; i++) {
                if (i == 0) {
                    stringBuilder.append("排序数据: [ " + array[i]);
                } else if (i == array.length - 1) {
                    stringBuilder.append("," + array[i] + " ]");
                } else {
                    stringBuilder.append("," + array[i]);
                }
            }
        }
        return stringBuilder.toString();
    }
}

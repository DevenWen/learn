package com.qpm.learn.algorithms.sort;

/**
 * 二分查找
 *
 * @author kangqiang.w
 * @Date 2020-05-15
 **/
public class BinarySearch {


    /**
     *
     *
     * @param key
     * @param a
     * @return -1 找不到指定对象
     */
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if       (key < a[mid]) hi = mid - 1;
            else if  (key > a[mid]) lo = mid + 1;
            else return mid;


        }
        return -1;

//        写得不优雅
//        if (a == null || a.length == 0) {
//            return -1;
//        }
//
//        int begin = 0;
//        int end = a.length - 1;
//
//        while (end >= begin) {
//            int mid = (end + begin) / 2;
////            int  mid = begin + (end - begin) / 2;
//            if (a[mid] == key) {
//                return mid;
//            }
//            if (a[mid] > key) {
//                end = mid - 1;
//                continue;
//            }
//            if (a[mid] < key) {
//                begin = mid + 1;
//                continue;
//            }
//        }
//
//        return -1;
    }


}

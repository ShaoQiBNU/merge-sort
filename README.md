归并排序
=======

# 一. 基本思想

> 归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略（分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。具体流程如图所示：

![image](https://github.com/ShaoQiBNU/merge-sort/blob/master/images/1.gif)

# 二. 基本思想

> 首先采用分治策略，将数组划分，划分后的数组进行排序，然后在进行合并，从而使数组有序，具体如图1和2所示：
![image](https://github.com/ShaoQiBNU/merge-sort/blob/master/images/1.png)
![image](https://github.com/ShaoQiBNU/merge-sort/blob/master/images/2.png)

> 代码如下：

```JAVA
import java.util.Arrays;

public class MergeSort {
    
    public static void main(String []args){
        int []arr = {9,8,7,6,5,4,3,2,1};
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        
        sort(arr,0,arr.length-1,temp);
        
        System.out.println(Arrays.toString(arr));
    }
    
    private static void sort(int[] arr,int left,int right,int []temp){
        if(left<right){
            
            int mid = (left+right)/2;
            
            sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
            
            sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
            
            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
        }
    }
    
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }
}
```




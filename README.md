归并排序
=======

# 一. 基本思想

> 归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略（分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。具体流程如图所示：

![image](https://github.com/ShaoQiBNU/merge-sort/blob/master/images/1.gif)

# 二. 具体过程

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

# 三. 应用

> 有一组数，对于其中任意两个数组，若前面一个大于后面一个数字，则这两个数字组成一个逆序对。请设计一个高效的算法，计算给定数组中的逆序对个数。给定一个int数组A和它的大小n，请返回A中的逆序对个数。保证n小于等于5000。测试样例：[1,2,3,4,5,6,7,0],8，返回：7。

> 采用归并排序思想，先把数组分隔成子数组，统计出子数组内部的逆序对的数目，然后再统计出两个相邻子数组之间的逆序对的数目。在统计逆序对的过程中，还需要对数组进行排序，避免在之后的统计过程中重复统计。逆序对的总数=左边数组中的逆序对的数量+右边数组中逆序对的数量+左右结合成新的顺序数组时中出现的逆序对的数量。 

> 以{7,5,6,4}为例，具体过程如下：将数组分解成两个长度为2的子数组，再把这两个子数组分解成两个长度为1的子数组。接下来一边合并相邻的子数组，一边统计逆序对的数目。在第一对长度为1的子数组{7}、{5}中7>5，因此（7,5）组成一个逆序对。同样在第二对长度为1的子数组{6}、{4}中也有逆序对（6,4），由于已经统计了这两对子数组内部的逆序对，因此需要把这两对子数组进行排序，避免在之后的统计过程中重复统计。然后对(5,7)和(4,6)两个子数组进行合并，并统计逆序对数。先用两个指针分别指向两个子数组的首位，并每次比较两个指针指向的数字。如果第一个子数组中的数字大于第二个子数组中的数字，则构成逆序对，并且逆序对的数目等于第一个子数组中的剩余数字的个数。如果第一个数组中的数字小于或等于第二个数组中的数字，则不构成逆序对。每一次比较的时候，都把较小的数字从前往后复制到一个辅助数组中去，确保辅助数组中的数字是递增排序的。在把较小的数字复制到数组之后，把对应的指针向后移动一位，接着来进行下一轮的比较。对(5,7)和(4,6)统计，由于5>4，此时逆序数有2个，然后对(5,7)和(6)统计，5<6，没有逆序数，然后对(7)和(6)统计，7>6，逆序数有1个，到此为止，逆序数总共有1+1+2+1=5。代码如下：


```java
import java.util.Arrays;

public class MergeSort {
    public static int count=0;
    public static void main(String []args){
        int []arr = {9,8,7,6,5,4,3,2,1};
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr,0,arr.length-1,temp);
        
        System.out.println(count);
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
            }
            
            // 左数组比右数组大
            else {
                  
                // 数组此时是有序数组,如果a[i]此时比右数组的当前元素a[j]大，那么左数组中a[i]后面的元素就都比a[j]大  
                
                count=count+mid-i+1;
                
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


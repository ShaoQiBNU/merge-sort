import java.util.Arrays;

public class MergeSort {
    public static int count=0;
    public static void main(String []args){
        int []arr = {1,3,5,2,4,6};
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
            
            if(arr[i]>arr[j]){
                
                temp[t++] = arr[j++];
            }
            
            // 左数组比右数组小
            else {
                  
                // 数组此时是有序数组,如果a[i]此时比右数组的当前元素a[j]小，那么右数组中a[j]后面的元素就都比a[i]大  
                
                count=count+(right-j+1)*arr[i];
                
                temp[t++] = arr[i++];
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

package leetcode

object TwoPointers extends App{
    val arr = Array(1,1,1,2,2,2,3,3,4, 4)
    val elems = arr.toList
    println(LeetCode26.instructonal(arr));   
    println(LeetCode26.functional(elems));   

    val arr2 = Array(0,1,2,2,3,0,4,2)
    println(LeetCode27.instruction(arr2, 2))


    println(LeetCode1480.instruction(Array(1,2,3,4)).mkString(","))
}

/**
 * LeetCode 26 — Remove Duplicates from Sorted Array
 *
 * Given an integer array nums sorted in non-decreasing order,
 * remove the duplicates in-place such that each unique element appears only once.
 * The relative order of the elements should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages,
 * you must instead have the result be placed in the first part of the array nums.
 * More formally, if there are k elements after removing the duplicates,
 * then the first k elements of nums should hold the final result.
 * It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example 1:
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 *
 * Example 2:
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 *
 * Constraints:
 *  - 1 <= nums.length <= 3 * 10^4
 *  - -100 <= nums[i] <= 100
 *  - nums is sorted in non-decreasing order.
 */
object LeetCode26{
    def functional[T](elems:List[T]): Int = {
        val res = elems.foldLeft(List.empty[T])((acc, e) => {
            if(acc.isEmpty || acc.head != e){
                e :: acc
            }else{
                acc
            }
        })
        /*
         * @note
         * We must remember the order of elements in res is reversed
         */
        res.length
    }

    def instructonal[T](arr: Array[T]) : Int = {
        if(arr.isEmpty){
            return 0
        }


        var slow = 0
        var fast = slow + 1
        while(fast < arr.length){
            if(arr(slow) != arr(fast)){
                arr.update(slow + 1, arr(fast))
                slow += 1
            }
            fast += 1
            
        }
        slow + 1
    }
}
/*  
* Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.  
* The order of the elements may be changed.  
* Then return the number of elements in nums which are not equal to val.
* 
* Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:
*   • Change the array nums such that the first k elements of nums contain the elements which are not equal to val.  
*   • The remaining elements of nums are not important as well as the size of nums.
*   • Return k.
* 
* Example 1:
* Input: nums = [3,2,2,3], val = 3  
* Output: 2, nums = [2,2,_,_]  
* 
* Example 2:
* Input: nums = [0,1,2,2,3,0,4,2], val = 2  
* Output: 5, nums = [0,1,4,0,3,_,_,_]  
* 
* Constraints:
*   0 ≤ nums.length ≤ 100  
*   0 ≤ nums[i] ≤ 50  
*   0 ≤ val ≤ 100
*/
object LeetCode27{

    def fuctional[T](list:List[T], v:T): Int = {
        /**
          * 这个collect是要求一个 partialfunction的
          */
        list.collect{
            case x if x != v => x
        }.size
        /**
          * 直接filter也很好
          */
        list.filter(_ == v).size

        /* 空间都省了，直接count简单粗暴有效 */
        list.count( _ != v)
    }


    def instruction[T](arr:Array[T], v:T): Int = {


        var slow = 0
        var fast = 0

        while(fast < arr.length){
            if(arr(fast) != v){
                arr(slow) = arr(fast)
                slow += 1
            }
            fast += 1
        }


        slow
    }
}

/**
 * LeetCode 1480 — Running Sum of 1d Array
 *
 * Given an array nums, we define a running sum of an array as
 * runningSum[i] = sum(nums[0] ... nums[i]).
 *
 * Return the running sum of nums.
 *
 * Example 1:
 *   Input:  nums = [1,2,3,4]
 *   Output: [1,3,6,10]
 *
 * Example 2:
 *   Input:  nums = [1,1,1,1,1]
 *   Output: [1,2,3,4,5]
 *
 * Example 3:
 *   Input:  nums = [3,1,2,10,1]
 *   Output: [3,4,6,16,17]
 *
 * Constraints:
 *   - 1 <= nums.length <= 1000
 *   - -10^6 <= nums[i] <= 10^6
 */
object LeetCode1480{


    /**
      * 这里的scanLeft以0为初始值
      * 每个元素都和前一个结果做计算
      * 
      * 区别就是第二个括号里的内会作为一个元素被放到结果数组里
      * 这样结果数据就有n+1个元素
      * 
      * 而tail，就是取最后一个元素之外的所有元素
      * 
      * 其实就是说 foldLeft是每次迭代都只返回一个acc，最终拿到的也是一个acc
      * 但是这个scanLeft是每次迭代返回一个acc，然后把这个acc放到一个数组里了
      * 
      * 这样方法结束后，就可以得到一组acc（个数比数据中的元素多一个）
      *
      * @param arr
      * @return
      */
    def functional(arr:Array[Int]):Array[Int] = {
        arr.scanLeft(0)(_+_).tail
    }


    def instruction(arr:Array[Int]):Array[Int] = {
        val result = arr.clone()

        var index = 1

        while(index < arr.length){
            result(index) = result(index - 1) + arr(index)
            index+=1
        }

        result
    }
}
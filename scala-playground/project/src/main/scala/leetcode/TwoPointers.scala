package leetcode

object TwoPointers extends App{
    val arr = Array(1,1,1,2,2,2,3,3,4, 4)
    val elems = arr.toList
    println(LeetCode26.instructonal(arr));   
    println(LeetCode26.functional(elems));   
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

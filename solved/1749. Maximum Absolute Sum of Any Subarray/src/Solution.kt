class Solution {
    fun maxAbsoluteSum(nums: IntArray): Int {
        var max = 0
        var min = 0
        var acc = 0
        nums.forEach {
            acc += it
            if (acc > max) {
                max = acc
            } else if (acc < min) {
                min = acc
            }
        }

        return max - min
    }
}



fun main(args: Array<String>) {
    val nums1 = intArrayOf(1, -3, 2, 3, -4)
    val nums2 = intArrayOf(2, -5, 1, -4, 3, -2)
    val nums3 = intArrayOf(-3,-5,-3,-2,-6,3,10,-10,-8,-3,0,10,3,-5,8,7,-9,-9,5,-8)

    val test = nums1

    println(test.toList())
    val res = Solution().maxAbsoluteSum(test)

    println(res)
}
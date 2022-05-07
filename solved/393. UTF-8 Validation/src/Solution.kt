class Solution {
    fun validUtf8(data: IntArray): Boolean {
        val dataList = data.toList()
        var i = 0
        while (i < dataList.size) {
            val isValid = dataList.subList(i, dataList.size).isValid()
            if (isValid == -1) return false
            i += isValid
        }
        return true
    }


    private fun List<Int>.isValid(): Int {
        // 0xxxxxxx
        if (size == 0) return -1
        val item0 = get(0)
        if (item0 shr 7 == 0b0) return 1

        // 110xxxxx 10xxxxxx
        if (size < 2) return -1
        val item1Res = get(1) shr 6 == 0b10
        if (item0 shr 5 == 0b110 && item1Res) return 2

        // 1110xxxx 10xxxxxx 10xxxxxx
        if (size < 3) return -1
        val item2Res = get(2) shr 6 == 0b10
        if (item0 shr 4 == 0b1110 && item1Res && item2Res) return 3

        // 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
        if (size < 4) return -1
        val item3Res = get(3) shr 6 == 0b10
        if (item0 shr 3 == 0b11110 && item1Res && item2Res && item3Res) return 4

        return -1
    }
}


fun main(args: Array<String>) {
    val test1 = intArrayOf(197,130,1)
    val test2 = intArrayOf(235,140,4)

    val test = test1

    println(test.toList())
    val res = Solution().validUtf8(test)
    println(res)
}
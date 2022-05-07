import java.util.*

class Solution {
    fun btreeGameWinningMove(root: TreeNode?, n: Int, x: Int): Boolean {
        if (root == null) return false

        val player1Node = findNode(root, x)!!
        val path1 = player1Node.left?.let { countPath(it) } ?: 0
        val path2 = player1Node.right?.let { countPath(it) } ?: 0
        val path3 = n - path1 - path2 - 1

        val half = n / 2
        return path1 > half ||
                path2 > half ||
                path3 > half
    }

    private fun countPath(node: TreeNode?): Int {
        if (node == null) return 0
        return countPath(node.left) + countPath(node.right) + 1
    }

    private fun findNode(node: TreeNode, value: Int): TreeNode? {
        if (node.`val` == value) return node
        return node.left?.let { findNode(it, value) }
            ?: node.right?.let { findNode(it, value) }
    }
}


/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

private data class InputData(
    val root: List<Int>,
    val n: Int,
    val x: Int
)

fun buildTree(values: List<Int>): TreeNode {
    val root = TreeNode(values.first())
    val queue: Queue<TreeNode> = LinkedList<TreeNode>()
    var currentNode = root
    (1..values.lastIndex).forEach { i ->
        val nodeValue = values[i]

        if (currentNode.left == null) {
            currentNode.left = TreeNode(nodeValue).also { queue.add(it) }
        } else if (currentNode.right == null) {
            currentNode.right = TreeNode(nodeValue).also { queue.add(it) }
        } else {
            currentNode = queue.poll()
            currentNode.left = TreeNode(nodeValue).also { queue.add(it) }
        }
    }

    return root
}

fun main(args: Array<String>) {
    val test1 = InputData(
        root = listOf(1,2,3,4,5,6,7,8,9,10,11),
        n = 11,
        x = 3
    )
    val test2 = InputData(
        root = listOf(1,2,3),
        n = 3,
        x = 1
    )

    val test = test2

    println(test)

    val tree = buildTree(test.root)
    val res = Solution().btreeGameWinningMove(tree, test.n, test.x)

    println(res)
}
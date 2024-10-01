package util.math

class MathE {
    companion object {
        fun factorial(n: Int): Int {
            if (n == 0) return 1
            var result = 1
            for (i in 1..n) {
                result *= i
            }
            return result
        }

        fun fibonacci(n: Int): Int {
            if (n == 0) return 0
            if (n == 1) return 1
            var a = 0
            var b = 1
            var c = 0
            for (i in 2..n) {
                c = a + b
                a = b
                b = c
            }
            return c
        }

        fun isPrime(n: Int): Boolean {
            if (n <= 1) return false
            if (n <= 3) return true
            if (n % 2 == 0 || n % 3 == 0) return false
            var i = 5
            while (i * i <= n) {
                if (n % i == 0 || n % (i + 2) == 0) return false
                i += 6
            }
            return true
        }

        fun gcd(a: Int, b: Int): Int {
            if (b == 0) return a
            return gcd(b, a % b)
        }

        fun lcm(a: Int, b: Int): Int {
            return a * b / gcd(a, b)
        }

        fun isPerfectSquare(n: Int): Boolean {
            val sqrt = kotlin.math.sqrt(n.toDouble()).toInt()
            return sqrt * sqrt == n
        }

        fun isPerfectCube(n: Int): Boolean {
            val cbrt = kotlin.math.cbrt(n.toDouble()).toInt()
            return cbrt * cbrt * cbrt == n
        }

        fun isPowerOfTwo(n: Int): Boolean {
            return n > 0 && n and n - 1 == 0
        }
    }
}
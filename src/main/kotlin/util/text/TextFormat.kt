package util.text

import java.util.*

class TextFormat {
    companion object {
        fun String.isPalindrome(): Boolean {
            val cleaned = this.replace("\\s".toRegex(), "").lowercase(Locale.getDefault())
            return cleaned == cleaned.reversed()
        }
    }
}
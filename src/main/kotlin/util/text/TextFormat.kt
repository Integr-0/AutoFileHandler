package util.text

import net.integr.regexbuilder.build.RegexBuilder
import java.util.*

class TextFormat {
    companion object {
        fun String.isPalindrome(): Boolean {
            val cleaned = this.replace("\\s".toRegex(), "").lowercase(Locale.getDefault())
            return cleaned == cleaned.reversed()
        }

        fun String.isAnagramOf(other: String): Boolean {
            val cleaned = this.replace("\\s".toRegex(), "").lowercase(Locale.getDefault())
            val cleanedOther = other.replace("\\s".toRegex(), "").lowercase(Locale.getDefault())
            return cleaned.toCharArray().sorted() == cleanedOther.toCharArray().sorted()
        }

        fun String.isPangram(): Boolean {
            val cleaned = this.replace("\\s".toRegex(), "").lowercase(Locale.getDefault())
            return ('a'..'z').all { cleaned.contains(it) }
        }

        fun String.isIsogram(): Boolean {
            val cleaned = this.replace("\\s".toRegex(), "").lowercase(Locale.getDefault())
            return cleaned.toCharArray().distinct().size == cleaned.length
        }

        fun String.isVowel(): Boolean {
            return this.matches("[aeiou]".toRegex())
        }

        fun String.isConsonant(): Boolean {
            return this.matches("[bcdfghjklmnpqrstvwxyz]".toRegex())
        }

        fun String.isDigit(): Boolean {
            return this.matches("[0-9]".toRegex())
        }

        fun String.isAlphabetic(): Boolean {
            return this.matches("[a-zA-Z]".toRegex())
        }

        fun String.isAlphanumeric(): Boolean {
            return this.matches("[a-zA-Z0-9]".toRegex())
        }

        fun String.isLowerCase(): Boolean {
            return this.matches("[a-z]".toRegex())
        }

        fun String.isUpperCase(): Boolean {
            return this.matches("[A-Z]".toRegex())
        }

        fun String.isTitleCase(): Boolean {
            return this.matches("[A-Z][a-z]*".toRegex())
        }

        fun String.isCapitalized(): Boolean {
            return this.matches("[A-Z][a-z]*".toRegex())
        }

        fun String.isWhitespace(): Boolean {
            return this.matches("\\s".toRegex())
        }

        fun String.isAscii(): Boolean {
            return this.matches("\\p{ASCII}".toRegex())
        }

        fun String.isPrintable(): Boolean {
            return this.matches("\\p{Print}".toRegex())
        }

        fun String.isNumeric(): Boolean {
            return this.matches("[+-]?([0-9]*[.])?[0-9]+".toRegex())
        }

        fun String.isEmail(): Boolean {
            return this.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex())
        }

        fun String.isUrl(): Boolean {
            return this.matches("^(http|https)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex())
        }

        fun String.isIpv4(): Boolean {
            return this.matches("^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$".toRegex())
        }

        fun String.isIpv6(): Boolean {
            return this.matches("^([0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]{1,4})$".toRegex())
        }

        fun String.isHexColor(): Boolean {
            return this.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$".toRegex())
        }

        fun String.isRgbColor(): Boolean {
            return this.matches("^rgb\\((\\d{1,3}),\\s*(\\d{1,3}),\\s*(\\d{1,3})\\)$".toRegex())
        }

        fun String.isHslColor(): Boolean {
            return this.matches("^hsl\\((\\d{1,3}),\\s*(\\d{1,3})%,\\s*(\\d{1,3})%\\)$".toRegex())
        }

        fun String.isDate(): Boolean {
            return this.matches("^\\d{4}-\\d{2}-\\d{2}$".toRegex())
        }

        fun String.isTime(): Boolean {
            return this.matches("^\\d{2}:\\d{2}:\\d{2}$".toRegex())
        }

        fun String.isDateTime(): Boolean {
            return this.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$".toRegex())
        }
    }
}
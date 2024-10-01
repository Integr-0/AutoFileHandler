/*
 * Copyright © 2024 Integr
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package util.text

import java.math.BigInteger
import java.security.MessageDigest

class Hashing {
    companion object {
        fun String.md5(): String {
            val md = MessageDigest.getInstance("MD5")
            val digest = md.digest(toByteArray())
            val bigInt = BigInteger(1, digest)
            val hashedString = bigInt.toString(16)
            return hashedString
        }

        fun String.sha256(): String {
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(toByteArray())
            val bigInt = BigInteger(1, digest)
            val hashedString = bigInt.toString(16)
            return hashedString
        }

        fun String.sha512(): String {
            val md = MessageDigest.getInstance("SHA-512")
            val digest = md.digest(toByteArray())
            val bigInt = BigInteger(1, digest)
            val hashedString = bigInt.toString(16)
            return hashedString
        }

        fun String.sha1(): String {
            val md = MessageDigest.getInstance("SHA-1")
            val digest = md.digest(toByteArray())
            val bigInt = BigInteger(1, digest)
            val hashedString = bigInt.toString(16)
            return hashedString
        }

        fun String.sha384(): String {
            val md = MessageDigest.getInstance("SHA-384")
            val digest = md.digest(toByteArray())
            val bigInt = BigInteger(1, digest)
            val hashedString = bigInt.toString(16)
            return hashedString
        }

        fun String.sha224(): String {
            val md = MessageDigest.getInstance("SHA-224")
            val digest = md.digest(toByteArray())
            val bigInt = BigInteger(1, digest)
            val hashedString = bigInt.toString(16)
            return hashedString
        }

        fun String.base64Encode(): String {
            return java.util.Base64.getEncoder().encodeToString(toByteArray())
        }

        fun String.base64Decode(): String {
            return String(java.util.Base64.getDecoder().decode(toByteArray()))
        }
    }
}




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

package util.file

import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import kotlin.io.path.*

class AutoFileHandler(private val inputPath: String, private val outputFolder: String = "./outputs") {
    private val fileOutputList: HashMap<String, MutableList<String>> = hashMapOf()

    private val transformedOutputPath = if (outputFolder.endsWith("/")) {
        "$outputFolder${inputPath.replaceRange(inputPath.lastIndexOf('.')..<inputPath.length, "")}/"
    } else {
        "$outputFolder/${inputPath.replaceRange(inputPath.lastIndexOf('.')..<inputPath.length, "")}/"
    }


    fun extractAndReadContents(): AutoFileHandler {
        try {
            println("● Extracting and reading file: $inputPath")
            ZipInputStream(File(inputPath).inputStream()).use { zis ->
                var entry: ZipEntry? = zis.nextEntry

                while (entry != null) {
                    val red = zis.bufferedReader()

                    if (entry.name.endsWith(".in")) {
                        println("   → Found input file: ${entry.name}")

                        var ln = red.readLine()
                        while (ln != null) {
                            if (fileOutputList.containsKey(entry.name)) {
                                fileOutputList[entry.name]?.add(ln)
                            } else {
                                fileOutputList[entry.name] = mutableListOf(ln)
                            }

                            ln = red.readLine()
                        }
                    }


                    zis.closeEntry()
                    entry = zis.nextEntry
                }

                zis.close()

                println("✓ Finished extracting and reading.")

            }
        } catch (e: Exception) {
            println("✕ Error unzipping and reading file: $e")
        }

        return this
    }

    fun readContents(): AutoFileHandler {
        val lines = Path(inputPath).readLines()

        lines.forEach {
            if (fileOutputList.containsKey(inputPath.substringAfter("/"))) {
                fileOutputList[inputPath.substringAfter("/")]?.add(it)
            } else {
                fileOutputList[inputPath.substringAfter("/")] = mutableListOf(it)
            }
        }

        return this
    }

    fun getOutputList(): HashMap<String, MutableList<String>> {
        return fileOutputList
    }

    fun transformEachFileAndWriteOutput(transformer: (MutableList<String>) -> Iterable<String>) {
        println("● Creating directories for output: $transformedOutputPath")
        Path(transformedOutputPath).createDirectories()

        println("● Transforming and writing output.")
        for ((path, value) in fileOutputList) {
            val result = transformer(value)
            val outputPath = transformedOutputPath + path.replaceAfterLast('.', "out")

            Path(outputPath).writeLines(result)

            println("   → Wrote output to: $outputPath")
        }

        println("✓ Finished transforming and writing output.")
    }
}
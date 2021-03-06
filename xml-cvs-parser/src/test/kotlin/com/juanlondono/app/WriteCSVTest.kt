/*
 * MIT License
 *
 * Copyright (c) 2017 Liew Jun Tung
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.juanlondono.app

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Created by jtlie on 3/15/2017.
 */
class WriteCSVTest {

    @Test
    @DisplayName("run read XML Folders")
    fun readXmlFolder() {
        val list = getHeadersFromDirectory()
        stringXmlToDatabase(".", list)
        databaseToCSV(list, ".", "strings.csv", arrayOf("name", "translatable"), "translation")
    }

    @Test
    fun writeArrayCSV() {
        val list = getHeadersFromDirectory()
        arrayXmlToDatabase(".", list)
        databaseToCSV(list, ".", "build/try-arrays.csv", arrayOf("name"), "arrays_translation")
    }

    @Test
    fun writePluralsCSV() {
        val list = getHeadersFromDirectory()
        pluralXmlToDatabase(".", list)
        databaseToCSV(list, ".", "build/try-plurals.csv", arrayOf("name"), "plurals_translation")
    }

    @Test
    fun processWriteCSV(){
        processXMLToCSV(
            "./src/test/resources/test/xml",
            "./src/test/resources/result/csv",
            TranslationType.NORMAL
        )
        processXMLToCSV(
            "./src/test/resources/test/xml",
            "./src/test/resources/result/csv",
            TranslationType.ARRAYS
        )
        processXMLToCSV(
            "./src/test/resources/test/xml",
            "./src/test/resources/result/csv",
            TranslationType.PLURALS
        )
    }
}
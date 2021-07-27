package ro.rsbideveloper.rsbi.test

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import ro.rsbideveloper.rsbi.R
import java.io.IOException
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory


class XMLParser : AppCompatActivity(R.layout.xml_parser) {
    var empDataHashMap = HashMap<String, String>()
    var empList: ArrayList<HashMap<String, String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("XMLPARSER", "Activity has started 1")
        Log.d("XMLPARSER", "Activity has started 2")
        Log.d("XMLPARSER", "Activity has started 3")
        Log.d("XMLPARSER", "Activity has started 4")
        Log.d("XMLPARSER", "Activity has started 5")
        Log.d("XMLPARSER", "Activity has started 6")
        Log.d("XMLPARSER", "Activity has started 7")
        Log.d("XMLPARSER", "Activity has started 8")
        Log.d("XMLPARSER", "Activity has started 9")

        try {
            val istream = assets.open("empdetail.xml")
            val builderFactory = DocumentBuilderFactory.newInstance()
            val docBuilder = builderFactory.newDocumentBuilder()
            val doc = docBuilder.parse(istream)

            // reading the tag "employee" of empdetail file
            val nList = doc.getElementsByTagName("employee")
            for (i in 0 until nList.length) {
                if (nList.item(0).nodeType == Node.ELEMENT_NODE) {
                    //creating instance of HashMap to put the data of node value
                    empDataHashMap = HashMap()
                    val element = nList.item(i) as Element
                    Log.d("XMLPARSER", "Element: $element")
                    empDataHashMap.put("name", getNodeValue("name", element))
                    empDataHashMap.put("salary", getNodeValue("salary", element))
                    empDataHashMap.put("designation", getNodeValue("designation", element))
                    //adding the HashMap data to ArrayList
                    empList.add(empDataHashMap)
                }
            }
        } catch (e: Exception) {
            Log.d("XMLPARSER", "Exception: ${e.message}")
        }

        Log.d("XMLPARSER", "The whole list: $empList")
    }

    protected fun getNodeValue(tag: String, element: Element): String {
        val nodeList = element.getElementsByTagName(tag)
        val node = nodeList.item(0)
        if (node != null) {
            if (node.hasChildNodes()) {
                val child = node.getFirstChild()
                while (child != null) {
                    if (child.getNodeType() === Node.TEXT_NODE) {
                        return child.getNodeValue()
                    }
                }
            }
        }
        return ""
    }

    private fun DoStuff() {
        val pullParserFactory: XmlPullParserFactory
        try {
            pullParserFactory = XmlPullParserFactory.newInstance()
            val parser = pullParserFactory.newPullParser()
            val in_s: InputStream = this.applicationContext.assets.open("empdetail.xml")
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(in_s, null)
            Toast.makeText(this.applicationContext, "size: ", Toast.LENGTH_LONG)
                .show()
            parseXML(parser)
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    private fun parseXML(parser: XmlPullParser?) {

    }
}



    /*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

// NOTE: THIS FILE IS AUTO-GENERATED, DO NOT EDIT!
// See libraries/tools/idl2k for details

//    @file:Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
//    package org.w3c.dom.parsing
//
//    import kotlin.js.*
//    import org.khronos.webgl.*
//    import org.w3c.dom.*
//    import org.w3c.dom.css.*
//    import org.w3c.dom.events.*
//    import org.w3c.dom.svg.*
//    import org.w3c.dom.url.*
//    import org.w3c.fetch.*
//    import org.w3c.files.*
//    import org.w3c.notifications.*
//    import org.w3c.performance.*
//    import org.w3c.workers.*
//    import org.w3c.xhr.*
//
//    /**
//     * Exposes the JavaScript [DOMParser](https://developer.mozilla.org/en/docs/Web/API/DOMParser) to Kotlin
//     */
//    public external open class DOMParser {
//        fun parseFromString(str: String, type: dynamic): Document
//    }
//
//    /**
//     * Exposes the JavaScript [XMLSerializer](https://developer.mozilla.org/en/docs/Web/API/XMLSerializer) to Kotlin
//     */
//    public external open class XMLSerializer {
//        fun serializeToString(root: Node): String
//    }
package com.demo.common.constant

import java.util.HashMap

class CommonMap {
    // CommmonMap的伴生对象
    companion object { //伴生体 内部：静态常量、静态变量、静态方法
//        /**
//         * 状态编码转换
//         */
//        var javaTypeMap: MutableMap<String, String> = HashMap()
//
//        init {
//            initJavaTypeMap()
//        }
//
//        /**
//         * 返回状态映射
//         */
//        fun initJavaTypeMap() {
//            javaTypeMap["tinyint"] = "Integer"
//            javaTypeMap["smallint"] = "Integer"
//            javaTypeMap["mediumint"] = "Integer"
//            javaTypeMap["int"] = "Integer"
//            javaTypeMap["number"] = "Integer"
//            javaTypeMap["integer"] = "integer"
//            javaTypeMap["bigint"] = "Long"
//            javaTypeMap["float"] = "Float"
//            javaTypeMap["double"] = "Double"
//            javaTypeMap["decimal"] = "BigDecimal"
//            javaTypeMap["bit"] = "Boolean"
//            javaTypeMap["char"] = "String"
//            javaTypeMap["varchar"] = "String"
//            javaTypeMap["varchar2"] = "String"
//            javaTypeMap["tinytext"] = "String"
//            javaTypeMap["text"] = "String"
//            javaTypeMap["mediumtext"] = "String"
//            javaTypeMap["longtext"] = "String"
//            javaTypeMap["time"] = "Date"
//            javaTypeMap["date"] = "Date"
//            javaTypeMap["datetime"] = "Date"
//            javaTypeMap["timestamp"] = "Date"
//        }
        // 创建HashMap集合
        var javaTypeMap = hashMapOf<String, String>(
                "tinyint" to "Integer",
                "smallint" to "Integer",
                "mediumint" to "Integer",
                "int" to "Integer",
                "number" to "Integer",
                "integer" to "integer",
                "bigint" to "Long",
                "float" to "Float",
                "double" to "Double",
                "decimal" to "BigDecimal",
                "bit" to "Boolean",
                "char" to "String",
                "varchar" to "String",
                "varchar2" to "String",
                "tinytext" to "String",
                "text" to "String",
                "mediumtext" to "String",
                "longtext" to "String",
                "time" to "Date",
                "date" to "Date",
                "datetime" to "Date",
                "timestamp" to "Date"
        )
    }
}
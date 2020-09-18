package com.kotlin.mvvm.utils

/**
 * Created by Waheed on 08,November,2019
 */

/**
 * Get Country Short Key Required For API.
 */
object CountryNameMapping {

    fun getCountryKey(country: String): String {

        val hashMap: HashMap<String, String> = HashMap()

        hashMap["Argentina.png"] = "ar"
        hashMap["Australia.png"] = "au"
        hashMap["Austria.png"] = "at"
        hashMap["Belgium.png"] = "be"
        hashMap["Brazil.png"] = "br"
        hashMap["Bulgaria.png"] = "bg"
        hashMap["Canada.png"] = "ca"
        hashMap["China.png"] = "cn"
        hashMap["Colombia.png"] = "co"
        hashMap["Cuba.png"] = "cu"
        hashMap["Czech_Republic.png"] = "cz"
        hashMap["Egypt.png"] = "eg"
        hashMap["France.png"] = "fr"
        hashMap["Germany.png"] = "de"
        hashMap["Greece.png"] = "gr"
        hashMap["Hong_Kong.png"] = "hk"
        hashMap["Hungary.png"] = "hu"
        hashMap["India.png"] = "in"
        hashMap["Indonesia.png"] = "id"
        hashMap["Ireland.png"] = "ie"
        hashMap["Israel.png"] = "il"
        hashMap["Italy.png"] = "it"
        hashMap["Japan.png"] = "jp"
        hashMap["Latvia.png"] = "lv"
        hashMap["Lithuania.png"] = "lt"
        hashMap["Malaysia.png"] = "my"
        hashMap["Mexico.png"] = "mx"
        hashMap["Morocco.png"] = "ma"
        hashMap["Netherlands.png"] = "nl"
        hashMap["New_Zealand.png"] = "nz"
        hashMap["Nigeria.png"] = "ng"
        hashMap["Norway.png"] = "no"
        hashMap["Philippines.png"] = "ph"
        hashMap["Poland.png"] = "pl"
        hashMap["Portugal.png"] = "pt"
        hashMap["Romania.png"] = "ro"
        hashMap["Russia.png"] = "ru"
        hashMap["Saudi_Arabia.png"] = "sa"
        hashMap["Serbia.png"] = "rs"
        hashMap["Singapore.png"] = "sg"
        hashMap["Slovakia.png"] = "sk"
        hashMap["Slovenia.png"] = "si"
        hashMap["South_Africa.png"] = "za"
        hashMap["South_Korea.png"] = "kr"
        hashMap["Sweden.png"] = "se"
        hashMap["Switzerland.png"] = "ch"
        hashMap["Taiwan.png"] = "tw"
        hashMap["Thailand.png"] = "th"
        hashMap["Turkey.png"] = "tr"
        hashMap["UAE.png"] = "ae"
        hashMap["Ukraine.png"] = "ua"
        hashMap["United_Kingdom.png"] = "gb"
        hashMap["United_States_of_America.png"] = "us"
        hashMap["Venezuela.png"] = "ve"

        /**
         * Return value for the country key otherwise return US as a default country
         * getOrElse examples below
         *
         * val map = mutableMapOf<String, Int?>()
         *
         * println(map.getOrElse("x") { 1 }) // 1
         *
         * map["x"] = 3
         * println(map.getOrElse("x") { 1 }) // 3
         *
         *  map["x"] = null
         *  println(map.getOrElse("x") { 1 }) // 1
         */

        return hashMap.getOrElse(country, defaultValue = { "us" })
    }
}
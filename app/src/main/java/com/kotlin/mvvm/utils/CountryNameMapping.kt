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

        hashMap.put("Argentina.png", "ar")
        hashMap.put("Australia.png", "au")
        hashMap.put("Austria.png", "at")
        hashMap.put("Belgium.png", "be")
        hashMap.put("Brazil.png", "br")
        hashMap.put("Bulgaria.png", "bg")
        hashMap.put("Canada.png", "ca")
        hashMap.put("China.png", "cn")
        hashMap.put("Colombia.png", "co")
        hashMap.put("Cuba.png", "cu")
        hashMap.put("Czech_Republic.png", "cz")
        hashMap.put("Egypt.png", "eg")
        hashMap.put("France.png", "fr")
        hashMap.put("Germany.png", "de")
        hashMap.put("Greece.png", "gr")
        hashMap.put("Hong_Kong.png", "hk")
        hashMap.put("Hungary.png", "hu")
        hashMap.put("India.png", "in")
        hashMap.put("Indonesia.png", "id")
        hashMap.put("Ireland.png", "ie")
        hashMap.put("Israel.png", "il")
        hashMap.put("Italy.png", "it")
        hashMap.put("Japan.png", "jp")
        hashMap.put("Latvia.png", "lv")
        hashMap.put("Lithuania.png", "lt")
        hashMap.put("Malaysia.png", "my")
        hashMap.put("Mexico.png", "mx")
        hashMap.put("Morocco.png", "ma")
        hashMap.put("Netherlands.png", "nl")
        hashMap.put("New_Zealand.png", "nz")
        hashMap.put("Nigeria.png", "ng")
        hashMap.put("Norway.png", "no")
        hashMap.put("Philippines.png", "ph")
        hashMap.put("Poland.png", "pl")
        hashMap.put("Portugal.png", "pt")
        hashMap.put("Romania.png", "ro")
        hashMap.put("Russia.png", "ru")
        hashMap.put("Saudi_Arabia.png", "sa")
        hashMap.put("Serbia.png", "rs")
        hashMap.put("Singapore.png", "sg")
        hashMap.put("Slovakia.png", "sk")
        hashMap.put("Slovenia.png", "si")
        hashMap.put("South_Africa.png", "za")
        hashMap.put("South_Korea.png", "kr")
        hashMap.put("Sweden.png", "se")
        hashMap.put("Switzerland.png", "ch")
        hashMap.put("Taiwan.png", "tw")
        hashMap.put("Thailand.png", "th")
        hashMap.put("Turkey.png", "tr")
        hashMap.put("UAE.png", "ae")
        hashMap.put("Ukraine.png", "ua")
        hashMap.put("United_Kingdom.png", "gb")
        hashMap.put("United_States_of_America.png", "us")
        hashMap.put("Venezuela.png", "ve")

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
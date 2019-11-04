package com.kotlin.mvvm.utils

import java.util.regex.Pattern

/**
 * Created by Waheed on 04,November,2019
 */

object StringUtils {
    private val EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    private val IP_REGEX = "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9]))"

    fun isExistNull(vararg arr: String?): Boolean {
        var isExistNull = true
        for (a in arr) {
            if (!checkEmpty(a)) {
                isExistNull = false
                continue
            }
            isExistNull = true
            break
        }
        return isExistNull
    }

    private fun checkEmpty(string: String?) = string == null || string.isEmpty()

    fun isValidEmail(email: String) = Pattern.compile(EMAIL_REGEX).matcher(email).matches()

    fun isValidPhoneNumber(phoneNumber: String) = !isExistNull(phoneNumber)
            && (phoneNumber.length == 10 || phoneNumber.length == 11)

    fun isValidIpAddress(ipAddress: String) = !isExistNull(ipAddress) && ipAddress.matches(IP_REGEX.toRegex())
}
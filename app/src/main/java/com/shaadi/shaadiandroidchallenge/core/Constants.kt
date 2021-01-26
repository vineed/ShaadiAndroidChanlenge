package com.shaadi.shaadiandroidchallenge.core

interface Constants {

    interface MESSAGE {
        companion object {
            const val PLEASE_WAIT = "Please wait..."
        }
    }

    interface ERROR {
        companion object {
            const val CHECK_NETWORK =
                "Network error, please check whether you have a active internet connection."
            const val ERROR_OCCURRED = "Some error occurred."
        }
    }

}
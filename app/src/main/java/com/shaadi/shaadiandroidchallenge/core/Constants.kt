package com.shaadi.shaadiandroidchallenge.core

interface Constants {

    interface MESSAGE {
        companion object {
            const val PLEASE_WAIT = "Please wait..."
        }
    }

    interface ERROR {
        companion object{
            const val CHECK_NETWORK = "Network error occured, please check whether you are connetion to internet."
            const val ERROR_OCCURRED = "Some error occurred."
        }
    }

}
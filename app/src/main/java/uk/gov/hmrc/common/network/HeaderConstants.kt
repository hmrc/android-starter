package uk.gov.hmrc.common.network

class HeaderConstants {
    object Authorization {
        const val NAME = "Authorization"

        fun getValue(token: String): String {
            return "Bearer $token"
        }
    }

    object Accept {
        const val NAME = "Accept"

        val value: String
            get() = "application/json"
    }
}

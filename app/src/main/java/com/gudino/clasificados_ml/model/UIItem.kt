package com.gudino.clasificados_ml.model

/**
 * UI item used to full the detail of a classified
 */
data class UIItem(val type: Int, val text: String? = "") {

    companion object {
        const val TEXT_TYPE = 0
        const val BUTTONS_TYPE = 1
        const val TITLE_TYPE = 2
        const val PRICE_TYPE = 3
    }
}
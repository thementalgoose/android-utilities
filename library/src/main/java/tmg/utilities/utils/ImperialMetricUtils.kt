package tmg.utilities.utils

import kotlin.math.roundToInt

/**
 * Measurement utilites and conversions (ie. kg, mm, weight,
 */
class ImperialMetricUtils {

    private val stToLb = 14
    private val lbToKg = 0.45
    private val ftToIn = 12
    private val inToCm = 2.54

    fun cmToFtIn(cm: Double): Pair<Int, Int> {
        val inches = (cm / inToCm).roundToInt()
        val feet = inches / ftToIn
        return feet to inches - (feet * ftToIn)
    }

    fun kgToStLb(kg: Double): Pair<Int, Int> {
        val inches = (kg / lbToKg).roundToInt()
        val feet = inches / stToLb
        return feet to inches - (feet * stToLb)
    }
}
package tmg.utilities.utils

import tmg.utilities.utils.ImperialMetricUtils.ftToIn
import tmg.utilities.utils.ImperialMetricUtils.inToCm
import tmg.utilities.utils.ImperialMetricUtils.lbToKg
import tmg.utilities.utils.ImperialMetricUtils.stToLb
import kotlin.math.roundToInt

/**
 * Measurement utilites and conversions (ie. kg, mm, weight,
 */
object ImperialMetricUtils {
    val stToLb = 14
    val lbToKg = 0.45
    val ftToIn = 12
    val inToCm = 2.54
}

// TODO: Add more of these

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
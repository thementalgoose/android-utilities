package tmg.utilities.extensions

/**
 * Isolate the keys out of a list of pairs and store them in a set
 */
fun <T,E> List<Pair<T, E>>.keySet(): Set<T> {
    return this
        .map { (key, _) -> key }
        .toSet()
}
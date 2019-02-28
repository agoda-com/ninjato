package com.agoda.fleksora.misc

fun MutableMap<String, MutableList<String>>.add(pair: Pair<String, String>) {
    if (this[pair.first] != null) {
        this[pair.first]?.add(pair.second)
    } else {
        this[pair.first] = mutableListOf(pair.second)
    }
}

fun MutableMap<String, MutableList<String>>.addAll(pair: Pair<String, List<String>>) {
    if (this[pair.first] != null) {
        this[pair.first]?.addAll(pair.second)
    } else {
        this[pair.first] = pair.second.toMutableList()
    }
}

fun MutableMap<String, MutableList<String>>.addAll(map: Map<String, List<String>>) {
    map.forEach { key, value -> addAll(key to value) }
}

fun MutableMap<String, MutableList<String>>.remove(pair: Pair<String, String>) {
    this[pair.first]?.remove(pair.second)
}

fun MutableMap<String, MutableList<String>>.removeAll(pair: Pair<String, List<String>>) {
    pair.second.forEach { remove(pair.first to it) }
}

fun MutableMap<String, MutableList<String>>.removeAll(map: Map<String, List<String>>) {
    map.forEach { key, value -> removeAll(key to value) }
}

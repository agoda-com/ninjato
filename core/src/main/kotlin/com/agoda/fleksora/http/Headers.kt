package com.agoda.fleksora.http

class Headers {
    internal var parent: Headers? = null

    private val added: MutableMap<String, MutableList<String>> = mutableMapOf()
    private val removed: MutableMap<String, MutableList<String>> = mutableMapOf()
    private val removedAll: MutableList<String> = mutableListOf()
    private val overridden: MutableMap<String, MutableList<String>> = mutableMapOf()
    private val set: MutableMap<String, MutableList<String>> = mutableMapOf()

    operator fun plusAssign(header: Pair<String, String>) {
        add(added, header)
    }

    operator fun minusAssign(header: Pair<String, String>) {
        add(removed, header)
    }

    operator fun minusAssign(header: String) {
        removedAll.add(header)
    }

    infix fun override(header: Pair<String, List<String>>) {
        overridden[header.first] = header.second.toMutableList()
    }

    infix fun set(headers: Map<String, List<String>>) {
        with(set) {
            clear()
            putAll(headers.mapValues { it.value.toMutableList() })
        }
    }

    @PublishedApi
    internal fun resolve(): MutableMap<String, MutableList<String>> {
        return if (set.isNotEmpty()) set else (parent?.resolve() ?: mutableMapOf()).apply {
            added.forEach { entry -> entry.value.forEach { add(this, entry.key to it) } }
            removed.forEach { entry -> entry.value.forEach { remove(this, entry.key to it) } }
            removedAll.forEach { remove(it) }
            overridden.forEach { put(it.key, it.value) }
        }
    }

    private fun add(map: MutableMap<String, MutableList<String>>, header: Pair<String, String>) {
        if (map[header.first] != null) {
            map[header.first]?.add(header.second)
        } else {
            map[header.first] = mutableListOf(header.second)
        }
    }

    private fun remove(map: MutableMap<String, MutableList<String>>, header: Pair<String, String>) {
        map[header.first]?.remove(header.second)
    }
}

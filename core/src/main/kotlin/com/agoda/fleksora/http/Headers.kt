package com.agoda.fleksora.http

open class Headers {
    protected val added: MutableMap<String, MutableList<String>> = mutableMapOf()
    protected val removed: MutableMap<String, MutableList<String>> = mutableMapOf()
    protected val removedAll: MutableList<String> = mutableListOf()
    protected val overridden: MutableMap<String, List<String>> = mutableMapOf()

    operator fun plusAssign(header: Pair<String, String>) {
        add(added, header)
    }

    operator fun minusAssign(header: Pair<String, String>) {
        add(removed, header)
    }

    operator fun minusAssign(header: String) {
        removedAll.add(header)
    }

    infix fun override(headers: Map<String, List<String>>) {
        with(overridden) {
            clear()
            putAll(headers)
        }
    }

    private fun add(map: MutableMap<String, MutableList<String>>, header: Pair<String, String>) {
        if (map[header.first] != null) {
            map[header.first]?.add(header.second)
        } else {
            map[header.first] = mutableListOf(header.second)
        }
    }
}

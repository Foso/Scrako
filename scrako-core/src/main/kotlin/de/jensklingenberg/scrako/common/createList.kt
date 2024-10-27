package de.jensklingenberg.scrako.common
fun ScriptBuilder.createList(name: String, contents: List<String>): ScratchList {
    val element = ScratchList(name, contents)
    lists.add(element)
    return element
}
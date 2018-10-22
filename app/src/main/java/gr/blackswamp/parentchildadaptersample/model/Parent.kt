package gr.blackswamp.parentchildadaptersample.model

data class Parent(var idD: Int = 0) {
    var dept: String = ""
    var fkD: Int = 0
    private val _children = mutableListOf<Child>()
    val children
        get() = _children.toList()


    fun addChild(id: Int, item: String) {
        _children.add(Child(id).apply { this.item = item })
    }
}
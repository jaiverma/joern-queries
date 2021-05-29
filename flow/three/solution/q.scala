def getFlow() = {
    val methods = cpg.method.name("memcpy").caller
    val filteredMethods = methods.filter(
        method => {
            val src = method.parameter
            val sink = method.ast.isCallTo("memcpy").argument(3)
            sink.reachableBy(src)
        }.size > 0
    )

    val src = cpg.call.name("ntohl")
    val sink = filteredMethods.parameter.argument
    sink.reachableByFlows(src).p
}

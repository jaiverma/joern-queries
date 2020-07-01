def getFlow() = {
    val methods = cpg.method.name("memcpy").caller
    val filteredMethods = methods.l.filter(
        method => {
            val src = method.start.parameter
            val sink = method.start.ast.isCallTo("memcpy").argument.order(3)
            sink.reachableBy(src)
        }.size > 0
    ).start

    val src = cpg.call.name("ntohl")
    val sink = filteredMethods.parameter.argument
    sink.reachableByFlows(src).p
}

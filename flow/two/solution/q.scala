def getFlow() = {
    // this gives us methods which call ntohl
    val methods = cpg.method.name("ntohl").caller

    // we want to filter those methods where the value returned by
    // ntohl is returned by the method
    val filteredMethods = methods.l.filter(
        method => {
            val src = method.start.ast.isCallTo("ntohl")
            val sink = method.start.methodReturn
            sink.reachableBy(src)
        }.size > 0
    ).start
    
    // we will treat call to these filtered methods as good as a call to
    // ntohl. this is will only get one layer of calls though...
    val srcs = filteredMethods.name.l.map(cpg.call.name(_))
    val sink = cpg.call.name("memcpy").argument.order(3)

    srcs.map(sink.reachableByFlows(_).p)
}

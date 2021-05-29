def getFlow() = {
    val src = cpg.call.name("ntohl")
    val sink = cpg.call.name("memcpy").argument(3)
    sink.reachableByFlows(src).p
}

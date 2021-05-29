// our approach is something like this
// look for functions `f`, where there is the following pattern:
//      ```
//      int x = ntohl(...);
//      ...
//      return x;
// ```
// similarly, look for functions `g` where there is the following pattern:
//      ```
//      int x = f();
//      ...
//      return x;
//      ```
// do this recursively till we find a function `h` which satisfies the
// constraint:
// cpg.call.name("memcpy").argument.order(3).reachableBy(cpg.call.name("h"))

// we will start with something like this
// find which functions call ntohl
// cpg.method.name("ntohl").caller.p
// for each of the callers, we will see if one of those calls reach our call
// to memcpy

def getFlows(methodName: String) : Unit = {
    val candidateFuncs = cpg.method.name(methodName)
        .caller
        .filter(func => {
            val src = func.parameter
            val sink = func.methodReturn
            sink.reachableBy(src)
        }.size > 0)
        .name
        .l
    for (f <- candidateFuncs) {
        def src = cpg.call.name(f)
        def sink = cpg.call.name("memcpy").argument(3)
        if (src.size > 0) {
            def flow = sink.reachableBy(src)
            if (flow.size > 0) {
                print(sink.reachableByFlows(src).p)
            }
            else {
                getFlows(f)
            }
        }
        else {
            return
        }
    }
}

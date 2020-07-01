#include <stdlib.h>
#include <string.h>

// we want to find this
void g (char* buf, int n) {
    char* s = malloc(10);
    memcpy(s, buf, n);
}

void foo (int n) {
    int x = ntohl(n);
    char* s = malloc(x);
    g(s, x);
}

// we don't want this one since it does not use `n`
// in the call to `memcpy`
// we're still getting this in the current version of Joern though
// it looks like Joern does not support argument level granularity
void h (char* buf, int n) {
    char* s = malloc(10);
    int x = 10;
    memcpy(s, buf, x);
}

void bar (int n) {
    int x = ntohl(n);
    char* s = malloc(x);
    h(s, x);
}

// we definitely shouldn't be getting this since any of the arguments
// in the memcpy call are reachable from the method parameters
void i (char* buf, int n) {
    char* t = malloc(10);
    char* s = malloc(10);
    int x = 10;
    memcpy(s, t, x);
}

void baz (int n) {
    int x = ntohl(n);
    char* s = malloc(x);
    i(s, x);
}

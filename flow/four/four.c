#include <stdlib.h>
#include <string.h>

int f (int x) {
    int n = ntohl(x);
    return n;
}

int f2 (int x) {
    int n = ntohl(x);
    return 42;
}

int g (int x) {
    int n = f(x);
    return n;
}

// we don't want to find this
int g2 (int x) {
    int n = f2(x);
    return n;
}

// we want to find this
int h (int x) {
    int n = g(x);
    return n;
}

void foo(char* buf, int n) {
    int sz = h(n);
    char* s = malloc(10);
    memcpy(s, buf, sz);
}

void bar(char* buf, int n) {
    int sz = g2(n);
    char* s = malloc(10);
    memcpy(s, buf, sz);
}

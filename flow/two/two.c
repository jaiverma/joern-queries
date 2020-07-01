#include <stdlib.h>
#include <string.h>

int f (int n) {
    int x = ntohl(n);
    return x;
}

int g (int n) {
    int x = ntohl(n);
    return 42;
}

void foo (int a, char* buf) {
    int n = f(a);
    char* s = malloc(100);
    memcpy(s, buf, n);
}

void bar (int a, char* buf) {
    int n = g(a);
    char* s = malloc(100);
    memcpy(s, buf, n);
}

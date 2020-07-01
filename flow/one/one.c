#include <stdlib.h>
#include <string.h>

// we want to find this
void f(char* buf, int x) {
    int n = ntohl(x);
    char* s = malloc(10);
    memcpy(s, buf, n);
}

// we don't want to find this
void g(char*buf, int x) {
    int a = ntohl(x);
    int b = 5;
    char* s = malloc(10);
    memcpy(s, buf, b);
}

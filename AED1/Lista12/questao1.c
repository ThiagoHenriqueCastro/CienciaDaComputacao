#include <stdio.h>
#include <stdlib.h>
void troca(int *x, int *y);
int main()
{
    int a = 5, b = 2;
    troca(&a, &b);
    printf("\n%i, %i \n", a, b);
}
void troca(int *x, int *y)
{
    int aux;
    aux = *x;
    *x = *y;
    *y = aux;
}
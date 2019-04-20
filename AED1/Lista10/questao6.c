#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

bool par(int numero);
int scanNum();
int main()
{
    int a = scanNum();
    bool isPar = par(a);
    if (isPar)
        printf("\nO numero é par!\n");
    else
        printf("\nO numero não é par!\n");
}
int scanNum()
{
    int x = 0;
    printf("\nDigite o numero: ");
    scanf("%i", &x);
    return x;
}
bool par(int numero)
{
    if (numero % 2 == 0)
        return 1;
    else
        return 0;
}
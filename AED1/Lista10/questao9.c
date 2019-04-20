#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

int fatorial(int n);
int scanNum();
int main()
{
    int a = scanNum();
    int valorFatorial = fatorial(a);
    printf("\nO fatorial de %i é: %i\n", a, valorFatorial);
}
int scanNum()
{
    int x = 0;
    printf("\nDigite o numero: ");
    scanf("%i", &x);
    return x;
}
int fatorial(int n)
{
    int fatorial = 1;
    for (int i = n; i > 1; i--)
    {
        fatorial *= i;
    }
    return fatorial;
}
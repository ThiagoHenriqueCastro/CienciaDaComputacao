#include <stdlib.h>
#include <stdio.h>
#include <math.h>
int somaCubo(int n);
int somatorio = 0;
int main()
{
    int soma = somaCubo(3);
    printf("%i \n", soma);
}
int somaCubo(int n)
{
    if (n >= 1)
    {
        somatorio += pow(n, 3);
        somaCubo(n - 1);
    }
    return somatorio;
}
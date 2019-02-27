#include <stdio.h>
#include <stdlib.h>

int main()
{
    float a, b, x;
    printf("Primeiro grau\n");
    printf("Digite o valor de A\n");
    scanf("%f", &a);
    printf("Digite o valor de B\n");
    scanf("%f", &b);
    x = -b / a;

    printf("O valor de x é igual a %.2f\n", x);
}
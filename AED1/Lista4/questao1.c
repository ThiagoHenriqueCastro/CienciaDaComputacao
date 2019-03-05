#include <stdio.h>
#include <stdlib.h>

int main()
{
    float a, b, maior;
    printf("Digite o valor do primeiro numero\n");
    scanf("%f", &a);
    printf("Digite o valor do segundo numero\n");
    scanf("%f", &b);
    if (a > b)
    {
        maior = a;
    }
    else
    {
        maior = b;
    }

    printf("O maior numero é %.2f\n", maior);
}
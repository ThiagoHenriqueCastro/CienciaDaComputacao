#include <stdio.h>
#include <stdlib.h>

int main()
{
    float a, b, c, maior;
    printf("Digite o valor do primeiro numero\n");
    scanf("%f", &a);
    printf("Digite o valor do segundo numero\n");
    scanf("%f", &b);
    printf("Digite o valor do terceiro numero\n");
    scanf("%f", &c);
    if (a > b && a > c)
    {
        maior = a;
    }
    else if (b > a && b > c)
    {
        maior = b;
    }
    else
    {
        maior = c;
    }

    printf("O maior numero é %.2f\n", maior);
}
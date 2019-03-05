#include <stdio.h>
#include <stdlib.h>

int main()
{
    float a, b, c, segundoMaior;
    printf("Digite o valor do primeiro numero\n");
    scanf("%f", &a);
    printf("Digite o valor do segundo numero\n");
    scanf("%f", &b);
    printf("Digite o valor do terceiro numero\n");
    scanf("%f", &c);
    if (a > b && a > c)
    {
        if (b > c)
        {
            segundoMaior = b;
        }
        else
        {
            segundoMaior = c;
        }
    }
    else if (b > a && b > c)
    {
        if (a > c)
        {
            segundoMaior = a;
        }
        else
        {
            segundoMaior = c;
        }
    }
    else
    {
        if (b > a)
        {
            segundoMaior = b;
        }
        else
        {
            segundoMaior = a;
        }
    }

    printf("O segundo maior numero é %.2f\n", segundoMaior);
}
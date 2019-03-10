#include <stdio.h>
#include <stdlib.h>

int main()
{
    float a, b, c;
    printf("Digite o valor do primeiro numero\n");
    scanf("%f", &a);
    printf("Digite o valor do segundo numero\n");
    scanf("%f", &b);
    printf("Digite o valor do terceiro numero\n");
    scanf("%f", &c);
    if (a + b < c)
    {
        printf("a + b é menor que c\n");
    }
    else
    {
        printf("a + b não é menor que c\n");
    }
}
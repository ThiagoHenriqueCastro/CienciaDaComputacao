#include <stdio.h>
#include <stdlib.h>

int main()
{
    float base, altura, area;
    printf("Area Retangulo\n");
    printf("Digite o valor da base\n");
    scanf("%f", &base);
    printf("Digite o valor da altura\n");
    scanf("%f", &altura);
    area = base * altura;

    printf("O valor da area é igual a %.2f\n", area);
}
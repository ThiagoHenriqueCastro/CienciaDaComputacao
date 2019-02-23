#include <stdio.h>
#include <stdlib.h>

int main()
{
    float base, altura, perimetro;
    printf("Calculo do perimetro do retangulo\n");
    printf("Digite a base\n");
    scanf("%f", &base);
    printf("Digite a altura\n");
    scanf("%f", &altura);

    perimetro = base * 2 + altura * 2;

    printf("O perimetro do retangulo descrito é %.2f\n", perimetro);
}
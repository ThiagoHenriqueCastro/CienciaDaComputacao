#include <stdio.h>
#include <stdlib.h>

int main()
{
    float altura;
    printf("Calculo do peso ideal masculino\n");
    printf("Digite sua altura, em metros\n");
    scanf("%f", &altura);
    float peso_ideal = (72.7 * altura) - 58;

    printf("O peso ideal de uma pessoa com altura igual a %.2f é %.2f\n", altura, peso_ideal);
}
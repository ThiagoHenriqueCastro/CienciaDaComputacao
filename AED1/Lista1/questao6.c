#include <stdio.h>
#include <stdlib.h>

int main()
{
    float altura = 1.85;
    float peso_ideal = (72.7 * altura) - 58;

    printf("O peso ideal de uma pessoa com altura igual a %.2f é %.2f", altura, peso_ideal);
}
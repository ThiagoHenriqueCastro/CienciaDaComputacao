#include <stdio.h>
#include <stdlib.h>

int main()
{
    char sexo;
    float altura;
    printf("Digite sua altura, em metros\n");
    scanf("%f", &altura);
    printf("Digite o seu sexo\n");
    printf("m para masculino e f para feminino\n");
    scanf(" %c", &sexo);
    if (sexo == 'm')
    {
        printf("Calculo do peso ideal masculino\n");
        float peso_ideal = (72.7 * altura) - 58;

        printf("O peso ideal de um homem com altura igual a %.2f é %.2f\n", altura, peso_ideal);
    }
    else if (sexo == 'f')
    {
        printf("Calculo do peso ideal feminino\n");
        float peso_ideal = (62.1 * altura) - 44.7;

        printf("O peso ideal de uma mulher com altura igual a %.2f é %.2f\n", altura, peso_ideal);
    }
    else
    {
        printf("Valor invalido\n");
    }
}
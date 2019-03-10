#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    float peso_kg, altura_metro, IMC;
    printf("Digite o peso em KG\n");
    scanf("%f", &peso_kg);
    printf("Digite a altura em metros\n");
    scanf("%f", &altura_metro);
    IMC = peso_kg / pow(altura_metro, 2);
    if (peso_kg > 0 && altura_metro > 0)
    {   
        printf("\n");
        printf("IMC -> %.2f\n", IMC);
        if (IMC < 16)
        {

            printf("MAGREZA GRAVE\n");
        }
        else if (IMC < 17)
        {

            printf("MAGREZA MODERADA\n");
        }
        else if (IMC < 18.5)
        {

            printf("MAGREZA LEVE\n");
        }
        else if (IMC < 25)
        {

            printf("SAUDAVEL\n");
        }
        else if (IMC < 30)
        {

            printf("SOBREPESO\n");
        }
        else if (IMC < 35)
        {

            printf("OBESIDADE GRAU I\n");
        }
        else if (IMC < 40)
        {

            printf("OBESIDADE GRAU II\n");
        }
        else
        {

            printf("OBESIDADE GRAU III\n");
        }
    }
    else
    {
        printf("Peso e/ou altura Invalidas\n");
    }
}
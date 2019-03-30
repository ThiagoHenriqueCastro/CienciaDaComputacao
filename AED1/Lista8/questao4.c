#include <stdlib.h>
#include <stdio.h>

int main()
{
    int continuar;
    do
    {
        int num, maior = 0, menor = 10000, amplitude = 0;
        do
        {
            do
            {
                printf("\nDigite seu número: ");
                scanf("%i", &num);
                if (num < 0)
                {
                    printf("\nValor Invalido");
                }
            } while (num < 0);
            if (num > maior && num > 0)
            {
                maior = num;
            }
            if (num < menor && num > 0)
            {
                menor = num;
            }
        } while (num != 0);
        amplitude = maior - menor;
        if (amplitude != -10000)
        {
            printf("\nAmplitude = %i\n", amplitude);
        }
        else
        {
            printf("\nNenhum valor informado\n");
        }
        do
        {
            printf("Deseja continuar?\n");
            printf("0-Nao\n");
            printf("1-Sim\n");
            scanf("%i", &continuar);
        } while (continuar != 0 && continuar != 1);
    } while (continuar == 1);
}
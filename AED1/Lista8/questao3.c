#include <stdlib.h>
#include <stdio.h>

int main()
{
    int primeiro = 0, razao = 0, x, termos = 0, continuar;

    do
    {
        int divisivel = 0;
        printf("Digite o primeiro termo: ");
        scanf("%i", &primeiro);
        printf("\nDigite a razao: ");
        scanf("%i", &razao);
        printf("\nDigite o numero de termos: ");
        scanf("%i", &termos);
        printf("\nDigite o numero de x para o teste: ");
        scanf("%i", &x);

        for (int i = 0; i < termos; i++)
        {
            if (primeiro % x == 0)
            {
                divisivel++;
            }
            primeiro += razao;
        }

        printf("\nNesta progressao, %i termos sao divisiveis por %i.\n", divisivel, x);
        do
        {
            printf("Deseja continuar?\n");
            printf("0-Nao\n");
            printf("1-Sim\n");
            scanf("%i", &continuar);
        } while (continuar != 0 && continuar != 1);

    } while (termos > 0 && continuar == 1);
}
#include <stdlib.h>
#include <stdio.h>
#include <math.h>

int main()
{

    int voto, chapa1 = 0, chapa2 = 0, branco = 0, nulo = 0, n = 10;
    float porcentagem;
    for (int i = 0; i < n; i++)
    {

        do
        {
            printf("VOTACAO!!!!\n");
            printf("0-Sair\n");
            printf("1-Chapa 1\n");
            printf("2-Chapa 2\n");
            printf("3-Branco\n");
            printf("4-Nulo\n");
            scanf("%d", &voto);
            switch (voto)
            {
            case 0:
                i = n;
                break;
            case 1:
                chapa1++;
                break;
            case 2:
                chapa2++;
                break;
            case 3:
                branco++;
                break;
            case 4:
                nulo++;
                break;
            default:
                printf("Numero fora do dominio da eleiçao, digite novamente\n");
                break;
            }

        } while (voto != 0 && voto != 1 && voto != 2 && voto != 3 && voto != 4);
    }

    if (chapa1 > chapa2)
    {
        porcentagem = (chapa1 / (double)n) * 100;
        printf("Chapa 1 venceu!!!\n");
        printf("Porcentagem: %f\n", porcentagem);
    }
    else if (chapa2 > chapa1)
    {
        porcentagem = (chapa2 / (double)n) * 100;
        printf("Chapa 2 venceu!!!\n");
        printf("Porcentagem: %f\n", porcentagem);
    }
    else
    {
        printf("EMPATE\n");
    }
    printf("Chapa 1: %d\n", chapa1);
    printf("Chapa 2: %d\n", chapa2);
    printf("Brancos: %d\n", branco);
    printf("Nulos: %d\n", nulo);
}
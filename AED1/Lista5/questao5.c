#include <stdio.h>
#include <stdlib.h>

int main()
{
    int dia;
    printf("Digite mês\n");
    scanf("%d", &dia);
    switch (dia)
    {
        case 1:
            printf("Janeiro - 31\n");
            break;
        case 2:
            printf("Fevereiro - 28/29\n");
            break;
        case 3:
            printf("Março - 31\n");
            break;
        case 4:
            printf("Abril - 30\n");
            break;
        case 5:
            printf("Maio - 31\n");
            break;
        case 6:
            printf("Junho - 30\n");
            break;
        case 7:
            printf("Julho - 31\n");
            break;
        case 8:
            printf("Agosto - 31\n");
            break;
        case 9:
            printf("Setembro - 30\n");
            break;
        case 10:
            printf("Outubro - 31\n");
            break;
        case 11:
            printf("Novembro - 30\n");
            break;
        case 12:
            printf("Dezembro - 31\n");
            break;
        default:
        printf("Mês Invalido\n");
            break;
    }
}
#include <stdio.h>
#include <stdlib.h>

int main()
{
    int ano;
    char deseja;
    do
    {

        printf("Digite o ano\n");
        scanf("%d", &ano);
        if (ano % 4 == 0 && ano % 100 != 0)
        {
            printf("O ano é bissexto \n");
        }
        else if (ano % 4 == 0 && ano % 100 == 0 && ano % 400 == 0)
        {
            printf("O ano é bissexto \n");
        }
        else
        {
            printf("O ano não é bissexto \n");
        }

        printf("\n");
        printf("Deseja consultar novamente?(s/n)\n");
        scanf("%s", &deseja);

    } while (deseja != 'n');
}
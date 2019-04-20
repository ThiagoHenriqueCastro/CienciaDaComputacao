#include <stdlib.h>
#include <stdio.h>

int main()
{
    int maiorProduzida = 0, maiorDefeituosa = 0, produzida = 0, defeituosa = 0;

    for (int i = 0; i < 2; i++)
    {
        do
        {
            printf("\nDigite o numero de pecas produzidas: ");
            scanf("%i", &produzida);
            if (produzida < 1)
            {
                printf("\nNumero invalido");
            }

        } while (produzida < 1);

        do
        {
            printf("\nDigite o numero de pecas defeituosas: ");
            scanf("%i", &defeituosa);
            if (defeituosa < 1)
            {
                printf("\nNumero invalido");
            }

        } while (defeituosa < 1);

        if (produzida > maiorProduzida)
        {
            maiorProduzida = produzida;
            maiorDefeituosa = defeituosa;
        }
    }
    printf("A maquina mais produtiva produziu %i pecas, sendo %i defeituosas.\n", maiorProduzida, maiorDefeituosa);
}
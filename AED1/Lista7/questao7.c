#include <stdlib.h>
#include <stdio.h>

int main()
{
    int n = 0, idade, maior = INT32_MIN, menor = INT32_MAX, adultos, i = 0;
    float adultos_porc;

    for (i = 1; i != 0; i = i)
    {

        do
        {
            printf("Digite a sua idade: ");
            scanf("%d", &idade);

            if (idade > maior && idade <= 100 & idade >= 1)
            {
                maior = idade;
            }
            if (idade < menor && idade <= 100 & idade >= 1)
            {
                menor = idade;
            }
            if (idade > 18 && idade <= 100 & idade >= 1)
            {
                adultos++;
            }
            if (idade == 0)
            {
                i = 0;
            }
            if (idade <= 100 & idade >= 1 && idade != 0)
            {
                n++;
            }

        } while ((idade < 1 || idade > 100) && idade != 0);
    }

    adultos_porc = ((double)adultos / (double)n) * 100;
    printf("Maior idade : %d\n", maior);
    printf("Menor idade : %d\n", menor);
    printf("Porcentagem de maiores de idade : %.2f\n", adultos_porc);
}
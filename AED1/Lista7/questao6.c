#include <stdlib.h>
#include <stdio.h>

int main()
{
    int n, idade, maior = INT32_MIN, menor = INT32_MAX, adultos, i = 0;
    float adultos_porc;

    printf("Digite o numero de alunos: ");
    scanf("%d", &n);

    for (i = 0; i < n; i++)
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

        } while (idade < 1 || idade > 100);
    }

    adultos_porc = ((double)adultos / (double)n) * 100;
    printf("Maior idade : %d\n", maior);
    printf("Menor idade : %d\n", menor);
    printf("Porcentagem de maiores de idade : %.2f\n", adultos_porc);
}
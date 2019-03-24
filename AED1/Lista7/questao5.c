#include <stdlib.h>
#include <stdio.h>

int main()
{

    int num = 0, soma = 0, n = 0, maior = INT32_MIN, media = 0;
    do
    {
        printf("Digite o numero: ");
        scanf("%d", &num);
        soma += num;
        if (num != 0)
        {
            n++;
        }

        if (num > maior)
        {
            maior = num;
        }
    } while (num != 0);

    media = soma / n;

    printf("Maior: %d\n", maior);
    printf("Media: %d\n", media);
}
#include <stdio.h>
#include <stdlib.h>

int main()
{
    float n, maior = INT64_MIN, menor = INT64_MAX;

    for (int i = 1; i <= 10; i++)
    {
        printf("Digite o numero de termo %d\n", i);
        scanf("%f", &n);

        if (n > maior)
        {
            maior = n;
        }
        if (n < menor)
        {
            menor = n;
        }
    }

    printf("O maior numero é %.2f e o menor é %.2f\n", maior, menor);
}
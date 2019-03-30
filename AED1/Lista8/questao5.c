#include <stdlib.h>
#include <stdio.h>

int main()
{
    int serie[100], a;

    printf("Digite o valor do primeiro termo: ");
    scanf("%i", &serie[0]);
    printf("\nDigite o valor do segundo termo: ");
    scanf("%i", &serie[1]);
    printf("\n%i", serie[0]);
    printf("\n%i", serie[1]);
    for (int i = 2; i <= 100; i++)
    {
        if ((i + 1) % 2 == 0)
        {
            a = serie[i - 1] - serie[i - 2];
        }
        else
        {
            a = serie[i - 1] + serie[i - 2];
        }
        serie[i] = a;

        printf("\n%i", a);
    }
}
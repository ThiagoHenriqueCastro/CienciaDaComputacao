#include <stdlib.h>
#include <stdio.h>

int main()
{
    int num = 0;
    do
    {
        int fatorial = 1;
        printf("Digite o numero desejado: ");
        scanf("%i", &num);

        if (num == 0)
        {
            fatorial = 1;
        }
        else
        {
            for (int i = num; i >= 1; i--)
            {
                fatorial *= i;
            }
        }

        printf("\nFatorial de %i é igual a: %i\n", num, fatorial);

    } while (num >= 0);
}
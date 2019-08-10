#include <stdlib.h>
#include <stdio.h>
int potencia(int base, int expoente);
int main()
{
    printf("%i\n", potencia(2, 4));
}

int potencia(int base, int expoente)
{
    if (base == 1 | expoente == 0)
    {
        return 1;
    }
    else
    {
        if (expoente == 1)
        {
            return base;
        }
        else
        {
            return base * potencia(base, expoente - 1);
        }
    }
}
#include <stdlib.h>
#include <stdio.h>
#include <math.h>

int somaCubo(int n);

int main()
{
    printf("%i\n", somaCubo(2));
}
int somacubo(int n)
{
    int retorno;
    if (n == 1)
    {
        retorno = 1;
    }
    else
    {
        retorno = pow((double)n, 3) + somaCubo(n - 1);
    }

    return retorno;
}
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int somaCubo(int n);
int main()
{
    printf("%i\n", somaCubo(3));
}

int somaCubo(int n)
{
    if (n == 1)
    {
        return 1;
    }
    else
    {
        return (pow(n, 3) + somaCubo(n - 1));
    }
}

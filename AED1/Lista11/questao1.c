#include <stdlib.h>
#include <stdio.h>
void sequencia(int n);
int main()
{
    sequencia(10);
}

void sequencia(int n)
{
    if (n > 0)
    {
        sequencia(n - 1);
        printf("%i\n", n);
    }
}
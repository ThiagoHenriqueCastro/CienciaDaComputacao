#include <stdlib.h>
#include <stdio.h>
void sequencia(int n);
int main()
{
    sequencia(1);
}
void sequencia(int n)
{
    if (n <= 5)
    {
        printf("%i ", n);
        sequencia(n + 1);
        printf("%i ", n);
    }
}
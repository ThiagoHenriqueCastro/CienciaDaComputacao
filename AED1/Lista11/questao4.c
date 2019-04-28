#include <stdlib.h>
#include <stdio.h>
void sequencia(int n);
int main()
{
    sequencia(20);
}
void sequencia(int n)
{
    if (n >= 0)
    {
        printf("%i ", n);
        sequencia(n - 1);
        }
}
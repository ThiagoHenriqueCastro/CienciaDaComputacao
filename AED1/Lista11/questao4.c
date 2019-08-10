#include <stdio.h>
#include <stdlib.h>
void sequencia(int n);
int main()
{
    sequencia(15);
}
void sequencia(int n)
{
    if (n >= 0)
    {
        printf("%i\n", n);
        sequencia(n - 1);
    }
}
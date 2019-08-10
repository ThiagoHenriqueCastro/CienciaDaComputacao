#include <stdio.h>
#include <stdlib.h>
int soma(int n, int m);
int main()
{
    printf("%i\n", soma(2, 4));
}
int soma(int n, int m)
{
    int somatorio = 0;
    if (n == m)
    {
        return m;
    }
    else
    {
        return n + soma(n + 1, m);
    }
}
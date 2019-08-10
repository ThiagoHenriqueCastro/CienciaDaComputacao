#include <stdio.h>
#include <stdlib.h>
int fatorial(int n);
int main()
{
    printf("%i\n", fatorial(5));
}
int fatorial(int n)
{
    if (n == 0 | n == 1)
    {
        return 1;
    }
    else
    {
        return n * fatorial(n - 1);
    }
}
#include <stdio.h>
#include <stdlib.h>
int calcFatorial(int n);
int main()
{
    printf("%i\n", calcFatorial(3));
}

int calcFatorial(int n)
{
    if (n <= 1)
    {
        return 1;
    }
    else
    {
        return n * calcFatorial(n - 1);
    }
}
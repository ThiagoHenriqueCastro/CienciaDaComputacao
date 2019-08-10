#include <stdio.h>
#include <stdlib.h>
int fibonacci(int n);
int main()
{
    printf("%i\n", fibonacci(6));
}
int fibonacci(int n)
{
    if (n == 1 | n == 2)
    {
        return 1;
    }
    else
    {
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
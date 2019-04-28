#include <stdlib.h>
#include <stdio.h>
#include <math.h>
void calcFibonacci(int n);
int a = 1;
int b = 1;
int main()
{
    calcFibonacci(5);
}
void calcFibonacci(int n)
{
    if (n > 0)
    {
        printf("%i ", a);
        printf("%i ", b);
        a += b;
        b += a;
        calcFibonacci(n - 1);
    }
    
}
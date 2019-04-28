#include <stdlib.h>
#include <stdio.h>
#include <math.h>
int calcFatorial(int n);
int fatorial = 1;
int main()
{
    int fatorial = calcFatorial(6);
    printf("%i \n", fatorial);
}
int calcFatorial(int n)
{
    if (n > 0)
    {
        fatorial *= n;
        calcFatorial(n - 1);
    }
    return fatorial;
    
}
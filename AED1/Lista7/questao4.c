#include <stdlib.h>
#include <stdio.h>
#include <math.h>

int main()
{

    int a = 1, b = 1, n;
    printf("Digite o numero de termos da progressao:\n");
    scanf("%d", &n);
    printf("\n");
    for (int i = 0; i < (n/2); i++)
    {   
        printf("%d\n", a);
        printf("%d\n", b);
        a += b;
        b += a;
     
    }
}
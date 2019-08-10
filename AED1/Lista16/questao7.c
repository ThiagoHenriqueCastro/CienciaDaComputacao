#include <stdio.h>
#include <stdlib.h>
#define MAX 6
int isNegativo(float array[]);
int main()
{
    float num[MAX] = {-1, 5, 6, -5, -7, 8};
    printf("%i\n", isNegativo(num));
}

int isNegativo(float array[])
{
    int x = 0;
    for (int i = 0; i < MAX; i++)
    {
        if (array[i] < 0)
        {
            x++;
        }
    }
    return x;
}
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
const int MAX = 3;
bool isPresente(float array[], float num);
int main()
{
    float reais[MAX] = {1.2, 5.4, 5.4};
    if (isPresente(reais, 5.4) == true)
    {
        printf("True\n");
    }
    else
    {
        printf("False\n");
    }
}

bool isPresente(float array[], float num)
{
    bool retorno = false;
    for (int i = 0; i < MAX; i++)
    {
        if (array[i] == num)
        {

            retorno = true;
        }
    }
    return retorno;
}
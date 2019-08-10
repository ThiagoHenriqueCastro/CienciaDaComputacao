#include <stdlib.h>
#include <stdio.h>
#define MAX 10
void fibonacci(int array[]);
int main()
{
    int fibo[MAX];
    fibo[0] = 1;
    fibo[1] = 1;
    printf("%i\n%i\n", fibo[0], fibo[1]);
    fibonacci(fibo);
}
void fibonacci(int array[])
{
    int fibo = 0;
    for (int i = 2; i < MAX; i++)
    {
        array[i] = array[i - 1] + array[i - 2];
        printf("%i\n", array[i]);
    }
}

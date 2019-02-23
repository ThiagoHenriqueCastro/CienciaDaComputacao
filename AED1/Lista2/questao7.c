#include <stdio.h>
#include <stdlib.h>

int main()
{
    float fahrenheit;
    printf("Conversor Farenheit-Celsius\n");
    printf("Digite a temperatura, em fahrenheit\n");
    scanf("%f", &fahrenheit);
    float celsius = 5.0/9.0 * (fahrenheit - 32);

    printf("A temperatura em Celsius é %.2f\n", celsius);
}
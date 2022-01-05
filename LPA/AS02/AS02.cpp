/**
 * AS02 - STRINGS
 * @author THIAGO HENRIQUE DE CASTRO OLIVEIRA
 * @version 1 08/2021
 * COMPLEXIDADE O(N) onde N é o numero de consultas na tabela de leds
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int led_table[] = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

/**
 *  Recebe uma string e, para cada digito, consulta a tabela de leds e soma no total de digitos
 * 
 * @param entry numero no painel a ser calculado
 * */
void _calcLeds(char *entry)
{
    int leds = 0;
    for (int i = 0; i < strlen(entry); i++)
    {
        leds += led_table[entry[i] - '0'];
    }

    printf("%d leds\n", leds);
}

int main()
{
    int entries;
    scanf("%d", &entries);

    char *entry;
    for (int i = 0; i < entries; i++)
    {
        scanf("%s", entry);
        _calcLeds(entry);
    }
}

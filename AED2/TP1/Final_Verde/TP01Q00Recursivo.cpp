/**
 * TP01Q00 Aquecimento
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Este algoritmo conta o numero de letras maiusculas em uma
 *          string.
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define LINE 1000
#define ENTRY 1000

#define false 0
#define true 1
#define boolean short

/**
     * 
     * @param line string que devera ser verificada
     * @return true se 'FIM' for encontrado
     */
boolean isFim(char *line)
{
    boolean resp = false;
    if (strlen(line) >= 3 && line[0] == 'F' && line[1] == 'I' && line[2] == 'M')
        resp = true;

    return resp;
}
<<<<<<< HEAD:AED2/TP01Q00Aquecimento/TP01Q00Recursivo.c
/**
     * 
     * @param letter letra a ser verificada
     * @return se for maisculo, retorna 1 para somar posteriormente
=======

/**
     * 
     * @param letter letra a ser verificada
     * @return se for maisculo, retorna true
>>>>>>> 6aa113d863187424cf64a8246ccac44f10208186:AED2/Final_Verde/TP01Q00Recursivo.cpp
     */
boolean isUpper(char letter)
{
    boolean resp = false;
    if (letter >= 'A' && letter <= 'Z')
        resp = true;

    return resp;
}
<<<<<<< HEAD:AED2/TP01Q00Aquecimento/TP01Q00Recursivo.c
=======

>>>>>>> 6aa113d863187424cf64a8246ccac44f10208186:AED2/Final_Verde/TP01Q00Recursivo.cpp
/**
     * 
     * @param line string a ser verificada
     * @return o numero de letras maisculas
     */
int countUpper(char *line, int i)
{
    int result = 0;
    if (i == strlen(line))
    {
        result = 0;
    }
    else if (isUpper(line[i]))
        result = 1 + countUpper(line, i + 1);
    else
        result = 0 + countUpper(line, i + 1);

    return result;
}

int main(int argc, char const *argv[])
{
    char input[ENTRY][LINE]; // array onde são armazenadas as strings
    int inputIndex = 0;      // controlador dos index para iteraçao

    // PREENCHIMENTO DO ARRAY
    do
    {
        fgets(input[inputIndex], LINE, stdin);
    } while (isFim(input[inputIndex++]) == false);
    inputIndex--;

    for (int i = 0; i < inputIndex; i++)
    {
        printf("%i\n", countUpper(input[i], 0));
    }
}
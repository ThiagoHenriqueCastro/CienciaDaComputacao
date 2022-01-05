/**
 * AS01 - MATEMÁTICA
 * @author THIAGO HENRIQUE DE CASTRO OLIVEIRA
 * @version 1 08/2021
 */

#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <string.h>

long _fatorial(int n)
{
    // BASE
    if (n == 1 || n == 0)
        return 1;
    return _fatorial(n - 1) * n;
}

void _calcFatorial(char *line)
{
    // SPLIT VARIABLES
    int i = 0;
    char *split = strtok(line, " ");
    char *toCalc[2];

    // RUN THROUGH THE SPLIT STRING TO GET ALL TOKENS
    // THE FUNCTION STRTOK RETURNS THE FIRST SPLIT TOKEN
    // TO GET ALL TOKENS, IT MUST RUN IN A LOOP AS SHOWN BELOW
    // ON THE FIRST CALL, STRTOK EXPECTS THE STRING
    // ON SUBSEQUENT CALLS, IT EXPECTS A NULL POINTER AND USES THE POSITION RIGHT AFTER THE END OF THE LAST GIVEN TOKEN
    // THE LOOP RUNS UNTIL NULL
    while (split != NULL)
    {
        toCalc[i++] = split;
        split = strtok(NULL, " ");
    }

    // CONVERTS THE STRING TO INT AND RUNS THE FATORIAL METHOD WITH EACH NUMBER
    long result = _fatorial(atoi(toCalc[0])) + _fatorial(atoi(toCalc[1]));

    printf("%lu\n", result);
}

int main()
{
    // INPUT VARIABLES
    char *line = NULL;
    size_t len = 0;
    ssize_t read = 0;

    // READS THE STDIN UNTIL THE END OF INPUTS
    while (1)
    {
        read = getline(&line, &len, stdin);
        if (read == -1)
            break;

        // CALL TO FUNCTION PASSING THE ENTIRE LINE
        _calcFatorial(line);
    }
}

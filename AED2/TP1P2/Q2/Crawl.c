#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define LINE 1000
#define ENTRY 1000
#define HTML 10000

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

void crawlNome(char *line, char *nome)
{
    int begin = 0;
    int end = 0;
    char *output = "";
    for (int i = 0; i < strlen(line); i++)
    {
        if (line[i] == 'F' && line[i + 1] == 'u' && line[i + 2] == 'l' && line[i + 3] == 'l' && line[i + 4] == ' ' && line[i + 5] == 'n' && line[i + 6] == 'a' && line[i + 7] == 'm' && line[i + 8] == 'e')
        {
            begin = i + 9;
            i = strlen(line);
        }
    }

    for (int i = 0; i < strlen(line); i++)
    {
        if (line[i] == 'N' && line[i + 1] == 'i' && line[i + 2] == 'c' && line[i + 3] == 'k' && line[i + 4] == 'n' && line[i + 5] == 'a' && line[i + 6] == 'm' && line[i + 7] == 'e')
        {
            end = i - 1;
            i = strlen(line);
        }
    }

    int cont = 0;
    for (int i = begin; i <= end; i++)
    {
        nome[cont++] = line[i];
    }
}

void crawlApelido(char *line, char *apelido)
{
    int begin = 0;
    int end = 0;
    char *output = "";
    for (int i = 0; i < strlen(line); i++)
    {
        if (line[i] == 'N' && line[i + 1] == 'i' && line[i + 2] == 'c' && line[i + 3] == 'k' && line[i + 4] == 'n' && line[i + 5] == 'a' && line[i + 6] == 'm' && line[i + 7] == 'e')
        {
            begin = i + 11;
            i = strlen(line);
        }
    }

    if (strstr(line, "Short name") != NULL)
    {
        for (int i = 0; i < strlen(line); i++)
        {
            if (line[i] == 'S' && line[i + 1] == 'h' && line[i + 2] == 'o' && line[i + 3] == 'r' && line[i + 4] == 't' && line[i + 5] == ' ' && line[i + 6] == 'n' && line[i + 7] == 'a')
            {
                end = i - 1;
                i = strlen(line);
            }
        }
    }
    else
    {

        for (int i = 0; i < strlen(line); i++)
        {
            if (line[i] == 'F' && line[i + 1] == 'o' && line[i + 2] == 'u' && line[i + 3] == 'n' && line[i + 4] == 'd' && line[i + 5] == 'e' && line[i + 6] == 'd')
            {
                end = i - 1;
                i = strlen(line);
            }
        }
    }

    int cont = 0;
    for (int i = begin; i <= end; i++)
    {
        apelido[cont++] = line[i];
    }
}

int crawlDia(char *line)
{
    int begin = 0;
    int end = 0;
    char output[10000];
    char dia[50];
    for (int i = 0; i < strlen(line); i++)
    {
        if (line[i] == 'F' && line[i + 1] == 'o' && line[i + 2] == 'u' && line[i + 3] == 'n' && line[i + 4] == 'd' && line[i + 5] == 'e' && line[i + 6] == 'd')
        {
            begin = i + 7;
            i = strlen(line);
        }
    }
    int cont = 0;
    for (int i = begin; i < strlen(line); i++)
    {
        output[cont++] = line[i];
    }

    for (int i = 0; i < strlen(output); i++)
    {
        if (output[i] == ' ')
        {
            end = i - 1;
            i = strlen(line);
        }
    }

    for (int i = 0; i <= end; i++)
    {
        dia[i] = output[i];
    }

    /*
    if(isalpha(dia[0])){

    }
    */
    int out;
    if (strstr(line, "January") || strstr(line, "February") || strstr(line, "March") || strstr(line, "April") || strstr(line, "May") || strstr(line, "June") || strstr(line, "July") || strstr(line, "September") || strstr(line, "August") || strstr(line, "October") || strstr(line, "November") || strstr(line, "July") || strstr(line, "December"))
    {
        out = atoi(dia);
    }
    else
    {
        out = 0;
    }

    return out;
}

int crawlMes(char *line)
{
    int begin = 0;
    int end = 0;
    char output[10000];
    char dia[50];
    for (int i = 0; i < strlen(line); i++)
    {
        if (line[i] == 'F' && line[i + 1] == 'o' && line[i + 2] == 'u' && line[i + 3] == 'n' && line[i + 4] == 'd' && line[i + 5] == 'e' && line[i + 6] == 'd')
        {
            begin = i + 7;
            i = strlen(line);
        }
    }
    int cont = 0;
    for (int i = begin; i < strlen(line); i++)
    {
        output[cont++] = line[i];
    }

    for (int i = 0; i < strlen(output); i++)
    {
        if (output[i] == ' ')
        {
            end = i - 1;
            i = strlen(line);
        }
    }

    for (int i = 0; i <= end; i++)
    {
        dia[i] = output[i];
    }

    /*
    if(isalpha(dia[0])){

    }
    */
    int out;
    if (strstr(line, "January") || strstr(line, "February") || strstr(line, "March") || strstr(line, "April") || strstr(line, "May") || strstr(line, "June") || strstr(line, "July") || strstr(line, "September") || strstr(line, "August") || strstr(line, "October") || strstr(line, "November") || strstr(line, "July") || strstr(line, "December"))
    {
        out = atoi(dia);
    }
    else
    {
        out = 0;
    }

    return out;
}

int crawlAno(char *line)
{
    int begin = 0;
    int end = 0;
    char output[10000];
    char ano[50];
    for (int i = 0; i < strlen(line); i++)
    {
        if (line[i] == 'F' && line[i + 1] == 'o' && line[i + 2] == 'u' && line[i + 3] == 'n' && line[i + 4] == 'd' && line[i + 5] == 'e' && line[i + 6] == 'd')
        {
            begin = i + 7;
            i = strlen(line);
        }
    }
    int cont = 0;
    for (int i = begin; i < strlen(line); i++)
    {
        output[cont++] = line[i];
    }

    for (int i = 0; i < strlen(output); i++)
    {
        if (output[i] == ' ')
        {
            end = i - 1;
            i = strlen(line);
        }
    }

    for (int i = 0; i <= end; i++)
    {
        ano[i] = output[i];
    }

    /*
    if(isalpha(dia[0])){

    }
    */
    int out;
    if (strstr(line, "January") || strstr(line, "February") || strstr(line, "March") || strstr(line, "April") || strstr(line, "May") || strstr(line, "June") || strstr(line, "July") || strstr(line, "September") || strstr(line, "August") || strstr(line, "October") || strstr(line, "November") || strstr(line, "July") || strstr(line, "December"))
    {
        out = atoi(ano);
    }
    else
    {
        out = 0;
    }

    return out;
}


int main(int argc, char const *argv[])
{
    char input[ENTRY][LINE];
    int inputIndex = 0;

    do
    {
        fgets(input[inputIndex], LINE, stdin);
    } while (isFim(input[inputIndex++]) == false);
    inputIndex--;

    FILE *arq;
    char *html = NULL;
    size_t len = 0;
    char nome[100];
    char apelido[100];
    int dia;
    int mes;
    int ano;
    for (int i = 0; i < inputIndex; i++)
    {
        arq = fopen(input[i], "r");
        if (arq == NULL)
        {
            perror(input[i]);
            return (-1);
        }
        int line_len;
        while (!feof(arq))
        {
            getline(&html, &len, arq);
            if (strstr(html, "Full name") != NULL)
            {
                int idx = 0;
                int opened = 0; // false
                for (i = 0; i < strlen(html); i++)
                {
                    if (html[i] == '<')
                    {
                        opened = 1; // true
                    }
                    else if (html[i] == '>')
                    {
                        opened = 0; // false
                    }
                    else if (!opened)
                    {
                        html[idx++] = html[i];
                    }
                }
                html[idx] = '\0';

                for (int i = 0; i < strlen(html); i++)
                {
                    if (html[i] == '#' || html[i] == '&')
                    {
                        html[i] = ' ';
                        html[i + 1] = ' ';
                        html[i + 2] = ' ';
                        html[i + 3] = ' ';
                        html[i + 4] = ' ';
                    }
                }

                crawlNome(html, nome);
                crawlApelido(html, apelido);
                dia = crawlDia(html);
                mes = crawlMes(html);
                //  ano = crawlAno();
                printf("%s\n", nome);
                printf("%s\n", apelido);
                printf("%d\n", dia);
                printf("%d\n", mes);
                // printf("%d\n", ano);
            }
        }
    }
}
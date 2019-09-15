#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <locale.h>

#define LINE 1000
#define ENTRY 1000
#define HTML 10000

#define false 0
#define true 1
#define boolean short

typedef struct Time
{
    char nome[200];
    char apelido[200];
    char estadio[100];
    char tecnico[100];
    char liga[100];
    char nomeArquivo[100];
    int capacidade;
    int fundacaoDia;
    int fundacaoMes;
    int fundacaoAno;
    long paginaTam;
} Time;

typedef struct Lista
{
    Time array[1000];
    int n;

} Lista;

boolean isFim(char *line)
{
    boolean resp = false;
    if (strlen(line) >= 3 && line[0] == 'F' && line[1] == 'I' && line[2] == 'M')
        resp = true;

    return resp;
}

char *multi_tok(char *input, char *delimiter)
{
    static char *string;
    if (input != NULL)
        string = input;

    if (string == NULL)
        return string;

    char *end = strstr(string, delimiter);
    if (end == NULL)
    {
        char *temp = string;
        string = NULL;
        return temp;
    }

    char *temp = string;

    *end = '\0';
    string = end + strlen(delimiter);
    return temp;
}

char *removeTags(char *input)
{
    int idx = 0;
    int opened = 0; // false
    for (int i = 0; i < strlen(input); i++)
    {
        if (input[i] == '<')
        {
            opened = 1; // true
        }
        else if (input[i] == '>')
        {
            opened = 0; // false
        }
        else if (!opened)
        {
            input[idx++] = input[i];
        }
    }
    input[idx] = '\0';

    return input;
}

char *subString(const char *input, int offset, char *dest)
{
    int input_len = strlen(input);

    int len = input_len - offset;

    strncpy(dest, input + offset, len);
    return dest;
}

void handle_liga(char *input)
{
    for (int i = 0; i < strlen(input); i++)
    {
        if (isdigit(input[i]))
        {
            input[i] = '\0';
            i = strlen(input);
        }
        if (input[i] == '\n')
        {
            input[i] = '\0';
            i = strlen(input);
        }
    }

    input[strlen(input)] = 0;
}

char *subString_delimiter(const char *input, int offset, int len, char *dest)
{
    int input_len = strlen(input);

    strncpy(dest, input + offset, len);
    return dest;
}

int getMes(char *campo)
{
    int resp = 0;
    if (strstr(campo, "January") != NULL)
    {
        resp = 1;
    }
    else if (strstr(campo, "February") != NULL)
    {
        resp = 2;
    }
    else if (strstr(campo, "March") != NULL)
    {
        resp = 3;
    }
    else if (strstr(campo, "April") != NULL)
    {
        resp = 4;
    }
    else if (strstr(campo, "May") != NULL)
    {
        resp = 5;
    }
    else if (strstr(campo, "June") != NULL)
    {
        resp = 6;
    }
    else if (strstr(campo, "July") != NULL)
    {
        resp = 7;
    }
    else if (strstr(campo, "August") != NULL)
    {
        resp = 8;
    }
    else if (strstr(campo, "September") != NULL)
    {
        resp = 9;
    }
    else if (strstr(campo, "October") != NULL)
    {
        resp = 10;
    }
    else if (strstr(campo, "November") != NULL)
    {
        resp = 11;
    }
    else if (strstr(campo, "December") != NULL)
    {
        resp = 12;
    }
    return resp;
}

void imprimir(Time *time)
{
    printf("%s ## %s ## %d/%d/%d ## %s ## %d ## %s ## %s ## %s ## %lu ##\n", time->nome, time->apelido, time->fundacaoDia, time->fundacaoMes, time->fundacaoAno, time->estadio, time->capacidade, time->tecnico, time->liga, time->nomeArquivo, time->paginaTam);
}

int ler(char *path, Time *time)
{
    //printf("%s\n", path);
    FILE *arq;
    arq = fopen(path, "r");
    char nome[200] = {'\0'};
    char apelido[70] = {'\0'};
    char date_rcv[25] = {'\0'};
    int mes;
    int dia;
    int ano;
    char ano_rcv[15] = {'\0'};
    char estadio[100] = {'\0'};
    char capacity_rcv[100] = {'\0'};
    int capacidade;
    char liga[100] = {'\0'};

    if (arq == NULL)
    {
        perror(path);
        return (-1);
    }
    char *html;
    char *line;
    size_t len = 0;
    while (!feof(arq))
    {
        getline(&html, &len, arq);

        if (strstr(html, "Full name") != NULL)
        {

            line = html;

            char *resp = strstr(line, "Full name");

            char *token = multi_tok(resp, "<tr>");

            char *campos[100];
            for (int i = 0; i < 100; i++)
            {
                campos[i] = "0";
            }
            int contador = 0;
            while (token != NULL)
            {
                campos[contador++] = token;
                token = multi_tok(NULL, "<tr>");
            }

            contador = 0;
            //

            do
            {
                if (strstr(campos[contador], "Full name") != NULL)
                {
                    campos[contador] = removeTags(campos[contador]);
                    // printf("%s\n", campos[contador]);
                    subString(campos[contador], 9, nome);
                    // printf("%s\n", nome);
                    strcpy((time)->nome, nome);

                    // printf("%s\n", time->nome);
                }
                else if (strstr(campos[contador], "Nickname(s)") != NULL)
                {

                    campos[contador] = removeTags(campos[contador]);
                    //printf("%s\n", campos[contador]);
                    subString(campos[contador], 11, apelido);
                    // printf("%s\n", apelido);
                    strcpy((time)->apelido, apelido);
                    // printf("%s\n", time->apelido);
                }
                else if (strstr(campos[contador], "Founded") != NULL)
                {

                    campos[contador] = removeTags(campos[contador]);

                    mes = getMes(campos[contador]);
                    if (mes == 0)
                    {
                        dia = 0;
                        subString_delimiter(campos[contador], 7, 4, ano_rcv);
                        ano = atoi(ano_rcv);
                    }
                    else
                    {
                        subString(campos[contador], 7, date_rcv);

                        char *split = multi_tok(date_rcv, " ");

                        char *splitted[3];
                        for (int i = 0; i < 3; i++)
                        {
                            splitted[i] = "0";
                        }
                        int founded_index = 0;
                        while (split != NULL)
                        {
                            splitted[founded_index++] = split;
                            split = multi_tok(NULL, " ");
                        }

                        char *test = splitted[0];

                        if (strstr(splitted[0], "January") != NULL || strstr(splitted[0], "February") != NULL || strstr(splitted[0], "March") != NULL || strstr(splitted[0], "April") != NULL || strstr(splitted[0], "May") != NULL || strstr(splitted[0], "June") != NULL || strstr(splitted[0], "July") != NULL || strstr(splitted[0], "August") != NULL || strstr(splitted[0], "September") != NULL || strstr(splitted[0], "October") != NULL || strstr(splitted[0], "November") != NULL || strstr(splitted[0], "December") != NULL)
                        {
                            if (strstr(campos[contador], ","))
                            {
                                dia = atoi(splitted[1]);
                                subString_delimiter(splitted[2], 0, 4, ano_rcv);
                                ano = atoi(ano_rcv);
                            }
                            else if (isdigit(test[0]))
                            {

                                dia = atoi(test);
                                subString_delimiter(splitted[1], 0, 4, ano_rcv);
                                ano = atoi(ano_rcv);
                            }
                            else
                            {

                                dia = 0;
                                subString_delimiter(splitted[1], 0, 4, ano_rcv);
                                ano = atoi(ano_rcv);
                            }
                        }
                        else
                        {
                            dia = 0;
                            subString_delimiter(splitted[1], 0, 4, ano_rcv);
                            ano = atoi(ano_rcv);
                        }
                    }
                    (time)->fundacaoMes = mes;
                    (time)->fundacaoDia = dia;
                    (time)->fundacaoAno = ano;
                    // printf("%d\n", mes);
                    // printf("%d\n", dia);
                    // printf("%d\n", ano);
                }

                else if (strstr(campos[contador], "Ground") != NULL)
                {
                    campos[contador] = removeTags(campos[contador]);

                    subString(campos[contador], 6, estadio);
                    strcpy((time)->estadio, estadio);
                    // printf("%s\n", time->estadio);
                }
                else if (strstr(campos[contador], "Capacity") != NULL)
                {

                    campos[contador] = removeTags(campos[contador]);

                    subString(campos[contador], 8, capacity_rcv);
                    char *split = multi_tok(capacity_rcv, "&");

                    char *splitted[50];
                    for (int i = 0; i < 50; i++)
                    {
                        splitted[i] = "0";
                    }
                    int founded_index = 0;
                    while (split != NULL)
                    {
                        splitted[founded_index++] = split;
                        split = multi_tok(NULL, " ");
                    }

                    capacidade = atoi(splitted[0]);
                    (time)->capacidade = capacidade;
                    //  printf("%d\n", time->capacidade);
                }
                else if (strstr(campos[contador], "Coach") != NULL || strstr(campos[contador], "Manager") != NULL)
                {
                    campos[contador] = removeTags(campos[contador]);

                    char *tecnico = strstr(campos[contador], "Coach") != NULL ? multi_tok(campos[contador], "Coach") : multi_tok(campos[contador], "Manager");
                    tecnico = strstr(campos[contador], "Coach") != NULL ? multi_tok(NULL, "Coach") : multi_tok(NULL, "Manager");
                    strcpy((time)->tecnico, tecnico);
                    // printf("%s\n", time->tecnico);
                }

                else if (strstr(campos[contador], "League") != NULL)
                {
                    campos[contador] = removeTags(campos[contador]);
                    subString(campos[contador], 6, liga);
                    handle_liga(liga);
                    strcpy((time)->liga, liga);
                    // printf("%s\n", liga);
                }

            } while (strcmp(campos[contador++], "0"));
            strcpy(time->nomeArquivo, path);
            fseek(arq, 0L, SEEK_SET);
            fseek(arq, 0L, SEEK_END);
            int sz = ftell(arq);

            time->paginaTam = sz;
        }
    }
    return 1;
    fclose(arq);
}

int inserirFim(Lista lista[], Time *time)
{
    if (lista->n > 1000)
    {
        perror("Erro ao inserir");
        return (-1);
    }
    lista->array[lista->n] = *time;
    lista->n++;

    return 1;
}

int inserirInicio(Lista lista[], Time *time)
{
    if (lista->n > 1000)
    {
        perror("Erro ao inserir");
        return (-1);
    }

    for (int i = lista->n; i > 0; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[0] = *time;

    lista->n++;

    return 1;
}

int inserir(Lista lista[], Time *time, int pos)
{
    if (lista->n > 1000)
    {
        perror("Erro ao inserir");
        return (-1);
    }

    for (int i = lista->n; i > pos; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[pos] = *time;

    lista->n++;

    return 1;
}

char *removerFim(Lista lista[])
{
    if (lista->n <= 0)
    {
        perror("Erro ao remover");
    }

    char *resp = lista->array[lista->n - 1].nome;

    lista->n--;

    return resp;
}

char *removerInicio(Lista lista[])
{
    if (lista->n <= 0)
    {
        perror("Erro ao remover");
    }

    char *resp = lista->array[0].nome;

    for (int i = 0; i < lista->n; i++)
    {
        lista->array[i] = lista->array[i + 1];
    }

    lista->n--;

    return resp;
}

char *remover(Lista lista[], int pos)
{
    if (lista->n <= 0)
    {
        perror("Erro ao remover");
    }

    char *resp = lista->array[pos].nome;

    for (int i = pos; i < lista->n; i++)
    {
        lista->array[i] = lista->array[i + 1];
    }

    lista->n--;

    return resp;
}

void mostrar(Lista *lista)
{
    for (int i = 0; i < lista->n; i++)
    {
        printf("[%d] ", i);
        imprimir(&lista->array[i]);
    }
}

int binarySearch(char *chave, Lista lista[], int inicio, int fim)
{
    int valorChave = 0;
    int valorLista = 0;
    char *aux;

    //printf("%d e %d\n", inicio, fim);

    aux = (char *)malloc(200 * sizeof(char));

    if (inicio > fim)
    {
        return 0;
    }
    int meio = (inicio + fim) / 2;

    strcpy(aux, lista->array[meio].nome);

    //printf("%s\n", lista->array[meio].nome);
    //printf("%s\n", chave);
    // printf("%lu\n", strlen(aux));
    // printf("%lu\n", strlen(chave));

    if (strcmp(aux, chave) == 0)
    {

        return 1;
    }
    else
    {
        if (strcmp(lista->array[meio].nome, chave) < 0)
        {
            return binarySearch(chave, lista, meio + 1, fim);
        }
        else
        {
            return binarySearch(chave, lista, inicio, meio - 1);
        }
    }

    free(aux);
}

int main(int argc, char const *argv[])
{

    char input[ENTRY][LINE];
    int inputIndex = 0;

    Time *time;
    Lista *lista = (Lista *)malloc(sizeof(Lista));
    lista->n = 0;

    do
    {

        fgets(input[inputIndex], LINE, stdin);
    } while (isFim(input[inputIndex++]) == false);
    inputIndex--;
    char *newline;
    for (int i = 0; i < inputIndex; i++)
    {
        newline = strchr(input[i], '\n');
        if (newline)
            *newline = 0;
        time = (Time *)malloc(sizeof(Time));
        if (time == NULL)
        {
            return (-1);
        }

        ler(input[i], time);
        // imprimir(time);
        inserirFim(lista, time);

        free(time);
    }

    char chave[ENTRY][LINE];
    int chaveIndex = 0;

    do
    {

        fgets(chave[chaveIndex], LINE, stdin);
    } while (isFim(chave[chaveIndex++]) == false);
    chaveIndex--;
    int result = 0;
    char *correctKey;
    for (int i = 0; i < chaveIndex; i++)
    {
        correctKey = strchr(chave[i], '\n');
        if (correctKey)
            *correctKey = 0;
        result = binarySearch(chave[i], lista, 0, lista->n);
        // printf("%d\n", result);
        result == 1 ? printf("%s\n", "SIM") : printf("%s\n", "NÃO");
    }

    free(lista);
}
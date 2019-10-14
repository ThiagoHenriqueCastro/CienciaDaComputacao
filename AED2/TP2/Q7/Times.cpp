/**
 * TP02Q4 Quicksort Flexivel em C
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 09/2019 Este algoritmo faz um quicksort em uma lista dupla
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <locale.h>
#include <err.h>

#define LINE 1000
#define ENTRY 1000
#define HTML 10000

#define false 0
#define true 1
#define boolean short

/*
* Definiçao da Struct Time
*/
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

//TIPO CELULA ===================================================================
typedef struct CelulaDupla
{
    Time elemento;            // Elemento inserido na celula.
    struct CelulaDupla *prox; // Aponta a celula prox.
    struct CelulaDupla *ant;  // Aponta a celula anterior.
} CelulaDupla;

CelulaDupla *novaCelulaDupla(Time elemento[])
{
    CelulaDupla *nova = (CelulaDupla *)malloc(sizeof(CelulaDupla));
    memccpy(&nova->elemento, elemento, sizeof(Time), sizeof(Time));
    nova->ant = nova->prox = NULL;
    return nova;
}

/*
* Recebe a linha e verifica se a mesma é o fim do arquivo;
*/
boolean isFim(char *line)
{
    boolean resp = false;
    if (strlen(line) >= 3 && line[0] == 'F' && line[1] == 'I' && line[2] == 'M')
        resp = true;

    return resp;
}

/*
* Recebe a linha e a quebra em tokens, com base em um delimiter
*/
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

/*
* recebe uma string e remove tags da mesma, verificando o ponto de abertura e fechamento de tags
*/
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

/*
* Recebe a linha, um offset e o destino do substring. Pega o conteudo dentro da string;
*/
char *subString(const char *input, int offset, char *dest)
{
    int input_len = strlen(input);

    int len = input_len - offset;

    strncpy(dest, input + offset, len);
    return dest;
}

/*
* Recebe a linha da liga e corrige uma quebra de linha nao intencional
*/
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

/*
* Recebe a linha, um offset e o destino do substring. Pega o conteudo dentro da string;
* Desta vez, podemos determinar um limite para a substring
*/
char *subString_delimiter(const char *input, int offset, int len, char *dest)
{
    int input_len = strlen(input);

    strncpy(dest, input + offset, len);
    return dest;
}

/*
* Recebe a linha e verifica qual mes esta na mesma, o convertendo para seu valor inteiro em datas
*/
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

/*
* Abre e le um html, nele vai pegando as informaçoes e colocando no Time
*/
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
    char *html = NULL;
    char *line = NULL;
    int flag = 0;
    size_t len = 0;
    while (!feof(arq) && flag != 1)
    {
        getline(&html, &len, arq);

        /*
        * Essa linha contem todas as informaçoes necessarias
        */
        if (strstr(html, "Full name") != NULL)
        {
            flag = 1;
            line = html;

            /*
            * Trata a linha, splittando
            */
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

            /*
            * Mapeia toda a linha, cada vez pegando cada informaçao
            * Ao encontrar a informaçao, é feito um substring e a remocao das tags
            */
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
                    char *capacity_rcv = splitted[0];
                    char capacity_out[strlen(capacity_rcv)];
                    int cont = 0;
                    for (int i = 0; i < strlen(capacity_rcv); i++)
                    {
                        if (!ispunct(capacity_rcv[i]))
                        {
                            capacity_out[cont++] = capacity_rcv[i];
                        }
                    }
                    // printf("%s\n", capacity_out);
                    capacidade = atoi(capacity_out);
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
    fclose(arq);
    return 1;
}

//LISTA PROPRIAMENTE DITA =======================================================
CelulaDupla *primeiro;
CelulaDupla *ultimo;

/**
 * Cria uma lista dupla sem elementos (somente no cabeca).
 */
void start()
{
    Time *time = (Time *)malloc(sizeof(Time));
    primeiro = novaCelulaDupla(time);
    ultimo = primeiro;
    free(time);
}
/**
 * Insere um elemento na ultima posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirFim(Time x[])
{
    ultimo->prox = novaCelulaDupla(x);
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
}

/**
 *  Calcula e retorna o tamanho, em numero de elementos, da lista.
 *  @return resp int tamanho
 */
int tamanho()
{
    int tamanho = 0;
    CelulaDupla *i;
    for (i = primeiro; i != ultimo; i = i->prox, tamanho++)
        ;
    return tamanho;
}

/**
 * Mostra os elementos da lista separados por espacos.
 */
void mostrar()
{
    CelulaDupla *i;
    int j = 0;

    for (i = primeiro->prox; i != NULL; i = i->prox, j++)
    {
        // printf("[%d] ", j);
        imprimir(&i->elemento);
    }
}

void swap(CelulaDupla *i, CelulaDupla *j)
{
    Time *aux = (Time *)malloc(sizeof(Time));

    memccpy(aux, &i->elemento, sizeof(Time), sizeof(Time));
    memccpy(&i->elemento, &j->elemento, sizeof(Time), sizeof(Time));
    memccpy(&j->elemento, aux, sizeof(Time), sizeof(Time));
}

CelulaDupla *lastCelula(CelulaDupla *raiz)
{
    while (raiz && raiz->prox)
    {
        raiz = raiz->prox;
    }

    return raiz;
}

CelulaDupla *partition(CelulaDupla *l, CelulaDupla *h)
{
    Time *x = (Time *)malloc(sizeof(Time));
    memcpy(x, &h->elemento, sizeof(Time));

    CelulaDupla *i = l->ant;

    for (CelulaDupla *j = l; j != h; j = j->prox)
    {
        if (strcmp(j->elemento.apelido, x->apelido) < 0)
        {
            i = (i == NULL) ? l : i->prox;
            swap(i, j);
        }
    }
    i = (i == NULL) ? l : i->prox;
    swap(i, h);

    return i;
}

void _quicksort(CelulaDupla *l, CelulaDupla *h)
{
    if (h != NULL && l != h && l != h->prox)
    {
        CelulaDupla *p = partition(l, h);
        _quicksort(l, p->ant);
        _quicksort(p->prox, h);
    }
}

void quicksort(CelulaDupla *raiz)
{
    CelulaDupla *h = lastCelula(raiz);

    _quicksort(raiz, h);
}

int main(int argc, char const *argv[])
{
    start();
    char input[ENTRY][LINE];
    int inputIndex = 0;

    Time *time;

    /*
    * Preenche um vetor com as entradas
    */
    do
    {

        fgets(input[inputIndex], LINE, stdin);
    } while (isFim(input[inputIndex++]) == false);
    inputIndex--;
    char *newline;
    /*
    * Para cada entrada, le e imprime o Time
    */
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
        inserirFim(time);

        free(time);
    }

    quicksort(primeiro);
    mostrar();
}
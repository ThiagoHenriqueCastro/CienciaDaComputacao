/**
 * Prova 1
 * @author THIAGO HENRIQUE DE CASTRO OLIVEIRA
 * @version 1 09/2021
 * COMPLEXIDADE O(n) onde n é o numero de consultas ao vetor de colonias

 */

#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main()
{
    int days;
    cin >> days;
    int d1, d2, d3, d4;
    long long matrix[4];
    long long result = 0;
    while (days != 0)
    {
        cin >> d1 >> d2 >> d3 >> d4;

        matrix[0] = d1;
        matrix[1] = d2;
        matrix[2] = d3;
        matrix[3] = d4;

        matrix[0] = (d1 + d2 + d3 + d4);
        result = (d1 + d2 + d3 + d4) * 2 - d1;

        for (int i = 5; i < days; i++)
        {
            int aux = matrix[i % 4];
            matrix[i % 4] = result;
            result *= 2;
            result -= aux;
        }

        cout << result % 13371337 << "\n";
        result = 0;
        cin >> days;
    }
}

/**
 * AS10
 * @author THIAGO HENRIQUE DE CASTRO OLIVEIRA
 * @version 1 09/2021
 */

#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
#include <iomanip>

using namespace std;

void _countMoney(double money)
{
    int notas100, aux50, notas50, aux20, notas20, aux10, notas10, aux5, notas5, aux2, notas2;
    int decPart, auxm1, m1, c50, auxc25, c25, auxc10, c10, auxc5, c5, auxc1, c1;

    int intPart = (int)money;
    money -= intPart;
    decPart = (money * 100);

    notas100 = intPart / 100;
    aux50 = intPart % 100;
    notas50 = aux50 / 50;
    aux20 = aux50 % 50;
    notas20 = aux20 / 20;
    aux10 = aux20 % 20;
    notas10 = aux10 / 10;
    aux5 = aux10 % 10;
    notas5 = aux5 / 5;
    aux2 = aux5 % 5;
    notas2 = aux2 / 2;

    cout << "NOTAS:" << endl;
    cout << notas100 << " nota(s) de R$ 100.00" << endl;
    cout << notas50 << " nota(s) de R$ 50.00" << endl;
    cout << notas20 << " nota(s) de R$ 20.00" << endl;
    cout << notas10 << " nota(s) de R$ 10.00" << endl;
    cout << notas5 << " nota(s) de R$ 5.00" << endl;
    cout << notas2 << " nota(s) de R$ 2.00" << endl;

    auxm1 = aux2 % 2;
    m1 = auxm1 / 1;
    c50 = decPart / 50;
    auxc25 = decPart % 50;
    c25 = auxc25 / 25;
    auxc10 = auxc25 % 25;
    c10 = auxc10 / 10;
    auxc5 = auxc10 % 10;
    c5 = auxc5 / 5;
    auxc1 = auxc5 % 5;
    c1 = auxc1 / 1;

    cout << "MOEDAS:" << endl;
    cout << m1 << " moeda(s) de R$ 1.00" << endl;
    cout << c50 << " moeda(s) de R$ 0.50" << endl;
    cout << c25 << " moeda(s) de R$ 0.25" << endl;
    cout << c10 << " moeda(s) de R$ 0.10" << endl;
    cout << c5 << " moeda(s) de R$ 0.05" << endl;
    cout << c1 << " moeda(s) de R$ 0.01" << endl;
}

int main()
{
    double money;

    cin >> money;

    _countMoney(money);
}

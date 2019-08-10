#include <iostream>
#include <string>
#include <math.h>
using namespace std;

class Triangulo
{
private:
    float lado1;
    float lado2;
    float lado3;

public:
    void setLado1(float valor)
    {
        if (valor >= 0)
        {
            this->lado1 = valor;
        }
        else
        {
            this->lado1 = 0;
        }
    }

    void inLado1()
    {
        cout << "\nDigite o lado 1: ";
        cin >> this->lado1;
    }

    void inLado2()
    {
        cout << "\nDigite o lado 2: ";
        cin >> this->lado2;
    }

    void inLado3()
    {
        cout << "\nDigite o lado 3: ";
        cin >> this->lado3;
    }

    void setLado2(float valor)
    {
        if (valor >= 0)
        {
            this->lado2 = valor;
        }
        else
        {
            this->lado2 = 0;
        }
    }

    void setLado3(float valor)
    {
        if (valor >= 0)
        {
            this->lado3 = valor;
        }
        else
        {
            this->lado3 = 0;
        }
    }

    float getLado1()
    {
        float retorno = this->lado1;
        if (this->lado1 < 0)
        {
            retorno = 0;
        }
        return retorno;
    }

    float getLado2()
    {
        float retorno = this->lado2;
        if (this->lado2 < 0)
        {
            retorno = 0;
        }
        return retorno;
    }

    float getLado3()
    {
        float retorno = this->lado3;
        if (this->lado3 < 0)
        {
            retorno = 0;
        }
        return retorno;
    }

    float calcPerimetro()
    {
        return getLado1() + getLado2() + getLado3();
    }

    string tipoTriangulo()
    {
        string retorno = "Nao é um triangulo";
        if (!(getLado1() >= getLado2() + getLado3() || getLado2() >= getLado1() + getLado3() || getLado3() >= getLado1() + getLado2()))
        {
            if (getLado1() != getLado2() && getLado1() && getLado3() && getLado2() != getLado3())
            {
                retorno = "Escaleno";
            }
            else if ((getLado1() == getLado2() && getLado1() != getLado3()) || (getLado1() == getLado3() && getLado1() != getLado2()) || (getLado2() == getLado3() && getLado2() != getLado1()))
            {
                retorno = "Isorceles";
            }
            else
            {
                retorno = "Equilatero";
            }
        }
        return retorno;
    }

    string comparaTriangulos(Triangulo *triangulo_)
    {
        string retorno = "Diferentes\n";
        if (getLado1() == triangulo_->getLado1() && getLado2() == triangulo_->getLado2() && getLado3() == triangulo_->getLado3())
        {
            retorno = "Iguais\n";
        }
        return retorno;
    }
};
int main()
{
    Triangulo triangulo;
    triangulo.setLado1(1);
    triangulo.setLado2(2);
    triangulo.setLado3(2);
    cout << std::to_string(triangulo.calcPerimetro()) + "\n";
    cout << triangulo.tipoTriangulo() + "\n";
    // --------------------------------------- //
    Triangulo triangulo1;
    triangulo1.inLado1();
    triangulo1.inLado2();
    triangulo1.inLado3();
    cout << std::to_string(triangulo1.calcPerimetro()) + "\n";
    cout << triangulo1.tipoTriangulo() + "\n";
    // --------------------------------------- //
    Triangulo trianguloArray[5];
    for (int i = 0; i < 5; i++)
    {
        trianguloArray[i].inLado1();
        trianguloArray[i].inLado2();
        trianguloArray[i].inLado3();
        cout << trianguloArray[i].tipoTriangulo() + "\n";
        cout << std::to_string(trianguloArray[i].calcPerimetro()) + "\n";
    }
    // --------------------------------------- //
    cout << triangulo.comparaTriangulos(&trianguloArray[1]);
}
#include <iostream>
#include <string>
#include <math.h>
using namespace std;

class FigGeometrica
{
private:
    static int quantidade;

public:
    virtual float calcPerimetro() = 0;
    virtual float calcArea() = 0;
    void incrementQuant() { quantidade++; };
};

class Triangulo : public FigGeometrica
{
private:
    float lado1;
    float lado2;
    float lado3;
    static int quant;

public:
    Triangulo(float a, float b, float c)
    {
        setLado1(a);
        setLado2(b);
        setLado3(c);
        incrementQuant();
    };
    Triangulo(float a)
    {
        setLado1(a);
        setLado2(a);
        setLado3(a);
        quant++;
    };
    Triangulo()
    {
        setLado1(0);
        setLado2(0);
        setLado3(0);
        quant++;
    };
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
    static int getQuant()
    {
        return quant;
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

    void getInfo()
    {
        cout << getLado1();
        cout << "\n";
        cout << getLado2();
        cout << "\n";
        cout << getLado3();
        cout << "\n";
        cout << calcPerimetro();
        cout << "\n";
    }

    float calcPerimetro()
    {
        return getLado1() + getLado2() + getLado3();
    }

    float calcArea()
    {
        return (getLado1() + getLado2()) / 2;
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
    void listaTipo(string tipo)
    {
        if (this->tipoTriangulo() == tipo)
        {
            this->getInfo();
        }
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
    int contIguais(Triangulo *triangulo_)
    {
        int retorno = 0;
        if (getLado1() == triangulo_->getLado1() && getLado2() == triangulo_->getLado2() && getLado3() == triangulo_->getLado3())
        {
            retorno = 1;
        }
        return retorno;
    }
};
int FigGeometrica::quantidade = 0;
int main()
{
    char option = '0';
    float lado1, lado2, lado3;
    Triangulo *triangulo[100];
    do
    {
        cout << "\n";
        cout << "0 - Sair do Programa\n";
        cout << "1 - Criar Triangulo\n";
        cout << "2 - Listar Triangulos\n";
        cout << "3 - Triangulos iguais\n";
        cout << "4 - Listar os triangulos de um determinado tipo\n";
        cout << "5 - Verificar inconsistencias\n";
        cout << "Selecione uma opcao: ";
        cin >> option;
        if (option == '1')
        {
            int indice = Triangulo::getQuant();
            if (indice <= 100)
            {
                cout << "Digite o primeiro lado\n";
                cin >> lado1;
                cout << "Digite o segundo lado\n";
                cin >> lado2;
                cout << "Digite o terceiro lado\n";
                cin >> lado3;
                triangulo[indice] = new Triangulo(lado1, lado2, lado3);
            }
            else
            {
                cout << "Array cheio\n";
            }
        }
        if (option == '2')
        {
            int indice = Triangulo::getQuant();
            for (int i = 0; i < indice; i++)
            {
                triangulo[i]->getInfo();
            }
        }
        if (option == '3')
        {
            int indice = Triangulo::getQuant();
            int areEqual = 0;
            for (int i = 0; i < indice; i++)
            {
                for (int j = 0; j < indice; j++)
                {
                    if (i != j)
                    {
                        areEqual += triangulo[i]->contIguais(triangulo[j]);
                    }
                }
            }
            cout << areEqual / 2;
            cout << "\n";
        }
        if (option == '4')
        {
            int indice = Triangulo::getQuant();
            for (int i = 0; i < indice; i++)
            {
                triangulo[i]->listaTipo("Equilatero");
            }
        }
    } while (option != '0');
}
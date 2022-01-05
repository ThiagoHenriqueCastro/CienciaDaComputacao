// Questao 2 da lista 2
// Grasp - Polimorfismo

class Pluviometro {
  String tipo;
  int? peso, capacidade;

  Pluviometro({required this.tipo});

  String getTipo() {
    return tipo;
  }

  int getPeso() {
    if (tipo == 'A') {
      return 10;
    } else {
      return 20;
    }
  }

  int getCapacidade() {
    if (tipo == 'A') {
      return 10;
    } else {
      return 20;
    }
  }
}

class Caminhao {
  int? capacidade = 0;
  List<Pluviometro>? p_;
  Caminhao();
  void inserePluviometro(Pluviometro p) {}
  void setP(Pluviometro p) {
    p_!.add(p);
  }
}

class CaminhaoAlfa extends Caminhao {
  @override
  void inserePluviometro(Pluviometro p) {
    if (capacidade! <= 5000) setP(p);
  }
}

class CaminhaoBeta extends Caminhao {
  @override
  void inserePluviometro(Pluviometro p) {
    if (!p_!.contains(p)) setP(p);
  }
}

void main(List<String> arguments) {
  var a = CaminhaoAlfa();
  a.inserePluviometro(Pluviometro(tipo: 'A'));
}

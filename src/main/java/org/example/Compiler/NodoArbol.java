package org.example.Compiler;

import java.util.ArrayList;

public class NodoArbol {
    String valor;
    ArrayList<NodoArbol> hijos;

    public NodoArbol(String valor) {
        this.valor = valor;
        this.hijos = new ArrayList<>();
    }

    public void agregarHijo(NodoArbol hijo) {
        this.hijos.add(hijo);
    }

    public void imprimir(String prefijo, boolean esUltimo) {
        System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + valor);
        for (int i = 0; i < hijos.size() - 1; i++) {
            hijos.get(i).imprimir(prefijo + (esUltimo ? "    " : "│   "), false);
        }
        if (hijos.size() > 0) {
            hijos.get(hijos.size() - 1).imprimir(prefijo + (esUltimo ? "    " : "│   "), true);
        }
    }
}

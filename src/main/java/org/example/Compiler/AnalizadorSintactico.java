package org.example.Compiler;

import org.example.Compiler.NodoArbol;
import org.example.Compiler.Token;

import java.util.ArrayList;

public class AnalizadorSintactico {
    ArrayList<Token> tokens;
    int indice;
    NodoArbol raiz;

    public AnalizadorSintactico(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.indice = 0;
        this.raiz = null;
    }

    void avanzar() {
        indice++;
    }

    Token obtenerTokenActual() {
        if (!esFinDeCodigo()) {
            return tokens.get(indice);
        } else {
            return null;
        }
    }

    boolean esFinDeCodigo() {
        return indice >= tokens.size();
    }

    boolean esToken(String tipoEsperado) {
        return !esFinDeCodigo() && tokens.get(indice).tipo.equals(tipoEsperado);
    }

    boolean emparejar(String tipoEsperado) {
        if (esToken(tipoEsperado)) {
            avanzar();
            return true;
        } else {
            return false;
        }
    }

    boolean declaracionVariable(NodoArbol nodo) {
        if (emparejar("VAR") && emparejar("IDENTIFICADOR") && emparejar("IGUAL") && expresion(nodo) && emparejar("PUNTO_COMA")) {
            return true;
        } else {
            return false;
        }
    }

    boolean expresion(NodoArbol nodo) {
        NodoArbol nodoTermino = new NodoArbol("TERMINO");
        if (termino(nodoTermino)) {
            nodo.agregarHijo(nodoTermino);
            return true;
        } else {
            return false;
        }
    }

    boolean termino(NodoArbol nodo) {
        NodoArbol nodoFactor = new NodoArbol("FACTOR");
        if (factor(nodoFactor)) {
            nodo.agregarHijo(nodoFactor);
            return true;
        } else {
            return false;
        }
    }

    boolean factor(NodoArbol nodo) {
        if (emparejar("NUMERO")) {
            nodo.agregarHijo(new NodoArbol("NUMERO"));
            return true;
        } else if (emparejar("IDENTIFICADOR")) {
            nodo.agregarHijo(new NodoArbol("IDENTIFICADOR"));
            return true;
        } else if (emparejar("PARENTESIS") && expresion(nodo) && emparejar("PARENTESIS")) {
            return true;
        } else {
            return false;
        }
    }

    public void inicioAnalisis() {
        this.raiz = new NodoArbol("INICIO");
        while (!esFinDeCodigo()) {
            NodoArbol nodo = new NodoArbol("SENTENCIA");
            if (!declaracionVariable(nodo)) {
                System.out.println("Error sintáctico en la línea " + indice);
                break;
            }
            this.raiz.agregarHijo(nodo);
        }
        if (raiz != null) {
            System.out.println("Árbol de Derivación:");
            raiz.imprimir("", true);
        }
    }}
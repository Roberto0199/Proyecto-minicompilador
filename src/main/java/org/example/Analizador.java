package org.example;

import org.example.Compiler.AnalizadorLexico;
import org.example.Compiler.AnalizadorSintactico;
import org.example.Compiler.Token;

import java.util.ArrayList;

public class Analizador {
    public static void main(String[] args) {
        AnalizadorLexico lexico = new AnalizadorLexico();
        String codigo = "int a = b + c";
        ArrayList<Token> tokens = lexico.analizar(codigo);

        // Mostrar tabla de símbolos y tabla de errores léxicos
        lexico.mostrarTablaSimbolos();
        lexico.mostrarErroresLexicos();

        // Identificar los tokens
        System.out.println("Tokens encontrados:");
        for (Token token : tokens) {
            System.out.println(token.tipo + ": " + token.valor);
        }

        // Código de ejemplo para análisis sintáctico
        AnalizadorSintactico sintactico = new AnalizadorSintactico(tokens);
        sintactico.inicioAnalisis();
        System.out.println("Análisis sintáctico completado.");
    }
}

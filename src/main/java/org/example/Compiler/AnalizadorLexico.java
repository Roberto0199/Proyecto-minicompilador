package org.example.Compiler;

import org.example.Compiler.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorLexico {
    ArrayList<Token> tokens = new ArrayList<>();
    HashMap<String, String> tablaSimbolos = new HashMap<>();
    ArrayList<String> erroresLexicos = new ArrayList<>();

    public ArrayList<Token> analizar(String codigo) {
        // Expresiones regulares para los tokens
        Pattern patronVar = Pattern.compile("var");
        Pattern patronFuncion = Pattern.compile("funcion");
        Pattern patronSi = Pattern.compile("si");
        Pattern patronMientras = Pattern.compile("mientras");
        Pattern patronRetornar = Pattern.compile("retornar");
        Pattern patronIdentificador = Pattern.compile("[a-zA-Z][a-zA-Z0-9_]*");
        Pattern patronNumero = Pattern.compile("\\d+");
        Pattern patronOperador = Pattern.compile("[+\\-*/]");
        Pattern patronParentesis = Pattern.compile("[()]");
        Pattern patronIgual = Pattern.compile("=");
        Pattern patronPuntoComa = Pattern.compile(";");

        // Iterar sobre el código y encontrar tokens
        Matcher matcher = Pattern.compile("\\s*(" + String.join("|", patronVar.pattern(), patronFuncion.pattern(), patronSi.pattern(), patronMientras.pattern(), patronRetornar.pattern(), patronIdentificador.pattern(), patronNumero.pattern(), patronOperador.pattern(), patronParentesis.pattern(), patronIgual.pattern(), patronPuntoComa.pattern()) + ")").matcher(codigo);
        while (matcher.find()) {
            String token = matcher.group(1);
            if (patronVar.matcher(token).matches()) {
                tokens.add(new Token("VAR", token));
            } else if (patronFuncion.matcher(token).matches()) {
                tokens.add(new Token("FUNCION", token));
            } else if (patronSi.matcher(token).matches()) {
                tokens.add(new Token("SI", token));
            } else if (patronMientras.matcher(token).matches()) {
                tokens.add(new Token("MIENTRAS", token));
            } else if (patronRetornar.matcher(token).matches()) {
                tokens.add(new Token("RETORNAR", token));
            } else if (patronIdentificador.matcher(token).matches()) {
                tokens.add(new Token("IDENTIFICADOR", token));
                // Agregar identificador a la tabla de símbolos
                tablaSimbolos.putIfAbsent(token, "VARIABLE");
            } else if (patronNumero.matcher(token).matches()) {
                tokens.add(new Token("NUMERO", token));
            } else if (patronOperador.matcher(token).matches()) {
                tokens.add(new Token("OPERADOR", token));
            } else if (patronParentesis.matcher(token).matches()) {
                tokens.add(new Token("PARENTESIS", token));
            } else if (patronIgual.matcher(token).matches()) {
                tokens.add(new Token("IGUAL", token));
            } else if (patronPuntoComa.matcher(token).matches()) {
                tokens.add(new Token("PUNTO_COMA", token));
            } else {
                // Si se encuentra un token no reconocido, agregar a la lista de errores léxicos
                erroresLexicos.add("Token no reconocido: " + token);
            }
        }
        return tokens;
    }

    public void mostrarTablaSimbolos() {
        System.out.println("Tabla de Símbolos:");
        for (String nombre : tablaSimbolos.keySet()) {
            String tipo = tablaSimbolos.get(nombre);
            System.out.println(nombre + ": " + tipo);
        }
    }

    public void mostrarErroresLexicos() {
        System.out.println("Errores Léxicos:");
        for (String error : erroresLexicos) {
            System.out.println(error);
        }
    }
}

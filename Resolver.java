import java.util.Stack;

public class Resolver {
    public static void main(String[] args) {
        double r = resolver(args[0]);
        System.out.println(r);
    }

    public static double resolver(String expresion) {

        Stack<Double> operandos = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        String[] caracteres = expresion.split("(?<=[-+*/^()])|(?=[-+*/^()])");
        imprimir(caracteres);
        int i = 0;
        for (String caracter : caracteres) {
            
            System.out.println( "caracter actual  " + caracter);
            if (caracter.isEmpty()) {
                // Ignorar tokens vacíos
                continue;
            } 
            else if(esNumeroDouble(caracter)){
                if(!operadores.isEmpty() && operadores.peek() == '!'){
                    operandos.push(-Double.parseDouble(caracter));
                    operadores.pop();
                }
                else{
                    operandos.push(Double.parseDouble(caracter));

                }
            }

            else if (caracter.equals("(")) {
                operadores.push(caracter.charAt(0));
                
            } 
            // operadores.peek() es el ultimo operador en la pila 
            // comparamos si el ultimo operadore en la pila tiene mayor prioridad que el operador actual 
            else if(esOperador(caracter) && esOperadorUnario(caracteres, i)){
                System.out.println("Es operador unario?: " + esOperadorUnario(caracteres, i));
                operadores.push('!');
            }
            else if (esOperador(caracter)) {
                System.out.println("Es operador unario?: " + esOperadorUnario(caracteres, i));
              
                if(!operadores.isEmpty() && prioridad(operadores.peek()) > prioridad(caracter.charAt(0)) ){
                    evaluarOperador(operandos, operadores);
                    
                }
                operadores.push(caracter.charAt(0));
            }
            else if(caracter.equals(")")){
                System.out.println("evaluando expresion dentro de parentesis ....");
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    evaluarOperador(operandos, operadores);
                }
                operadores.pop();
            }
        
            System.out.println("operandos  " + operandos);
            System.out.println("operadores  " + operadores);
            i++;
            System.out.println("__________________");
     
        }

         while (!operadores.isEmpty()) {
            evaluarOperador(operandos, operadores);
        }

       System.out.println("operandos  " + operandos);
            System.out.println("operadores  " + operadores);
   
            System.out.println("__________________");
        return operandos.peek();
    }

    public static void imprimir(String[] arreglo) {
        for (String String : arreglo) {
            System.out.print(String + " , ");
        }
        System.out.println();

    }
    public static boolean esOperadorUnario(String[] expresion, int indice){
        
        if(indice == 0){
            return true;
        }
        else if(esOperador(expresion[indice-1])|| expresion[indice-1].equals("(")){
            return true;
        }
      

        return false;

    }
    public static boolean esOperador(String cadena) {
        if (cadena.equals("+") || cadena.equals("-") ||
                cadena.equals("-") || cadena.equals("*") ||
                cadena.equals("^") || cadena.equals("/")) {
            return true;
        }
        return false;

    }
    public static int prioridad(char operador){
        
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0; // Para otros caracteres o paréntesis
        }
    }
    public static void evaluarOperador(Stack<Double> operandos, Stack<Character> operadores){
         if (operandos.size() < 2 || operadores.isEmpty()) {
            throw new IllegalArgumentException("Expresión no válida");
        }
        double b = operandos.pop();
        double a = operandos.pop();
        char operador = operadores.pop();
        switch (operador) {
            case '^':

                System.out.println("potencia");

                break;
            case '+':
                operandos.push(a + b);
                break;
            case '-':
                operandos.push(a - b);
                break;
            case '*':
                operandos.push(a * b);
  
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("División por cero");
                }
                operandos.push(a / b);
                break;
            default:
                throw new IllegalArgumentException("Operador no válido");
        }

    }
    public static boolean esNumeroDouble(String cadena) {
        try {
            // Intenta convertir la cadena en un double
            Double.parseDouble(cadena);
            return true; // La conversión fue exitosa, es un número double válido
        } catch (NumberFormatException e) {
            return false; // La conversión falló, no es un número double válido
        }
    }

}
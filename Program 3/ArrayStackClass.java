/*
 * Alexander Gonzalez Ramirez
 * Program #3 - Stacks: Infix to Postfix
 */

public class ArrayStackClass {
    public char[] stack;
    private int topElement = -1;
    public ArrayStackClass(int n){
        stack = new char[n];
    }
    public String InfixToPostfix(String s) {
        StringBuilder output = new StringBuilder();
        int openParenCount = 0;
        char element;

        for (int i = 0; i < s.length(); i++) {
            char[] characters = {'(', ')', '.', '+', '*', '/', '–', '%'};
            element = s.charAt(i);

            if (Character.isWhitespace(element)) continue;

            if (Character.isLetterOrDigit(element) || element == '.') {
                output.append(element);
            } else if (element == '(') {
                push(element);
                openParenCount++;
            } else if (element == ')') {
                if (openParenCount == 0) {
                    return ("unmatched parens");
                }
                while (peek() != '(') {
                    output.append(pop());
                }
                pop();
                openParenCount--;
            } else {
                boolean isValid = false;
                for (char c : characters) {
                    if (element == c) {
                        isValid = true;
                        break;
                    }
                }

                if (!isValid) {
                    return ("invalid character");
                }

                while (!isEmpty() && peek() != '(' && precedence(element) <= precedence(peek())) {
                    output.append(pop());
                }
                push(element);
            }
        }

        while (!isEmpty()) {
            output.append(pop());
        }


        return output.toString();
    }

    public void push(char element){
        if (topElement == getSize()) throw new StackOverflowError("Stack is full");
        stack[++topElement] = element;
    }

    public char pop(){
        return stack[topElement--];
    }

    public char peek(){
        return stack[topElement];
    }

    public boolean isEmpty(){
        return topElement == -1;
    }

    public int getSize(){
        return topElement + 1;
    }

    public int precedence(char operand){
        if (operand == '+' || operand == '-'){
            return 1;
        } else if (operand == '*' || operand == '/'|| operand == '%') {
            return 2;
        }
        else{
            return  0;
        }
    }
}

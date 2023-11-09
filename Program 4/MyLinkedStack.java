/*
 * Alexander Gonzalez Ramirez
 * Program #4 - Linked Lists
 */

import java.util.EmptyStackException;

class Node{
    char data;
    Node next;

    public Node(char data) {
        this.data = data;
        this.next = null;
    }
}

public class MyLinkedStack {
    Node head;

    public String InfixToPostfix(String s) {
        StringBuilder output = new StringBuilder();
        int openParenCount = 0;
        int closedParenCount = 0;
        char element;

        for (int i = 0; i < s.length(); i++) {
            element = s.charAt(i);

            if (Character.isWhitespace(element)) continue;
            if (Character.isLetterOrDigit(element) || element == '.') {
                output.append(element);
            } else if (element == '(') {
                push(element);
                openParenCount++;
            } else if (element == ')') {
                closedParenCount++;
                while (peek() != '(') {
                    output.append(pop());
                }
                pop();
                openParenCount--;
            } else {
                char[] characters = {'(', ')', '.', '+', '*', '/', '–', '%'};
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

        if (openParenCount > closedParenCount) {
            return("unmatched parens");
        }

        return output.toString();
    }

    public void push(char element) {
        Node newNode = new Node(element);
        newNode.next = head;
        head = newNode;
    }

    public char pop() {
        if (isEmpty()) throw new EmptyStackException();
        char data = head.data;
        head = head.next;
        return data;
    }

    public char peek() {
        if (isEmpty()) throw new EmptyStackException();
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int precedence(char operand) {
        if (operand == '+' || operand == '-') {
            return 1;
        } else if (operand == '*' || operand == '/' || operand == '%') {
            return 2;
        } else {
            return 0;
        }
    }
}


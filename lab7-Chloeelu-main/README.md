# Evaluating Arithmetic Expressions

In this activity, you will evaluate arithmetic expressions in two different ways:

1. when the expression is provided in the postfix notation, and
2. when the expression is provided in the more common infix notation.

Through this activity, you will learn to work with stacks and [binary] trees. You will also see lexical analysis and parsing in action.

## Evaluating Postfix Expressions

- You are expected to support only the four basic arithmetic operations: addition, subtraction, multiplication and division.
  - You do not need to support unary operations such as unary - directly.

### Suggested Reading

- Wikipedia entry on [Postfix Notation](https://en.wikipedia.org/wiki/Reverse_Polish_notation)

- Java's [Stack](http://docs.oracle.com/javase/8/docs/api/java/util/Stack.html) class

### Tasks

- Using the provided skeleton code, implement a method to evaluate an arithmetic expression given in Postfix notation.
- If the provided expression string is not in the correct form then, when evaluating the expression, a `MalformedExpressionException` should be thrown.

## Evaluating Infix Expressions using Binary Trees

This part of the activity demonstrates how to convert a provided arithmetic expression into an arithmetic expression tree. The example only works with fully parenthesized expressions with only three operations: addition, subtraction and multiplication.

The purpose of this example is to demonstrate the following:

- how to create simple *subtypes* using *subclassing* in Java (`extends`);
- how to parse a simple arithmetic expression;
- how to construct a binary tree;
- how to exploit the recursive nature of a binary tree;
- how to evaluate an arithmetic expression from its binary tree representation.

This activity involves infix arithmetic expression evaluation. This contrasts with postfix expression evaluation that used only a stack.

### Tasks

- Implement support for multiplication and division.

### Suggested Reading/Viewing

- Tutorial on [subclasses](http://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html) in Java
- [Slidedeck: Binary trees](https://drive.google.com/file/d/1O76uSAI77NOJr4dwHTyyLeOeGM5_RDB1/view?usp=sharing)
- Videos on binary trees: [Stanford - Programming Abstractions](http://www.youtube.com/watch?v=FKvL3Duawv8)
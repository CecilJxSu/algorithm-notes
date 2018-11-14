package chapter01;

import java.util.Stack;

public class ArithmeticExpression {

    public static void main(String[] args) {
        double result1 = calcExpression("1 + ((2 - 4) * (12 / 3)) - 10");
        double result2 = calcExpression("3 - (6 - (10 - 1))");
        System.out.println(result1);
        System.out.println(result2);
    }

    /**
     * 栈顶部的操作符优先级 >= 当前扫描的操作符优先级？
     * ┌───┬────────────┐
     * │   │ +  -  *  / │
     * ├───┼────────────┤
     * │ + │ T  T  F  F │
     * │ - │ T  T  F  F │
     * │ * │ T  T  T  T │
     * │ / │ T  T  T  T │
     * └───┴────────────┘
     */
    private static boolean[][] opCompare = {
            {true, true, false, false},
            {true, true, false, false},
            {true, true, true, true},
            {true, true, true, true}
    };

    /**
     * 计算表达式
     * @param expression    算术表达式
     * @return              结果
     */
    private static double calcExpression(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("\"expression\" can not be empty.");
        }
        // 操作数
        Stack<Double> operands = new Stack<Double>();
        // 操作符
        Stack<Character> operators = new Stack<Character>();
        // 扫描表达式
        for (int i = 0; i < expression.length(); i++) {
            // 数字压栈
            if (isNum(expression.charAt(i))) {
                int numStart = i;
                while (++i < expression.length() && isNum(expression.charAt(i))) {}
                operands.push(Double.parseDouble(expression.substring(numStart, i--)));
                continue;
            }
            switch (expression.charAt(i)) {
                case '(':
                    operators.push(expression.charAt(i));
                    break;
                case '+':
                case '*':
                case '-':
                case '/':
                    // 栈中的操作符号优先级高或同级别，则可以直接计算结果
                    // 运算是从左到右的，因此要及时计算结果
                    int rowIndex = getOpIndex(operators.peek());
                    int colIndex = getOpIndex(expression.charAt(i));
                    while (!operators.isEmpty()
                           && operators.peek() != '('
                           && opCompare[rowIndex][colIndex]) {
                        operands.push(calc(operands.pop(), operands.pop(), operators.pop()));
                    }
                    operators.push(expression.charAt(i));
                    break;
                case ')':
                    // 计算括号内的表达式
                    char operator;
                    while (!operators.empty() && (operator = operators.pop()) != '(') {
                        operands.push(calc(operands.pop(), operands.pop(), operator));
                    }
                    break;
                default:
            }
        }
        while (!operators.isEmpty()) {
            operands.push(calc(operands.pop(), operands.pop(), operators.pop()));
        }
        // 计算整个表达式
        return operands.isEmpty() ? Double.NaN : operands.pop();
    }

    /**
     * 判断字符是否是数字
     * @param c 字符
     * @return  true - 数字；false - 非数字
     */
    private static boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 获取操作符优先级表opCompare的下标
     * @param op    操作符
     * @return      下标
     */
    private static int getOpIndex(char op) {
        switch (op) {
            case '+':
                return 0;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 3;
            default:
                return -1;
        }
    }

    /**
     * 计算（operand2 op operand1）的值
     * @param operand1  右操作数
     * @param operand2  左操作数
     * @param op        操作符
     * @return          运算结果
     */
    private static double calc(double operand1, double operand2, char op) {
        switch (op) {
            case '+':
                return operand2 + operand1;
            case '-':
                return operand2 - operand1;
            case '*':
                return operand2 * operand1;
            case '/':
                return operand2 / operand1;
            default:
                return Double.NaN;
        }
    }
}

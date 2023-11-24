package model;

public class MathNode {
    String value;
    MathNode left;
    MathNode right;

    public MathNode(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public MathNode(String value, MathNode left, MathNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    // Differentiates the node
    public MathNode differentiate() {
        switch (value) {
            case "+":
                return new MathNode("+", left.differentiate(), right.differentiate());
            case "-":
                return new MathNode("-", left.differentiate(), right.differentiate());
            case "*":
                return new MathNode("+",
                        new MathNode("*", left.differentiate(), right),
                        new MathNode("*", left, right.differentiate()));
            case "/":
                return new MathNode("/",
                        new MathNode("-",
                                new MathNode("*", left.differentiate(), right),
                                new MathNode("*", left, right.differentiate())),
                        new MathNode("*", right, right));
            case "^":  // Implementing the power rule, assuming the form is x^n, where n is a constant
                if (left.value.equals("x") && isNumeric(right.value)) {
                    return new MathNode("*",
                            right,
                            new MathNode("^", left, new MathNode(String.valueOf(Integer.parseInt(right.value) - 1))));
                }
            case "x":
                return new MathNode("1");
            default: // Assuming it's a constant
                return new MathNode("0");
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Converts the node back to string for display
    @Override
    public String toString() {
        if (left == null && right == null) {
            return value;
        }
        return "(" + left.toString() + value + right.toString() + ")";
    }

    public MathNode simplify() {
        if (isOperator(value)) {
            left = left == null ? null : left.simplify();
            right = right == null ? null : right.simplify();

            switch (value) {
                case "+":
                    if (isZero(left.value)) return right; // 0 + x = x
                    if (isZero(right.value)) return left; // x + 0 = x
                    break;
                case "-":
                    if (isZero(right.value)) return left; // x - 0 = x
                    break;
                case "*":
                    if (isZero(left.value) || isZero(right.value)) return new MathNode("0"); // x * 0 or 0 * x = 0
                    if (isOne(left.value)) return right; // 1 * x = x
                    if (isOne(right.value)) return left; // x * 1 = x
                    break;
                case "/":
                    if (isOne(right.value)) return left; // x / 1 = x
                    break;
            }

            // Add more simplification rules as needed
        }
        return this;
    }

    private boolean isOperator(String value) {
        return "+-*/".contains(value);
    }

    private boolean isZero(String value) {
        return "0".equals(value);
    }

    private boolean isOne(String value) {
        return "1".equals(value);
    }
}

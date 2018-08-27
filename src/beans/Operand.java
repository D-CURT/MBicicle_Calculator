package beans;

import beans.abstractions.AbstractElement;
import beans.interfaces.FIOperand_check;

import java.util.Arrays;
import java.util.regex.Pattern;

import static support.constants.Constants.FRACTIONAL_REGEX;
import static support.constants.Constants.INTEGER_REGEX;

public class Operand extends AbstractElement {
    public enum Content {
        INTEGER(s -> Pattern.compile(INTEGER_REGEX).matcher(s).find()),
        FRACTIONAL(s -> Pattern.compile(FRACTIONAL_REGEX).matcher(s).find()),

        DEFAULT();

        private FIOperand_check method;

        Content() {
        }

        Content(FIOperand_check method) {
            this.method = method;
        }

        public FIOperand_check getMethod() {
            return method;
        }
    }

    @Override
    public Content getElement(String s) {
        return Arrays.stream(Content.values()).filter(operand ->
                Content.INTEGER.method.check(s) || Content.FRACTIONAL.method.check(s)).findFirst().orElse(Content.DEFAULT);
    }

    public boolean found(StringBuilder sb) {
        return found(sb.toString());
    }

    @Override
    public boolean found(String s) {
        return getElement(s).method != null;
    }
}

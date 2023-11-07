package lotto.view;

public class VerificationView {

    private final static String INPUT_MONEY_ERROR = "[ERROR] 구입 금액은 최소 1000원 이상, 1000원 단위의 금액입니다.";
    private final static String STRING_TO_LONG_ERROR = "[ERROR] 입력 값은 숫자로만 이루어져 있어야 합니다.";
    private final static String LOTTO_NUMBER_RANGE_ERROR = "[ERROR] 로또 번호는 1 부터 45 까지의 숫자로 이루어져 있어야 합니다.";
    private final static String LOTTO_NUMBER_LENGTH_ERROR = "[ERROR] 로또 번호 6자리로 이루어져 있어야 합니다.";
    private final static String LOTTO_NUMBER_INTEGER_ERROR = "[ERROR] 문자열의 요소는 숫자 패턴이여야 합니다.";
    public static void verificationViewRangeInputMoney() {
        System.out.println(INPUT_MONEY_ERROR);
    }

    public static void verificationViewStringToLong() {
        System.out.println(STRING_TO_LONG_ERROR);
    }

    public static void verificationViewRangeLottoNumber() {
        System.out.println(LOTTO_NUMBER_RANGE_ERROR);
    }

    public static void verificationViewLengthLottoNumberList() {
        System.out.println(LOTTO_NUMBER_LENGTH_ERROR);
    }

    public static void verificationViewIntegerLottoNumberList() {
        System.out.println(LOTTO_NUMBER_INTEGER_ERROR);
    }
}

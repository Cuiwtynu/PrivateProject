package InputConstraint;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AnswerOfInt {
    public static int Number() {// 保证输入的一定是数字
        int tag;
        Scanner sc = new Scanner(System.in);
        try {
            tag = sc.nextInt();
        } catch (InputMismatchException in) {
            System.out.println("您输入的不是数字：");
            tag = Number();
        }
        return tag;
    }

    public static int RangeOfNumber(int front, int rear) {// 保证输入的一定是一定范围内的数字
        int tag = Number();
        if (tag < front || tag > rear) {
            try {
                throw new InPutException("请输入" + front + "至" + rear + "之间的数:");
            } catch (InPutException e) {
                // TODO Auto-generated catch block
                System.out.println("请输入" + front + "至" + rear + "之间的数:");
                tag = RangeOfNumber(front, rear);
            }
        }
        return tag;
    }
}

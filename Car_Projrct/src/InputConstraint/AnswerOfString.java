package InputConstraint;

import java.util.Scanner;

public class AnswerOfString {
    public static String getString2() {// 获得正确的字符串
        Scanner sc = new Scanner(System.in);
        String name;
        name = sc.next().trim();
        if (name == null || name.length() == 0) {
            try {
                throw new InPutException("输入不能为空");
            } catch (InPutException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                System.out.println("输入不能为空:");
                name = getString2();
            }
        }
        return name;
    }
}

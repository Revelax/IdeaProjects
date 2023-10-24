import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scan =new Scanner(System.in);


    public static void main(String[] args) {
        String primer;
        char arif=' ';

        String oper="+-*/";
        String arab="0123456789";
        String rim="IXV";
        while (true){
            System.out.println("Введи выражение типа (arg1+arg2), ");
            System.out.println("допустимы римские и арабские цифры ");
            System.out.println("и следующие операции (+-/*)");

            System.out.println("Input:");
            int countA=0; //счетчик арабских цифр в строке
            int countR=0; //счетчик римских цифр в строке
            int countS=0; //счетчик арифметических операций в строке
            primer=scan.nextLine(); //ввод примера пользователем
            primer=primer.trim();
            //определение в строке наличия арабских, римских цифр и арифметической операции
            for (int i=0;i<primer.length();i++){
                if (arab.indexOf(primer.charAt(i))>=0) {
                    countA++;
                } else if (rim.indexOf(primer.charAt(i))>=0) {
                    countR++;
                } else if (oper.indexOf(primer.charAt(i))>=0) {
                    countS++;
                    arif=primer.charAt(i);
                }
            }

            if ((countA!=0)&&(countS==1)&&(countR==0)){         //вычисление из арабских чисел
                System.out.println("Output:");
                System.out.println(calc(primer,arif));

            } else if ((countA==0)&&(countS==1)&&(countR!=0)) { //вычисление из римских чисел
                System.out.println("Output:");
                System.out.println(arabToRim(calc(rimToArab(primer,arif),arif)));

            }else{
                throw new InputMismatchException("Не правильный ввод");
            }

        }
    }
    // метод выполняющий арифметические операции
    private static String calc (String stroka, char arifm){
        String []arrayPrimer;
        arrayPrimer=stroka.split("[+-/*]");
        String numb1 = arrayPrimer[0].trim();
        String numb2 = arrayPrimer[1].trim();
        if ((Integer.parseInt(numb1)<11)&&(Integer.parseInt(numb2)<11)) {

            int res = 0;

            switch (arifm) {
                case '+':
                    res = Integer.parseInt(numb1) + Integer.parseInt(numb2);
                    //System.out.println(res);
                    break;
                case '-':
                    res = Integer.parseInt(numb1) - Integer.parseInt(numb2);
                    break;
                case '*':
                    res = Integer.parseInt(numb1) * Integer.parseInt(numb2);
                    break;
                case '/':
                    if (Integer.parseInt(numb2)!=0){
                    res = Integer.parseInt(numb1) / Integer.parseInt(numb2);
                    break;}else throw new InputMismatchException("НЕльзя делить на ноль");

                default:
                    throw new IllegalArgumentException("Не верный знак операции");
            }


            return String.valueOf(res);
        }else {
            throw new InputMismatchException("Вы ввели числа больше 10");
        }
    }
    //метод перевода результата из арабских цифр в римские
    private static String arabToRim (String stroka) {
        String[] rim = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        for (int i=0;i<stroka.length();i++){
            if (stroka.charAt(i)=='-'){
                throw new InputMismatchException("Римское число меньше 0");
            }else if((stroka.charAt(i)=='0')&&(stroka.length()==1)){
                throw new ArithmeticException("Недопустимый результат в римских цифрах");
            }
        }
        int num=Integer.parseInt(stroka);
        return rim[num-1];
    }
    //метод перевода строки введенной пользователем из римских в арабские цифры
    private static String rimToArab(String stroka, char arifm){
        String[] arrayPrimer;
        String[] arrayRim={"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        arrayPrimer=stroka.split("[+-/*]");
        String numb1 = arrayPrimer[0].trim();
        String numb2 = arrayPrimer[1].trim();
        int num1=0;
        int num2=0;
        for (int i=0;i<arrayRim.length;i++){
           if (arrayRim[i].equals(numb1)){
             num1=i+1;
           }
            if (arrayRim[i].equals(numb2)){
                num2=i+1;
            }

        }
        return  String.valueOf(num1)+arifm+String.valueOf(num2);

    }
}
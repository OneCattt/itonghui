package 策略模式;

/*
加法
 */
public class OperationAdd implements Strategy {

  @Override
  public int doOperation(int num1, int num2) {
    return num1 + num2;
  }
}

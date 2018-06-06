package 策略模式;

public class Demo {

  public static void main(String[] args) {
    Context context=new Context(new OperationMul());
    System.out.println(context.excuteStrategy(1,2));

  }

}

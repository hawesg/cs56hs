import java.util.Random;
public class RandomTest{
	public RandomTest(){
		Random random = new Random();
		State state = State.O;
		State state2 = (state==State.O)?State.X:State.O;
		System.out.println(state);
		System.out.println((state==State.O)?State.X:State.O);
		for (int i=0; i<=10;i++){
			int randomInt = random.nextInt(2);
			boolean x = (random.nextInt(2)==1)?true:false;
		}
	}
	public static void main(String [] args){
		RandomTest x = new RandomTest();
	}
}
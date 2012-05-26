import java.util.Random;
public class RandomTest{
	public RandomTest(){
		Random random = new Random();
		for (int i=0; i<=10;i++){
			int randomInt = random.nextInt(2);
			boolean x = (random.nextInt(2)==1)?true:false;
			System.out.println(randomInt);
			System.out.println(x);
		}
	}
	public static void main(String [] args){
		RandomTest x = new RandomTest();
	}
}
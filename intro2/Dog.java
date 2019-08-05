public class Dog {
	public int weightInPounds;
	public static String binomen = "Canis familiaris";

	/** One integer constructor for dogs. */
	public Dog(int w) {
		weightInPounds = w;
	}

	public void makeNoise() {
		if (weightInPounds < 10) {
			System.out.println("yip!");
		} else if (weightInPounds < 30) {
			System.out.println("bark.");
		} else {
			System.out.println("woooof!");
		}
	}

	public static Dog maxDog(Dog d1, Dog d2) {
		if (weightInPounds > d2.weightInPounds) {
			return this;
		}
		return d2;
	}	
	
	public static void main(String[] args) {
		Dog d1 = new Dog(10);
		Dog d2 = new Dog(20);
		System.out.println(Dog.maxDog(d1, d2).weightInPounds);
	}


} 
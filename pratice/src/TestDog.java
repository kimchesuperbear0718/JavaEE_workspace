public class TestDog {

    public static  void main(String[] args) {

        Dog inu = new Dog();

        Dog dog2 = new Dog();

        inu.bark();
        dog2.bark();
        
        inu.name = "바둑이";
        dog2.name = "초코";

        String name = inu.name;
        System.out.println(name);

        String name2 = dog2.name;
        System.out.println(name2);
        
    }
}
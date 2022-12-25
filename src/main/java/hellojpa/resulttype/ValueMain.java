package hellojpa.resulttype;

public class ValueMain {
    public static void main(String[] args) {
        Address address1 = new Address("city", "street", "10000");
        Address address2 = new Address("city", "street", "10000");

        //equals의 기본 함수는 == 비교이기 때문에 false가 나온다
        //이를 해결하기 위해 equals , hashCode를 override 해줘야 한다.
        System.out.println("Address 1 equals 2 : " + (address1.equals(address2)));

    }
}

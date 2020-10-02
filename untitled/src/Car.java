public class Car {
    public static Car getMax(Car[] cars) {
        Car fastesCar = null;
        for (int i = 1; i < cars.length; i++) {
            if (cars[i - 1].maxSpeed > cars[i].maxSpeed) {
                fastesCar = cars[i - 1];
            } else if (cars[i].maxSpeed > cars[i - 1].maxSpeed) {
                fastesCar = cars[i];
            } else if (cars[i].maxSpeed == cars[i - 1].maxSpeed) {
                if (cars[i].accelleration > cars[i - 1].accelleration) {
                    fastesCar = cars[i];
                } else {
                    fastesCar = cars[i - 1];
                }
            }
        }
        return fastesCar;
    }

    ////DO NOT MODIFY ANYTHING BELOW THIS LINE

    int maxSpeed;
    int accelleration;

    public Car(int maxSpeed, int accelleration) {
        this.maxSpeed = maxSpeed;
        this.accelleration = accelleration;
    }

    @Override
    public String toString() {
        return "Car with maxSpeed=" + maxSpeed + " and accelleration=" + accelleration;
    }

}
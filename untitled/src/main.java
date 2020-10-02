public class main {

    public static void main(String[] args) {

        main na = new main();
        System.out.println(na.calculate(5));
    }

    public int calculate(int index) {
        int[] array = new int[index + 1];

        for(int i = 0; i < array.length ; i++){
            if(i < 3){
                array[i] = i;
            }else {
                array[i] = array[i - 3] + array[i - 2] + array[i - 1];
            }
        }

        return array[index];
    }
}


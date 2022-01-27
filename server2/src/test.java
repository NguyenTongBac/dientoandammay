public class test {
        public static void main (String[] args) {
            Database bd = new Database();
            
            bd.insertData("123", "123", "123", "bac", "123");
            String s = bd.getData();
            System.out.println(s);
            
        }
}

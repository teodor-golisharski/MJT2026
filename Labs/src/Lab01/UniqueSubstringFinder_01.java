package Lab01;

public class UniqueSubstringFinder_01 {
    public static String longestUniqueSubstring(String s) {
        int max = 0;
        StringBuilder sb = new StringBuilder();
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int currentIndex = sb.indexOf(String.valueOf(c));

            if (currentIndex != -1) {
                sb.delete(0, currentIndex + 1);
            }
            sb.append(c);

            if(sb.length() > max){
                max = sb.length();
                res = sb.toString();
            }

        }
        return res;
    }

    public static void main() {
        String s = longestUniqueSubstring("abcabcbb");
        System.out.println(s);
    }

}

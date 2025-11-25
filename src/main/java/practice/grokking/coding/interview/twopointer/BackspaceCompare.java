package practice.grokking.coding.interview.twopointer;

public class BackspaceCompare {

    public static boolean compare(String str1, String str2) {
        int index1 = str1.length() - 1, index2 = str2.length() - 1;
        while (index1 >= 0 || index2 >= 0) {
            int i1 = getValidNextCharIndex(str1, index1);
            int i2 = getValidNextCharIndex(str2, index2);

            if (i1 < 0 && i2 < 0) {
                return true;
            }
            if (i1 < 0 || i2 < 0) {
                return false;
            }
            if (str1.charAt(i1) != str2.charAt(i2))
                return false;

            index1 = i1 - 1;
            index2 = i2 - 1;

        }
        return true;
    }

    private static int getValidNextCharIndex(String str, int index) {
        int backSpaceCount = 0;

        while (index >= 0) {
            if (str.charAt(index) == '#') {
                backSpaceCount++;
            } else if (backSpaceCount > 0)
                backSpaceCount--;
            else
                break;

            index--;
        }

        return index;
    }

    public static void main(String[] args) {
        System.out.println(BackspaceCompare.compare("bxj##tw", "bxo#j##tw"));
        System.out.println(BackspaceCompare.compare("ab##", "c#d#"));
        System.out.println(BackspaceCompare.compare("xy#z", "xzz#"));
        System.out.println(BackspaceCompare.compare("xy#z", "xyz#"));
        System.out.println(BackspaceCompare.compare("xp#", "xyz##"));
        System.out.println(BackspaceCompare.compare("xywrrmp", "xywrrmu#p"));
    }
}

package practice.cracking.coding.interview.arrays;

public class URLify {

    public void replaceSpaces(char[] ch, int trueLength) {
        int spaceCount = spaceCount(ch, trueLength);
        int charIndex = trueLength;// str.length() - spaceCount * 2;
        int index = trueLength + 2 * spaceCount;//ch.length();

        for (int i = charIndex - 1; i >= 0; i--) {
            if (ch[i] == ' ') {
                ch[index - 1] = '0';
                ch[index - 2] = '2';
                ch[index - 3] = '%';
                index = index - 3;
            } else {
                ch[index - 1] = ch[i];
                index--;
            }
        }

    }

    public int spaceCount(char[] ch, int trueLength) {
        int spaceCount = 0;
        for (int i = 0; i < trueLength; i++) {
            if (ch[i] == ' ') {
                spaceCount++;
            }
        }
        return spaceCount;
    }

    public static void main(String[] args) {
        URLify ur = new URLify();
        String str = "Mr John Smith hgg jhj hjhj jhjvjhv jhvhjvhj              ";
        char ch[] = str.toCharArray();
        ur.replaceSpaces(ch, 43);
        //System.out.println(Arrays.toString(ch));
        System.out.println(new String(ch));
    }
}

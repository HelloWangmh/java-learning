package wang.mh.base.io;


import java.io.*;

/**
 * DataOutput,DataInput   可以将数据以字节读取,写入
 * 子类:DataOutputStream,DataInputStream
 * RandomAccessFile实现了DataOutput和DataInput    可以根据seek()方法指定读取/写入的位置
 *
 */
public class DataInputOutputDemo {

    public static final String FILE_PATHH = "src/main/resources/io/user.dat";

    public static void main(String[] args) throws Exception {

        User[] users = new User[]{new User("wmh", 18, 888.0),
                                  new User("wang mh", 8, 8888.0),
                                  new User("hello,world!", 28, 88888.0)};
        //write
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(FILE_PATHH))){
            for (User user : users) {
                DataIo.writeFixedString(user.getName(), User.NAME_SIZE, out);
                out.writeInt(user.getAge());
                out.writeDouble(user.getMoney());
            }
        }

        //read
        try (RandomAccessFile in = new RandomAccessFile(FILE_PATHH, "r")){
            int num = (int)in.length() / (User.TOTAL_SIZE);
            for (int i = 0; i < num; i++) {
                String name = DataIo.readFixedString(User.NAME_SIZE, in);
                int age = in.readInt();
                double money = in.readDouble();
                User u = new User(name, age, money);
                System.out.println(u);
            }
        }

    }
}




class DataIo{

    public static void writeFixedString(String s, int size, DataOutput out)
            throws IOException {
        for (int i = 0; i < size; i++) {
            char ch = 0;
            if (i < s.length()) ch = s.charAt(i);
            out.writeChar(ch);
        }
    }

    public static String readFixedString(int size, DataInput in) throws IOException {
        StringBuilder s = new StringBuilder(size);
        int i = 0;
        boolean more = true;
        while (more && i < size) {
            char c = in.readChar();
            i++;
            if (c == 0) more = false;
            else s.append(c);
        }
        //a char  is 2 byte
        in.skipBytes(2 * (size - i));
        return s.toString();
    }
}

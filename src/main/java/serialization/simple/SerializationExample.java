package serialization.simple;

import java.io.*;
import java.math.BigDecimal;

public class SerializationExample {

    public static void main(String[] args) {

        Person person = new Person("apple", 31, new BigDecimal(777));

        byte[] bytes = convertObjectToBytes(person); // Serialization

        Person p = (Person) convertBytesToObject(bytes);

        System.out.println(p);
    }

    // Convert object to byte[]
    public static byte[] convertObjectToBytes(Object obj) {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            return boas.toByteArray();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        throw new RuntimeException();
    }

    // Convert byte[] to object
    public static Object convertBytesToObject(byte[] bytes) {
        InputStream is = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
        throw new RuntimeException();
    }

}

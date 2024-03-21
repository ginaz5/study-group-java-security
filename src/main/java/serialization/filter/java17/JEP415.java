package serialization.filter.java17;

import javax.swing.*;
import java.io.*;
import java.util.function.BinaryOperator;

public class JEP415 {

    public static void main(String[] args) throws IOException {

        byte[] bytes = convertObjectToStream(new JComponentExample());
        InputStream is = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(is);

        // Java 9
        ois.setObjectInputFilter(createObjectFilter());

        // Java 17
        ObjectInputFilter jComponentFilter = ObjectInputFilter.rejectFilter(
                JComponent.class::isAssignableFrom,
                ObjectInputFilter.Status.UNDECIDED);
        ois.setObjectInputFilter(jComponentFilter);

        try {
            Object obj = ois.readObject();
            System.out.println("Read object: " + obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // reject all JComponent classes
    private static ObjectInputFilter createObjectFilter() {
        return filterInfo -> {
            Class<?> clazz = filterInfo.serialClass();
            if (clazz != null) {
                return (JComponent.class.isAssignableFrom(clazz))
                        ? ObjectInputFilter.Status.REJECTED
                        : ObjectInputFilter.Status.ALLOWED;
            }
            return ObjectInputFilter.Status.UNDECIDED;
        };
    }

    private static byte[] convertObjectToStream(Object obj) {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            return boas.toByteArray();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        throw new RuntimeException();
    }

}

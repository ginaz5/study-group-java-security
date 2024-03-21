package serialization.simple;

import java.io.Serializable;
import java.math.BigDecimal;

public record Person (String name, int age, BigDecimal salary) implements Serializable { }

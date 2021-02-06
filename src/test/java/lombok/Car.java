package lombok;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    private String model;
    private String version;
    private int speed;
}

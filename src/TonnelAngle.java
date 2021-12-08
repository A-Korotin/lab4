// enum для наколна тоннеля. Имеет 3 состояния
public enum TonnelAngle {
    steep(101), average(127), flat(103);

    private final int code;
    // Конструктор, присваивает код состояния
    TonnelAngle(int code) {
        this.code = code;
    }

    // Getter, возвращает код состояния
    public int getCode() {
        return code;
    }
}

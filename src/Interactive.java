// Интерфейс для предмета, объекта взаимодействия
public interface Interactive {
    // Getter, возвращает имя объекта
    String getName();
}

// Класс тоннеля, объекта взаимодействия
class Tonnel implements Interactive {
    private  final String name = "тоннель";

    private  TonnelAngle angle = TonnelAngle.flat;

    private final TonnelFloor floor = new TonnelFloor();

    // Getter, возвращает имя объекта
    public String getName() {
        return name;
    }

    // метод для увеличения наклона тоннеля, возможно исколючение LeanOverflow при попытке наклона тоннеля с TonnelAngle.steep
    public String lean() throws Exception{
        return floor.lean();
    }

    // Getter, возвращает наклон тоннеля
    public TonnelAngle getAngle() {
        return angle;
    }

    private class TonnelFloor {

        private final String name = "ледяное дно " + Tonnel.this.name;

        public String lean() throws Exception {
            switch (Tonnel.this.angle) {
                case flat -> {
                    Tonnel.this.angle = TonnelAngle.average;
                    return this.name + " " + " шло под уклон";
                }
                case average -> {
                    Tonnel.this.angle = TonnelAngle.steep;
                    return "Наклон " + Tonnel.this.name + " стал слишком крутым";
                }
                case steep -> throw new LeanOverflow("Наклон тонееля был steep, больше наклоняться некуда");
            }
           return "";
        }
    }


    // Метод для сравнения двух объектов
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return !(other == null || getClass() != other.getClass());
    }

    // Метод для преставления объекта в строковом виде
    @Override
    public String toString() {
        return "Большой и страшный " + name;
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return name.hashCode() * 127 * angle.getCode();
    }

}
// Класс льда, объекта взаимодействия
class Ice implements Interactive {
    private final String name;

    // Конструктор, устанавливает имя объекта
    Ice(String name) {
        this.name = name == null ? "NoName" : name;
    }

    // Getter, возвращает имя объекта
    public String getName() {
        return name;
    }

    // Метод для сравнения двух объектов
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        return name.equals(((Ice) other).name);
    }

    // Метод для преставления объекта в строковом виде
    @Override
    public String toString() {
        return name + ". Холодный...";
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return name.hashCode() * 127;
    }
}

// Класс для веревки, объекта взаимодействия
class Rope implements Interactive {
    private final String name;

    // Конструктор, устанавливает имя объекта
    public Rope(String name) {
        this.name = name == null ? "NoName" : name;
    }

    // Getter, возвращает имя объекта
    public String getName() {
        return name;
    }

    // Метод для сравнения двух объектов
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        return name.equals(((Rope) other).name);
    }

    // Метод для преставления объекта в строковом виде
    @Override
    public String toString() {
        return "Крепкая крутая " + name;
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return name.hashCode() * 127;
    }

}
// Класс пояса, объекта взаимодействия
class Belt implements Interactive {
    private final String name;

    // Конструктор, устанавливает имя объекта
    public Belt(String name) {
        this.name = name == null ? "NoName" : name;
    }

    // Getter, возвращает имя объекта
    public String getName() {
        return name;
    }

    // Метод для сравнения двух объектов
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        return name.equals(((Belt) other).name);
    }

    // Метод для преставления объекта в строковом виде
    @Override
    public String toString() {
        return "Кожаный модный " + name;
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return name.hashCode() * 127;
    }
}
// Класс мотка шнура, объекта взаимодействия
class NylonCordSkein implements Interactive {
    private final String name;

    // Конструктор, устанавливает имя объекта
    public NylonCordSkein(String name) {
        this.name = name == null ? "NoName" : name;
    }

    // Getter, возвращает имя объекта
    public String getName() {
        return name;
    }

    // Метод для сравнения двух объектов
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        return name.equals(((NylonCordSkein) other).name);
    }

    // Метод для преставления объекта в строковом виде
    @Override
    public String toString() {
        return name;
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return name.hashCode() * 127;
    }

}
// Класс ледяных ступеней, объекта взаимодействия
class IceSteps implements Interactive {
    private final String name;

    // Конструктор, устанавливает имя объекта
    public IceSteps(String name) {
        this.name = name == null ? "NoName" : name;
    }

    // Getter, возвращает имя объекта
    public String getName() {
        return name;
    }

    // Метод для сравнения двух объектов
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        return name.equals(((IceSteps) other).name);
    }

    // Метод для преставления объекта в строковом виде
    @Override
    public String toString() {
        return "Скользкие мокрые " + name;
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return name.hashCode() * 127;
    }
}
// Класс альпенштока, объекта взаимодействия
class Alpenstock implements Interactive {
    private final String name;
    {
        name = "альпеншток";
    }

    // Getter, возвращает имя объекта
    public String getName() {
        return name;
    }

    private final SpearHead spearHead = new SpearHead();

    // Метод, чтобы astronaut принялся упираться альпенштоком в лёд
    public String pushUp(Astronaut astronaut) {
        return astronaut.name + spearHead.pushUp();
    }

    private class SpearHead {
        final String name = "острие " + Alpenstock.this.name;

        String pushUp() {
            return " принялся упираться " + name + " в лёд";
        }
    }
}

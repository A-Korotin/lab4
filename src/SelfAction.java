import java.util.*;

// Интерфейс для действий, совершаемых астроновтом самим над собой
public interface SelfAction {
    //выполнить действие
    String preform(Astronaut astronaut);
}

// Действие связать меж собой все мотки шнура
class TieSkeins implements SelfAction {

    // метод для выполнения действия. Если у astronaut имелись мотки шнура, то он получает длинную веревку
    public String preform(Astronaut astronaut) {
        StringBuilder msg = new StringBuilder();
        List<Interactive> skeins = new ArrayList<>();

        for(Interactive skeinSuspect: astronaut.equipment) {
            if (skeinSuspect instanceof NylonCordSkein) {
                msg.append(astronaut.act(new TieSkein(), skeinSuspect)).append(". ");
                skeins.add(skeinSuspect);
            }

        }
        Rope rope = new Rope("длинная веревка");
        if (skeins.size() != 0)
            astronaut.equipment.add(rope);

        astronaut.equipment.removeAll(skeins);

        return skeins.size() != 0 ? (msg.append("Получилась ").append(rope.getName())).toString() :
                "У " + astronaut.name + " мотков не оказалось, веревки не будет...";
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
        return "Действие связать мотки";
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode() * 127;
    }
}

// Действие упасть и катиться вниз
class FallAndRollDown implements SelfAction {
    // метод для выполнения действия
    public String preform(Astronaut astronaut) {
        return astronaut.name + " упал на спину и покатился вниз";
    }
}

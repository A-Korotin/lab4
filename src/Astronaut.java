import java.util.*;

// Абстрактный класс для всех астронавтов. Содержит в себе функционал для выполнения действий, переименования
public abstract class Astronaut {
    protected String name;
    protected List<Interactive> equipment = new ArrayList<>();

    // метод для исполнения дейстия Action данным астронавтом над interactive
    public String act(Action action, Interactive interactive) {
        return action.preform(this, interactive);
    }

    // метод для исполнения действия SpecialAction данным астронавтом над астронавтом subject
    public String act(SpecialAction action, Astronaut subject) {
        return action.preform(this, subject);
    }

    // метод для исполнения действия SelfAction самим над собой
    public String act(SelfAction action) {
        return action.preform(this);
    }

    // метод для переименования астронавта
    public void rename(String newName) {
        if (newName != null)
            name = newName;
    }

    // Метод для сравнения двух объектов
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Astronaut astronaut = (Astronaut) other;

        if (equipment.size() != astronaut.equipment.size())
            return false;

        return name.equals(astronaut.name) && equipment.containsAll(astronaut.equipment);
    }

    // Метод для преставления объекта в строковом виде
    @Override
    public String toString() {
        return "Астронавт " + name;
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        int result = name.hashCode() * 127;

        for(Interactive i: equipment)
            result *= i.hashCode() * 127;

        return result;
    }
}

// Класс астронавта Знайки. Содержит в себе состояние, необходимое для выполнение определенной логики объектной модели
class Znayka extends Astronaut {
    {
        name = "Знайка";
        equipment.add(new NylonCordSkein("моток"));
        equipment.add(new NylonCordSkein("моточек поменьше"));
        equipment.add(new NylonCordSkein("самый маленький моток"));
    }
}

// Класс астронавта Стекляшкина. Содержит в себе состояние, необходимое для выполнение определенной логики объектной модели
class Steklyashkin extends Astronaut {
    {
        name = "Стекляшкин";
        equipment.add(new Belt("пояс"));
    }
}

// Класс для всех второстепенных астронавтов модели
class Companion extends Astronaut {

    // Конструктор, устанавливает имя астронавта и добавляет объект Interactive для корректного выполнения логики программы
    public Companion(String name) {
        this.name = name == null ? "NoName" : name;
        equipment.add(new Belt("пояс"));
    }

}



// Интерфейс для действий, совершаемых астронавтом над предметом

public interface Action {
    //выполнить действие
    String preform(Astronaut astronaut, Interactive interactive);
}

// Действие проверки наклона тоннеля
class CheckAngle implements Action {

    // Метод для выполнения действия
    public String preform(Astronaut astronaut, Interactive interactive) {
        if (!(interactive instanceof Tonnel))
            return "Невозможно проверить наклон у " + interactive.getName();

        return switch (((Tonnel) interactive).getAngle()) {
            case flat -> astronaut.name + " продолжил спуск";
            case steep -> astronaut.name + " побоялся продолжить спуск";
            case average -> astronaut.name + " продолжил спуск с опасением";
        };
    }

    // Метод для сравнения двух объектов
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return !(other == null || getClass() != other.getClass());
    }
    // Метод для предствления объекта в строковом виде
    @Override
    public String toString() {
        return "Действие проверить наклон тоннеля";
    }
    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode() * 127;
    }
}

// Действие вырубить ступени
class CutStairs implements Action {

    // Метод для выполнения действия
    public String preform(Astronaut astronaut, Interactive interactive) {
        return astronaut.name + " вырубает ступеньки в " + interactive.getName();
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
        return "Действие вырубить ступени";
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode() * 127;
    }
}

// Действие связать мотки между собой
class TieSkein implements Action {

    // Метод для выполнения действия
    public String preform(Astronaut astronaut, Interactive interactive) {
        return astronaut.name + " связывает " + interactive.getName();
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
        return "Действие связать мотки между собой";
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode() * 127;
    }
}

// Действие стоять на чем-либо
class Stand implements Action {

    // Метод для выполнения действия
    public String preform(Astronaut astronaut, Interactive interactive) {
        return astronaut.name + " стоит на " + interactive.getName();
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
        return "Действие стоять";
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode() * 127;
    }
}

// Действие постепенно опускать
class LowerRope implements Action {

    // Метод для выполнения действия
    public String preform(Astronaut astronaut, Interactive interactive) {
        return astronaut.name + " постепенно опускает " + interactive.getName();
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
        return "Действие постепенно опускать верёвку";
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode() * 127;
    }
}

// Действие внимательно следить
class SuperviseRope implements Action {

    // Метод для выполнения действия
    public String preform(Astronaut astronaut, Interactive interactive) {
        String msg = astronaut.name + " внимательно следит, чтобы " + interactive.getName() + " не выскользнула из ручонок. ";
        return astronaut.equipment.contains(interactive) ? msg + interactive.getName() + " все еще в руках " + astronaut.name :
                msg + astronaut.name + " потерял " + interactive.getName() + "... Болван";
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
        return "Действие внимательно следить за верёвкой";
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode() * 127;
    }
}

// Действие поскользнуться
class Slip implements Action {

    // Метод для выполнения действия
    public String preform(Astronaut astronaut, Interactive interactive) {
        return astronaut.name + " поскользнулся на " + interactive.getName();
    }
}

// Действие потащить за собой
class Stretch implements Action {

    // Метод для выполнения действия
    public String preform(Astronaut astronaut, Interactive interactive) {
        return interactive.getName() + " натянулась и потащила за собой " + astronaut.name;
    }
}

// Действие упереть Альпешток
class PushUp implements Action {

    // Метод для выполнения действия
    public String preform(Astronaut astronaut, Interactive interactive) {
        if (!(interactive instanceof Alpenstock))
            return "Не выйдет";

        return ((Alpenstock) interactive).pushUp(astronaut);
    }
}
// Интерфейс для действий, совершаемых одним астроноватом над другим
public interface SpecialAction {
    // выполнение действия
    String preform(Astronaut object, Astronaut subject);
}

// Действие - приказ вырубить ступеньки
class OrderToCutStairs implements SpecialAction {

    // метод для выполнения действия
    public String preform(Astronaut object, Astronaut subject) {
        return object.name + " приказал " + subject.name + " вырубить ступеньки";
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
        return "Приказ вырубить ступени";
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode() * 127;
    }
}

// Действие прикрепить экипировку одного астронавта к экипировке другого
class AttachEquipment implements SpecialAction {

    // метод для выполнения действия
    public String preform(Astronaut object, Astronaut subject) {
        Interactive objectI;
        Interactive subjectI;
        try {
            objectI = object.equipment.get(0);
            subjectI = subject.equipment.get(0);
        }
        catch (RuntimeException e) {
            return "Упс... Прикрепить не получится (" + e.getMessage() + ")";
        }

        return object.name + " прикрепил " + objectI.getName() + " к " +
                subjectI.getName() + " " + subject.name;
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
        return "Действие прикрепления";
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode() * 127;
    }
}

// Действие - приказ осторожно спускаться вниз
class OrderToDescend implements SpecialAction {

    // метод для выполнения действия
    public String preform(Astronaut object, Astronaut subject) {
        return object.name + " велел " + subject.name + " осторожно спускаться вниз";
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
        return "Приказ к спуску";
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode() * 127;
    }
}
// Действие выполнения приказа об осторожном спуске
class ExecuteOrderToDescent implements SpecialAction {

    public String preform(Astronaut object, Astronaut subject) {
        return object.name + " осторожно спускается вниз по приказу " + subject.name;
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
        return "Действие осторожно спускаться";
    }

    // Метод для вычисления хэш-кода объекта
    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode() * 127;
    }
}

// Действие - приказ связаться веревкой
class OrderToTieWithRope implements SpecialAction {

    // метод для выполнения действия
    public String preform(Astronaut object, Astronaut subject) {
        return object.name + " велел " + subject.name +
                " свзяаться веревкой, как это делают альпинисты, переходя через ледники";
    }
}
// Действие подтащить в себе
class Pull implements SpecialAction {

    // метод для выполнения действия
    public String preform(Astronaut object, Astronaut subject) {
        return object.name + " подтащил к себе на веревке " + subject.name;
    }
}

// Действие - приказ привязать
class OrderToTie implements SpecialAction {

    // метод для выполнения действия
    public String preform(Astronaut object, Astronaut subject) {
        return object.name + " распорядился, чтобы " + subject.name +
                " привязали позади всех и не разрешали выползать вперед";
    }
}

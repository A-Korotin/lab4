import java.util.*;

// Исключение неверной команды
class InvalidCommand extends RuntimeException {
    public InvalidCommand(String command) {
        super(String.format("Command \"%s\" not found. Type \"help\" for more info", command));
    }
}

// Искоючение неверного фалага команды
class InvalidFlag extends RuntimeException {
    public InvalidFlag(CommandType type, String flag) {
        super(String.format("Invalid flag \"%s\" for command %s. Type \"%s -h\" for more info",
                flag, type.getName(), type.getName()));
    }
}

// Исключение пустого ввода
class EmptyInput extends RuntimeException {
    public EmptyInput(){
        super("Invalid empty input");
    }
}

// Исключение неверного числа параметров
class InvalidParameterNumber extends RuntimeException {
    public InvalidParameterNumber(int expected, int received) {
        super(String.format("Invalid parameter number: expected %d, received %d", expected, received));
    }
}

// Исключение не найденного имени астронавта
class AstronautNotFound extends RuntimeException {
    public AstronautNotFound(String name) {
        super(String.format("Astronaut \"%s\" not found.", name));
    }
}

// Исключение при попытке наколнить тоннель с TonnelAngle.steep
class LeanOverflow extends Exception {
    public LeanOverflow(String msg) {
        super(msg);
    }
}

// enum типа команды
enum CommandType {
    astro("astro"), stop("stop"), help("help");

    // Статческий метод, возвращает тип команды, принимая ее имя.
    // Может возникнуть InvalidCommand - RuntimeException при неверном имени команды
    public static CommandType getType(String name) throws RuntimeException {
        return switch (name) {
            case "astro" -> CommandType.astro;
            case "stop" -> CommandType.stop;
            case "help" -> CommandType.help;
            default -> throw new InvalidCommand(name);
        };
    }

    private final String name;

    // Конструктор, устанавливает имя команды
    CommandType(String name) {
        this.name = name;
    }

    // Getter, возвращает имя команды
    public String getName() {
        return name;
    }
}

// Класс обработки и исполнения команд из командной строки
class CommandFactory {
    private final InputHandler inputHandler = new InputHandler();

    private Command getCommand() throws RuntimeException {
        List<String> args = inputHandler.scanForInput();
        String command = args.get(0);
        args.remove(0);
        return switch (CommandType.getType(command)) {
            case astro -> new astro(args);
            case stop -> new stop(args);
            case help -> new help(args);
        };
    }

    // основной цикл обработки команд
    public void mainLoop(List<Astronaut> astronauts) {
        while (true) {
            try {
                Command command = getCommand();
                command.preform(astronauts);
                if (command instanceof stop)
                    break;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again.");
            }
        }
    }

    private static class InputHandler {
        private final Scanner scanner = new Scanner(System.in);

        public List<String> scanForInput() throws RuntimeException{
            String input = scanner.nextLine();
            if (input.isBlank())
                throw new EmptyInput();

            return new ArrayList<>(List.of(input.split(" ")));
        }
    }
}

// Абстрактный класс для всех команд
public abstract class Command {
    protected final List<String> args = new ArrayList<>();

    // абстрактный метод исполнения команды
    public abstract void preform(List<Astronaut> astronauts) throws RuntimeException;
}

// команда astro
class astro extends Command {
    private final astroFlag type;

    // конструктор. Устанавливает флаг команды.
    // Может возникнуть RuntimeException InvalidFlag, если будет передан несуществующий флаг команды
    public astro(List<String> args) throws RuntimeException {
        if (args.size() == 0)
            throw new InvalidFlag(CommandType.astro, "%no flag%");
        this.type = astroFlag.getType(args.get(0));
        args.remove(0);
        this.args.addAll(args);
    }

    // метод для выполнения команды. Могут возникунть 2 типа RuntimeException:
    // InvalidParameterNumber - если для команды было передано недопустимое кол-во параметров
    // AstronautNotFound - если астронавта с заданным именем нет в списке астронавтов
    public void preform(List<Astronaut> astronauts) throws RuntimeException {
        if(args.size() != type.getParamNumber())
            throw new InvalidParameterNumber(type.getParamNumber(), args.size());

        switch (type) {
            case add:
                astronauts.add(new Companion(args.get(0)));
                System.out.println("Astronaut added successfully.");
                return;
            case rename:
                int targetIndex = -1;
                for (int i = 0; i < astronauts.size(); ++i)
                    if(astronauts.get(i).name.equals(args.get(0)))
                        targetIndex = i;
                if (targetIndex == -1)
                    throw new AstronautNotFound(args.get(0));
                astronauts.get(targetIndex).rename(args.get(1));
                System.out.println("Astronaut renamed successfully.");
                return;
            case remove:
                targetIndex = -1;
                for (int i = 0; i < astronauts.size(); ++i)
                    if(astronauts.get(i).name.equals(args.get(0)))
                        targetIndex = i;
                if (targetIndex == -1)
                    throw new AstronautNotFound(args.get(0));
                astronauts.remove(targetIndex);
                System.out.println("Astronaut removed successfully.");
                return;
            case inspect:
                if(astronauts.size() == 0) {
                    System.out.println("%empty%");
                    return;
                }
                for(Astronaut a: astronauts)
                    System.out.println(a.name);
                return;
            case help:
                System.out.printf(" astro -add Name %s astro -rename OldName NewName %s astro -remove Name %s astro -inspect %s astro -h %s",
                        System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator());

        }
    }

    private enum astroFlag {
        add(1), remove(1), help(0), rename(2), inspect(0);

        public static astroFlag getType(String name) throws RuntimeException{
            return switch (name) {
                case "-add" -> astroFlag.add;
                case "-remove" -> astroFlag.remove;
                case "-h" -> astroFlag.help;
                case "-rename" -> astroFlag.rename;
                case "-inspect" -> astroFlag.inspect;
                default -> throw new InvalidFlag(CommandType.astro, name);
            };
        }

        private final int paramNumber;

        astroFlag(int paramNumber) {
            this.paramNumber = paramNumber;
        }

        public int getParamNumber() {
            return paramNumber;
        }
    }
}

// Команда stop, прекращает считывание команд с командной строки и начинает выполнение основной истории
class stop extends Command {

    // конструктор, принимает аргументы команды
    public stop(List<String> args){
        this.args.addAll(args);
    }

    // метод выполнения команды.
    // Может возникнуть RuntimeException InvalidParameterNumber, если было передано неверное кол-во параметров
    public void preform(List<Astronaut> astronauts) throws RuntimeException {
        if (args.size() != 0)
            throw new InvalidParameterNumber(0, args.size());
        System.out.println("Beginning of the story...");
    }
}

// команда help, выводит все возможные команды в консоль
class help extends Command {

    // конструктор, принимает аргументы команды
    public help(List<String> args) {
        this.args.addAll(args);
    }

    // метод выполнения команды.
    // Может возникнуть RuntimeException InvalidParameterNumber, если было передано неверное кол-во параметров
    public void preform(List<Astronaut> astronauts) {
        if (args.size() != 0)
            throw new InvalidParameterNumber(0, args.size());
        System.out.printf("Available commands: %s astro %s stop %s help %s",
                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator());
    }
}


import java.util.*;

public class Story {
    public static void main(String[] args) {

        class MessageHandler {
            private final static List<String> messages = new ArrayList<>();

            public static void logMessage(String msg) {
                messages.add(msg == null ? "" : msg);
            }

            public static void getAllMessages() {
                for(String m: messages)
                    System.out.println(m);
            }
            private MessageHandler() {}
        }

        Znayka znayka = new Znayka();
        Steklyashkin steklyashkin = new Steklyashkin();
        Astronaut klepka = new Companion("Клёпка");

        List<Astronaut> companions = new ArrayList<>();
        companions.add(new Companion("Кубик"));
        companions.add(new Companion("Звёздочкин"));
        companions.add(new Companion("Тюбик"));

        Tonnel tonnel = new Tonnel();
        Ice ice = new Ice("лёд");
        Rope oldRope = new Rope("веревка");
        Alpenstock alpenstock = new Alpenstock();
        IceSteps iceSteps = new IceSteps("ледяные ступеньки");

        interface SinglePieceOfText {
            String get();
        }

        SinglePieceOfText piece;
        piece = () -> "Это было сделано вовремя";

        CommandFactory factory = new CommandFactory();
        factory.mainLoop(companions);

        try {
            MessageHandler.logMessage(tonnel.lean());
        } catch (Exception e) {
            MessageHandler.logMessage(e.getMessage());
        }

        for (Astronaut a: companions)
            MessageHandler.logMessage(znayka.act(new OrderToTieWithRope(), a));

        MessageHandler.logMessage(piece.get());

        MessageHandler.logMessage(klepka.act(new Slip(), ice));
        MessageHandler.logMessage(klepka.act(new FallAndRollDown()));

        for (Astronaut a: companions)
            MessageHandler.logMessage(a.act(new Stretch(), oldRope));

        for (Astronaut a: companions)
            MessageHandler.logMessage(a.act(new PushUp(), alpenstock));


        SinglePieceOfText anotherPiece = new SinglePieceOfText() {
            public String get() {
                return "Это задержало падение.";
            }
        };

        MessageHandler.logMessage(anotherPiece.get());


        MessageHandler.logMessage(znayka.act(new Pull(), klepka));
        MessageHandler.logMessage(znayka.act(new OrderToTie(), klepka));
        try {
            MessageHandler.logMessage(tonnel.lean());
        } catch (Exception e) {
            MessageHandler.logMessage(e.getMessage());
        }
        MessageHandler.logMessage(znayka.act(new CheckAngle(), tonnel));

        for(Astronaut c: companions) {
            MessageHandler.logMessage(znayka.act(new OrderToCutStairs(), c));
            MessageHandler.logMessage(c.act(new CutStairs(), ice));
        }

        MessageHandler.logMessage(znayka.act(new TieSkeins()));
        MessageHandler.logMessage(znayka.act(new AttachEquipment(), steklyashkin));

        MessageHandler.logMessage(znayka.act(new OrderToDescend(), steklyashkin));
        MessageHandler.logMessage(steklyashkin.act(new ExecuteOrderToDescent(), znayka));

        for(Astronaut c: companions)
            MessageHandler.logMessage(c.act(new Stand(), iceSteps));

        Interactive rope = znayka.equipment.get(0);

        for(Astronaut c: companions) {
            c.equipment.add(rope);
            MessageHandler.logMessage(c.act(new LowerRope(), rope));
            MessageHandler.logMessage(c.act(new SuperviseRope(), rope));
        }

        MessageHandler.getAllMessages();

    }
}

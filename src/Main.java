public class Main {

    public static void main(String[] args) {

        if (args.length == 1 && args[0].equals("--demo")) {
            runDemo();
        } else {
            System.out.println("Use: java -cp out Main --demo");
        }
    }

    private static void runDemo() {

        int passed = 0;

        passed += testT1();
        passed += testT2();
        passed += testT3();
        passed += testT4();
        passed += testT5();

        System.out.println("SUMMARY: " + passed + "/5 PASS");
    }

    private static int testT1() {

        Shape circle = new Circle(
                "C1",
                2,
                new VectorRenderer()
        );

        String actual = circle.execute();
        String expected = "VECTOR circle radius=2";

        return printResult(
                "T1",
                "Circle + VectorRenderer",
                actual,
                expected
        );
    }

    private static int testT2() {

        Shape circle = new Circle(
                "C1",
                2,
                new RasterRenderer()
        );

        String actual = circle.execute();
        String expected = "RASTER circle radius=2";

        return printResult(
                "T2",
                "Circle + RasterRenderer",
                actual,
                expected
        );
    }

    private static int testT3() {

        Shape square = new Square(
                "S1",
                3,
                new VectorRenderer()
        );

        String actual = square.execute();
        String expected = "VECTOR square side=3";

        return printResult(
                "T3",
                "Square + VectorRenderer",
                actual,
                expected
        );
    }

    private static int testT4() {

        Shape square = new Square(
                "S1",
                3,
                new RasterRenderer()
        );

        String actual = square.execute();
        String expected = "RASTER square side=3";

        return printResult(
                "T4",
                "Square + RasterRenderer",
                actual,
                expected
        );
    }

    private static int testT5() {

        Circle circle = new Circle(
                "C1",
                2,
                new VectorRenderer()
        );

        Shape originalReference = circle;

        String before = circle.execute();
        String originalId = circle.getId();
        int originalRadius = circle.getRadius();

        circle.setImplementation(new RasterRenderer());

        Shape afterReference = circle;

        String after = circle.execute();

        boolean sameObject = originalReference == afterReference;
        boolean stateUnchanged =
                originalId.equals(circle.getId())
                        && originalRadius == circle.getRadius();

        boolean correctResult =
                before.equals("VECTOR circle radius=2")
                        && after.equals("RASTER circle radius=2");

        boolean passed =
                sameObject
                        && stateUnchanged
                        && correctResult;

        if (passed) {
            System.out.println(
                    "T5 PASS | sameObject=" + sameObject
                            + " | stateUnchanged=" + stateUnchanged
                            + " | before=" + before
                            + " | after=" + after
            );
            return 1;
        } else {
            System.out.println(
                    "T5 FAIL | sameObject=" + sameObject
                            + " | stateUnchanged=" + stateUnchanged
                            + " | before=" + before
                            + " | after=" + after
            );
            return 0;
        }
    }

    private static int printResult(
            String testId,
            String classes,
            String actual,
            String expected) {

        if (actual.equals(expected)) {
            System.out.println(
                    testId + " PASS | "
                            + classes
                            + " | result=" + actual
            );
            return 1;
        }

        System.out.println(
                testId + " FAIL | "
                        + classes
                        + " | actual=" + actual
                        + " | expected=" + expected
        );

        return 0;
    }
}
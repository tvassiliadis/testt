import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Main {

    public static final int FIVE_SECONDS = 5000;
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;


    public static void main(String... args) throws Exception {
        boolean even = false;
        long until = 0L;
        if(args.length > 0) {
            until = System.currentTimeMillis() + Long.parseLong(args[0]) * 1000L * 60L; // em minutos
            System.out.println("will die in " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(until)) + " - " + args[0] + "mins");
        }

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException el) {
            el.printStackTrace();
        }

        Random random = new Random();
        while(true) {
            PointerInfo info = MouseInfo.getPointerInfo();

            robot.mouseMove(info.getLocation().x, info.getLocation().y + (even ? 1 : -1));
            even = !even;

            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "--->" + info.getLocation().x + "/" + info.getLocation().y);

            try {
                Thread.sleep(FIVE_SECONDS);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            if(until > 0 && System.currentTimeMillis() > until) {
                System.out.println("exiting in " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                System.exit(FIVE_SECONDS);
            }
        }
    }
}
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File games = new File("M://Games");
        File src = new File(games, "src");
        File res = new File(games, "res");
        File savegames = new File(games, "savegames");
        File temp = new File(games, "temp");
        File main = new File(src, "main");
        File test = new File(src, "test");
        File drawables = new File(res, "drawables");
        File vectors = new File(res, "vectors");
        File icons = new File(res, "icons");

        File mainJava = new File("M://Games//src//main//Main.java");
        File utilJava = new File("M://Games//src//main//Utils.java");
        File tempTxt = new File("M://Games//temp//temp.txt");

        StringBuilder sb = new StringBuilder("Логи" + "\n");

        sb.append(creatingLogsForDirectories(src));
        sb.append(creatingLogsForDirectories(res));
        sb.append(creatingLogsForDirectories(savegames));
        sb.append(creatingLogsForDirectories(temp));
        sb.append(creatingLogsForDirectories(main));
        sb.append(creatingLogsForDirectories(test));
        sb.append(creatingLogsForDirectories(drawables));
        sb.append(creatingLogsForDirectories(vectors));
        sb.append(creatingLogsForDirectories(icons));

        sb.append(creatingLogsForFiles(mainJava));
        sb.append(creatingLogsForFiles(utilJava));
        sb.append(creatingLogsForFiles(tempTxt));

        String result = sb.toString();

//        String result = "Логи" + "\n" + creatingLogsForDirectories(src) +
//                creatingLogsForDirectories(res) +
//                creatingLogsForDirectories(savegames) +
//                creatingLogsForDirectories(temp) +
//                creatingLogsForDirectories(main) +
//                creatingLogsForDirectories(test) +
//                creatingLogsForDirectories(drawables) +
//                creatingLogsForDirectories(vectors) +
//                creatingLogsForDirectories(icons) +
//                creatingLogsForFiles(mainJava) +
//                creatingLogsForFiles(utilJava) +
//                creatingLogsForFiles(tempTxt);
//        System.out.println(result);

        try (FileWriter writer = new FileWriter("M://Games//temp//temp.txt", false)) {
            writer.write(result);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static String creatingLogsForDirectories(File dir) {
        if (dir.mkdir()) {
            return "Каталог " + dir.getName() + " был создан" + "\n";
        }
        return "Ошибка создания каталога  " + dir.getName() + "\n";
    }

    private static String creatingLogsForFiles(File file) {
        try {
            if (file.createNewFile())
                return "Файл " + file.getName() + " был создан" + "\n";
        } catch (IOException ex) {
            return ex.getMessage() + "\n";
        }

        return "Ошибка создания файла  " + file.getName() + "\n";
    }
}
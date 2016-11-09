package main;

/**
 * Обработчик ошибок приложения
 */
public class ExceptionHandler {
    public static void handleException(Throwable e){
        e.printStackTrace();
    }

    public static void showMessage(String string){
        System.out.println(string);
    }
}

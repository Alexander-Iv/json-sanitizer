package alexander.ivanov.jsonsanitizer.classinitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ReflectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    public static <T> T getInstance(Class<? extends T> type, Object... constructorArgs) {
        try {
            Constructor<? extends T> defaultConstructor = type.getConstructor();
            return defaultConstructor.newInstance(constructorArgs);
        } catch (NoSuchMethodException e) {
            printErrorLog(e);
            throw new RuntimeException("Default constructor not found");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            printErrorLog(e);
            throw new RuntimeException("Can't create instance");
        }
    }

    public static <T> T invokeMethod(String methodName, T object, Class<? extends T> targetType, T arg) {
        try {
            Method method = getMethod(object.getClass(), methodName, targetType);
            return (T) method.invoke(object, arg);
        } catch (IllegalAccessException | InvocationTargetException e) {
            printErrorLog(e);
            throw new RuntimeException(String.format("Can't invoke method('%s')", methodName));
        }
    }

    private static Method getMethod(Class<?> type, String methodName, Class<?>... parameterTypes) {
        logger.trace("type = {}, methodName = {}, parameterTypes = {}", type, methodName, parameterTypes);
        try {
            return type.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            printErrorLog(e);
            throw new RuntimeException("Method not found");
        }
    }

    private static void printErrorLog(Exception e) {
        String errMessage = e.getMessage() + stacktraceAsString(e.getStackTrace());
        logger.error(errMessage);
    }

    private static String stacktraceAsString(StackTraceElement[] stackTraceElements) {
        return "\nStacktrace elements:" + Arrays.stream(stackTraceElements)
                .map(stackTraceElement -> "\n\t" + stackTraceElement)
                .limit(10)
                .collect(Collectors.joining());
    }
}

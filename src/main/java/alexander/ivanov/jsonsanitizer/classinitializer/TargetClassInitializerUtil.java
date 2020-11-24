package alexander.ivanov.jsonsanitizer.classinitializer;

import alexander.ivanov.jsonsanitizer.json.JsonEntityParser;
import alexander.ivanov.jsonsanitizer.json.JsonEntityParserImpl;
import alexander.ivanov.jsonsanitizer.json.JsonEntityUtil;
import alexander.ivanov.jsonsanitizer.json.entity.JsonEntity;
import alexander.ivanov.jsonsanitizer.json.entity.NestedArrayJsonEntity;
import alexander.ivanov.jsonsanitizer.json.entity.NestedObjectJsonEntity;
import alexander.ivanov.jsonsanitizer.type.Type;
import alexander.ivanov.jsonsanitizer.type.annotation.CommonField;
import alexander.ivanov.jsonsanitizer.type.annotation.ComplexField;
import alexander.ivanov.jsonsanitizer.type.annotation.Entity;
import alexander.ivanov.jsonsanitizer.type.annotation.SimpleField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TargetClassInitializerUtil {
    private static final Logger logger = LoggerFactory.getLogger(TargetClassInitializerUtil.class);

    public static <T> T initTargetClass(String json, Class<T> targetClass) {
        List<TargetClassRow> targetClassRows = getTargetClassFields(json, targetClass);
        Map<Boolean, List<TargetClassRow>> partitionsByMessages = getTargetClassRowsPartitioningByMessage(targetClassRows);

        return getTargetClassInstance(targetClass, partitionsByMessages);
    }

    private static List<TargetClassRow> getTargetClassFields(String json, Class<?> targetClass) {
        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        Map<String, JsonEntity<?>> jsonEntities = JsonEntityUtil.toMap(jsonEntityParser.getEntityFields());

        if (isTargetClassAnnotatedEntity(targetClass)) {
            List<TargetClassRow> targetClassRows = getTargetClassRows(targetClass, jsonEntities);

            printTargetClassRowsInfo(targetClassRows);

            return targetClassRows;
        }
        return null;
    }

    private static boolean isTargetClassAnnotatedEntity(Class<?> targetClass) {
        return !Arrays.asList(targetClass.getDeclaredAnnotationsByType(Entity.class)).isEmpty();
    }

    private static List<TargetClassRow> getTargetClassRows(Class<?> targetClass, Map<String, JsonEntity<?>> jsonEntities) {
        return Arrays.stream(targetClass.getDeclaredFields())
                .map(field -> getTargetClassRow(field, jsonEntities))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static TargetClassRow getTargetClassRow(Field field, Map<String, JsonEntity<?>> jsonEntities) {
        JsonEntity<?> jsonEntity = jsonEntities.getOrDefault(field.getName(), null);
        logger.trace("jsonEntity = {}", jsonEntity);

        if (Objects.isNull(jsonEntity)) {
            return null;
        }

        return new TargetClassRow(field.getName(), getResultType(field, jsonEntity));
    }

    private static Type<?> getResultType(Field field, JsonEntity<?> jsonEntity) {
        Type<?> resultType = null;

        SimpleField simpleField = findAnnotationByClass(field.getDeclaredAnnotations(), SimpleField.class);
        if (Objects.nonNull(simpleField)) {
            resultType = getSimpleType(field, jsonEntity, simpleField);
        } else {
            logger.trace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            ComplexField complexField = findAnnotationByClass(field.getDeclaredAnnotations(), ComplexField.class);
            if (Objects.nonNull(complexField)) {
                resultType = getComplexType(field, jsonEntity, complexField);
            }
        }
        return resultType;
    }

    private static <T extends Annotation> T findAnnotationByClass(Annotation[] annotations, Class<T> type) {
        return Arrays.stream(annotations)
                .map(annotation -> annotation.annotationType().getDeclaredAnnotation(type))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    private static Type<?> getSimpleType(Field field, JsonEntity<?> jsonEntity, SimpleField simpleField) {
        Type<?> simpleType = initAndGetSimpleType(simpleField, jsonEntity.getValue());

        if (Objects.nonNull(simpleType) && Objects.nonNull(simpleType.getValue())) {
            logger.trace("fieldType = {}", field.getType());
            logger.trace("valueType = {}", simpleType.getValue().getClass());

            boolean isNonEqualsTypes = !simpleType.getValue().getClass().equals(field.getType());
            logger.trace("isNonEqualsTypes = {}", isNonEqualsTypes);
            if (isNonEqualsTypes) {
                simpleType.setValue(null);
                simpleType.setMessage("Incorrect types");
            }
        }

        return simpleType;
    }

    private static <T> Type<?> initAndGetSimpleType(SimpleField simpleField, T value) {
        Object stringValue = value instanceof String ? value : value.toString();
        logger.trace("stringValue = {}", stringValue);

        return initAndGetAnnotatedType(simpleField.value(), String.class, stringValue);
    }

    private static <T> Type<?> initAndGetAnnotatedType(CommonField commonField, Class<?> targetType, T value) {
        logger.trace("commonField = {}, targetType = {}, value = {}", commonField, targetType, value);

        Type<?> fieldInstance = null;

        if (commonField != null) {
            Class<? extends Type<?>> fieldClass = commonField.value();

            fieldInstance = ReflectionUtil.getInstance(fieldClass);
            logger.trace("fieldInstance = {}", fieldInstance);

            ReflectionUtil.invokeMethod("init", fieldInstance, targetType, value);
            logger.trace("fieldInstance = {}", fieldInstance);

            boolean isNonNullValue = fieldInstance.getValue() != null;
            logger.trace("isNonNullValue = {}", isNonNullValue);
            if (isNonNullValue) {
                logger.trace("fieldInstance.getValue() = {}", fieldInstance.getValue());
            }
        }

        return fieldInstance;
    }

    private static Type<?> getComplexType(Field field, JsonEntity<?> jsonEntity, ComplexField complexField) {
        return initAndGetAnnotatedType(complexField.value(), field.getType(), getValues(jsonEntity));
    }

    private static Object getValues(JsonEntity<?> jsonEntity) {
        return isJsonEntityArray(jsonEntity) ? getArrayValues(jsonEntity)
                : isJsonEntityObject(jsonEntity) ? getObjectValues(jsonEntity)
                : null;
    }

    private static boolean isJsonEntityArray(JsonEntity<?> jsonEntity) {
        return jsonEntity instanceof NestedArrayJsonEntity;
    }

    private static boolean isJsonEntityObject(JsonEntity<?> jsonEntity) {
        return jsonEntity instanceof NestedObjectJsonEntity;
    }

    private static List<?> getArrayValues(JsonEntity<?> jsonEntity) {
        return ((List<JsonEntity<?>>) jsonEntity.getValue())
                    .stream()
                    .map(JsonEntity::getValue)
                    .collect(Collectors.toList());
    }

    private static Map<String, ?> getObjectValues(JsonEntity<?> jsonEntity) {
        return ((List<JsonEntity<?>>) jsonEntity.getValue())
                    .stream()
                    .collect(Collectors.toMap(JsonEntity::getName, JsonEntity::getValue));
    }

    private static String formatTargetClassRowsInfo(List<TargetClassRow> targetClassRows) {
        String formatTemplate = "\n%-20s%-100s%s";

        return targetClassRows.isEmpty() ? "" : String.format(formatTemplate, "name", "value", "message") +
                targetClassRows
                        .stream()
                        .map(targetClassRow -> String.format(
                                formatTemplate,
                                targetClassRow.getName(),
                                targetClassRow.getType().getValue(),
                                targetClassRow.getType().getMessage()
                        ))
                        .collect(Collectors.joining());
    }

    private static void printTargetClassRowsInfo(List<TargetClassRow> targetClassRows) {
        String info = targetClassRows.isEmpty() ? "Fields not found" : "Target class fields: " + formatTargetClassRowsInfo(targetClassRows);
        logger.info("{}", info);
    }

    private static <T> T getTargetClassInstance(Class<T> targetClass, Map<Boolean, List<TargetClassRow>> partitionsByMessages) {
        partitionsByMessages.forEach((aBoolean, targetClassRows1) ->
                logger.trace("aBoolean = {}, targetClassRows1 = {}", aBoolean, targetClassRows1));

        raiseErrorIfContainsMessages(partitionsByMessages);

        return initInstance(targetClass, partitionsByMessages);
    }

    private static void raiseErrorIfContainsMessages(Map<Boolean, List<TargetClassRow>> partitionsByMessages) {
        if (!partitionsByMessages.get(false).isEmpty()) {
            String formattedTargetClassInfo = formatTargetClassRowsInfo(partitionsByMessages.get(false));
            logger.error("Incorrect fields: {}", formattedTargetClassInfo);
            throw new TargetClassInitializerException("Target class contain incorrect fields");
        }
    }

    private static <T> T initInstance(Class<T> targetClass, Map<Boolean, List<TargetClassRow>> partitionsByMessages) {
        T resultInstance = ReflectionUtil.getInstance(targetClass);
        logger.trace("Before init resultInstance = {}", resultInstance);

        partitionsByMessages
                .get(true)
                .forEach(targetClassRow -> {
                    String setMethodName = "set" + targetClassRow.getName().substring(0, 1).toUpperCase()
                            + targetClassRow.getName().substring(1);

                    ReflectionUtil.invokeMethod(setMethodName,
                            resultInstance,
                            targetClassRow.getType().getTargetType(),
                            targetClassRow.getType().getValue());
                });

        logger.trace("After init resultInstance = {}", resultInstance);
        return resultInstance;
    }

    private static Map<Boolean, List<TargetClassRow>> getTargetClassRowsPartitioningByMessage(List<TargetClassRow> targetClassRows) {
        return targetClassRows
                .stream()
                .collect(Collectors.partitioningBy(targetClassRow ->
                        Objects.nonNull(targetClassRow.getType())
                                && Objects.nonNull(targetClassRow.getType().getMessage())
                                && targetClassRow.getType().getMessage().isEmpty()
                ));
    }
}

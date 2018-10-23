package sample.Constants;

import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Configs {
public static final String UserName="";
    public static final String[] KEYWORDS = new String[]{
    "abstracto","booleano","quebrar","dato","EnCaso","Cachar","char",
            "Clase","coon","Continuar","Defecto","prueba","doble","ademas",
            "extender","Fiin","Flott","Si","Implementar","Importar","Ent",
            "Nuevo","paquete","privado","protegido","publico","regresar",
            "Corto","static","cambiar","Estt","lanzar","intento","void",
            "Mientras","Convertir", "a"
    };

    public static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    public static final String PAREN_PATTERN = "\\(|\\)";
    public static final String BRACE_PATTERN = "\\{|\\}";
    public static final String BRACKET_PATTERN = "\\[|\\]";
    public static final String SEMICOLON_PATTERN = "\\;";
    public static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    public static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

    public static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<BRACE>" + BRACE_PATTERN + ")"
                    + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                    + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );

    public static final String sampleCode = String.join("\n", new String[]{
        "Tipo de dato:"
    });

   public static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                            matcher.group("PAREN") != null ? "paren" :
                                    matcher.group("BRACE") != null ? "brace" :
                                            matcher.group("BRACKET") != null ? "bracket" :
                                                    matcher.group("SEMICOLON") != null ? "semicolon" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            null; /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
    public static String[] EXPRESIONES={
           "Tipo de dato: Binario (\\d+)?[A-Za-z][\\w+]*[=][0-1]{1,}",
            "Tipo de dato: Decimal (\\d+)?[A-Za-z][\\w+]*[=]\\d{1,}",
            "Tipo de dato: Hexadecimal (\\d+)?[A-Za-z][\\w+]*[=][A-Fa-f0-9]{1,}",
            "Tipo de dato: Octal (\\d+)?[A-Za-z][\\w+]*[=][0-7]{1,}",
            "Tipo de dato: Resultado (\\d+)?[A-Za-z][\\w+]",
            "(\\d+)?[A-Za-z][\\w+]*[=]Convertir (\\d+)?[A-Za-z][\\w+]* a (\\d+)?[A-Za-z][\\w+]*",
            "(\\d+)?[A-Za-z][\\w+]*.mostrar"
    };


}

package edu.escuelaing.arsw.labs.locc.filter;

import java.util.ArrayList;
import java.util.List;

public class JavaCommentFilter implements Filter {

    CharSequence startBlockCommentFlag;
    CharSequence endBlockCommentFlag;
    boolean blockComment;

    public JavaCommentFilter() {
        startBlockCommentFlag = "/*";
        endBlockCommentFlag = "*/";
        blockComment = false;
    }

    /**
     * Filtra los comentarios tanto en línea como en bloque.
     * 
     * @param lines Lista de líneas que se quieren filtrar.
     * @return Lista de líneas filtradas.
     */
    @Override
    public List<String> filter(List<String> lines) {
        List<String> filtredLines = new ArrayList<>();
        for (String line : lines) {
            if (!isJavaComment(line.stripLeading())) {
                filtredLines.add(line);
            }
        }
        return filtredLines;
    }

    private boolean isJavaComment(String line) {
        if (blockComment) {
            if (line.contains(endBlockCommentFlag)) {
                blockComment = false;
            }
            return true;
        } else if (line.length() >= 2) {
            if (line.charAt(0) == '/' && line.charAt(1) == '/') {
                return true;
            } else if (line.contains(startBlockCommentFlag)) {
                if (!line.contains(endBlockCommentFlag)) {
                    blockComment = true;
                }
                return true;
            }
        }
        return false;
    }

}

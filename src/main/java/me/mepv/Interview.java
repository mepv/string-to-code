package me.mepv;

import java.util.logging.Logger;

public class Interview {

    private static final Logger log = Logger.getLogger(Interview.class.getName());

    public static void main(String[] args) {

        String comment = "public class Main {\n" +
                "    static void splitString(String str1, int n) {\n" +
                "    //Comment here    \n" +
                "    if(str1.length() % n != 0){\n" +
                "            System.out.println(\"It can`t be divided into equal parts.\");\n" +
                "            return;\n" +
                "        }\n" +
                "/*\n" +
                "    block comment here\n" +
                " */" +
                "\n" +
                "        int begin = 0;\n" +
                "        int end = str1.length() ;\n" +
                "\n      " +
                "        //One more comment here \n"  +
                "        for (int i = 0; i < n; i++) {\n" +
                "            System.out.println(str1.substring(begin, end ));\n" +
                "            begin = begin + n;\n" +
                "            end = end - n;\n" + // changed '+ sign' to a -
                "        }\n" +
                "    }\n" +
                " }"; // added missing curly brace

        String removeBlockComment = comment.replaceAll("/\\*[\\s\\S]*?\\*/", "");
        String removeLineComment = removeBlockComment.replaceAll("//.*(?=\\n)", "");

        log.info(removeLineComment);

        /*
            solution based on this StackOverFlow answer
            https://stackoverflow.com/questions/935175/convert-string-to-code
         */
        String className = "Main";
        RuntimeCompiler runtimeCompiler = new RuntimeCompiler();
        runtimeCompiler.addClass(className, removeLineComment);
        runtimeCompiler.compile();
        MethodInvocationUtils
                .invokeStaticMethod(runtimeCompiler.getCompiledClass(className),
                        "splitString",
                        "This is a test string",
                        3);
    }
}
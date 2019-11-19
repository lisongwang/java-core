package com.lisong.learn.core.annotations.apt;

import com.lisong.learn.core.annotations.db.DBTable;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({
        "com.lisong.learn.core.annotations.db.DBTable",
        "com.lisong.learn.core.annotations.db.SQLString",
        "com.lisong.learn.core.annotations.db.SQLInteger",
        "com.lisong.learn.core.annotations.db.SQLDate",
        "com.lisong.learn.core.annotations.db.SQLTimestamp",
        "com.lisong.learn.core.annotations.db.SQLFloat"
})
public class TableCreationProcessor extends AbstractProcessor {

    private TableCreationVisitor tcv = new TableCreationVisitor();
    private Messager msg;
    private String sql = "";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        msg = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            processImpl(annotations, roundEnv);
        } catch (Exception e) {
            //do not throw any exception to compiler
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            msg.printMessage(Diagnostic.Kind.ERROR, sw.toString());
            return true;
        }
        return false;
    }

    private void processImpl(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        StringBuilder sb = new StringBuilder();
        for(TypeElement te : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(DBTable.class))) {
            te.accept(tcv,sb);
            for(VariableElement ve : ElementFilter.fieldsIn(te.getEnclosedElements()))
                ve.accept(tcv,sb);
            sql = sb.toString().substring(0, sb.length() - 1) + ");";
            execute();
            sql = "";
        }
    }

    private void execute() {
        msg.printMessage(Diagnostic.Kind.NOTE, "sql prepared for execute: \n" + sql);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            msg.printMessage(Diagnostic.Kind.ERROR, sw.toString());
        }
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/learn", "lisong", "lisong123")) {
            con.setAutoCommit(false);
            PreparedStatement preStmt = con.prepareStatement(sql);
            preStmt.executeUpdate();
            msg.printMessage(Diagnostic.Kind.NOTE, "sql completed successfully!");
        }catch(SQLException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            msg.printMessage(Diagnostic.Kind.ERROR, "sql failed: \n" + sw);
        }
    }
}
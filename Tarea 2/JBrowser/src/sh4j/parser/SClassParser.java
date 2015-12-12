package sh4j.parser;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import sh4j.model.browser.SClass;

import java.util.ArrayList;
import java.util.List;

public class SClassParser extends ASTVisitor {
  private final List<SClass> classes;

  public SClassParser() {
    classes = new ArrayList<SClass>();
  }

  public static List<SClass> parse(String source) {
    ASTParser parser = ASTParser.newParser(AST.JLS8);
    parser.setSource(source.toCharArray());
    parser.setKind(ASTParser.K_COMPILATION_UNIT);
    final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
    SClassParser builder = new SClassParser();
    cu.accept(builder);
    return builder.classes();
  }

  public boolean visit(TypeDeclaration node) {
    SMethodParser parser = new SMethodParser();
    node.accept(parser);
    classes.add(new SClass(node, parser.methods()));
    return super.visit(node);
  }

  private List<SClass> classes() {
    return classes;
  }

}

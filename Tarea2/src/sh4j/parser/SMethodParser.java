package sh4j.parser;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import sh4j.model.browser.SMethod;

import java.util.ArrayList;
import java.util.List;

class SMethodParser extends ASTVisitor {
  private final List<SMethod> methods;

  public SMethodParser() {
    methods = new ArrayList<SMethod>();
  }

  public boolean visit(MethodDeclaration node) {
    SASTParser parser = new SASTParser();
    node.accept(parser);
    methods.add(new SMethod(node, parser.top()));
    return super.visit(node);
  }

  public List<SMethod> methods() {
    return methods;
  }
}

package sh4j.parser;

import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.internal.compiler.parser.ScannerHelper;
import sh4j.parser.model.SBlock;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

@SuppressWarnings("unchecked")
public class SParser extends ASTVisitor {

  private Stack<SBlock> stack;

  private SParser() {
    stack = new Stack<SBlock>();
    stack.push(new SBlock());
  }

  public static SBlock parse(String source) {
    ASTParser parser = ASTParser.newParser(AST.JLS8);
    parser.setSource(source.toCharArray());
    parser.setKind(ASTParser.K_COMPILATION_UNIT);
    final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
    SParser builder = new SParser();
    cu.accept(builder);
    return builder.top();
  }

  public SBlock top() {
    return stack.peek();
  }

  public void enter() {
    top().enter();
  }

  public void space() {
    top().space();
  }

  public void print(String word) {
    top().word(word);
  }

  public void print(char c) {
    top().symbol(c);
  }

  public void push() {
    SBlock b = top().block();
    stack.push(b);
  }

  public void back() {
    stack.pop();
  }

  public void printModifiers(int modifiers) {
    if (Modifier.isPublic(modifiers)) {
      print("public");
    }
    if (Modifier.isProtected(modifiers)) {
      print("protected");
      space();
    }
    if (Modifier.isPrivate(modifiers)) {
      print("private");
      space();
    }
    if (Modifier.isStatic(modifiers)) {
      print("static");
      space();
    }
    if (Modifier.isAbstract(modifiers)) {
      print("abstract");
      space();
    }
    if (Modifier.isFinal(modifiers)) {
      print("final");
      space();
    }
    if (Modifier.isSynchronized(modifiers)) {
      print("synchronized");
      space();
    }
    if (Modifier.isVolatile(modifiers)) {
      print("volatile");
      space();
    }
    if (Modifier.isNative(modifiers)) {
      print("native");
      space();
    }
    if (Modifier.isStrictfp(modifiers)) {
      print("strictfp");
      space();
    }
    if (Modifier.isTransient(modifiers)) {
      print("transient");
      space();
    }
  }

  public void printModifiers(List<ASTNode> ext) {
    for (Iterator<ASTNode> it = ext.iterator(); it.hasNext(); ) {
      ASTNode p = it.next();
      p.accept(this);
      space();
    }
  }

  private void visitReferenceTypeArguments(List<Type> typeArguments) {
    print("::");
    if (!typeArguments.isEmpty()) {
      print('<');
      for (Iterator<Type> it = typeArguments.iterator(); it.hasNext(); ) {
        Type t = it.next();
        t.accept(this);
        if (it.hasNext()) {
          print(',');
        }
      }
      print('>');
    }
  }

  private void visitTypeAnnotations(AnnotatableType node) {
    visitAnnotationsList(node.annotations());
  }

  private void visitAnnotationsList(List<Annotation> annotations) {
    for (Iterator<Annotation> it = annotations.iterator(); it.hasNext(); ) {
      Annotation annotation = it.next();
      annotation.accept(this);
      space();
    }
  }

  public boolean visit(AnnotationTypeDeclaration node) {
    if (node.getJavadoc() != null) {
      node.getJavadoc().accept(this);
    }
    printModifiers(node.modifiers());
    print("@interface");
    space();
    node.getName().accept(this);
    space();
    print('{');
    for (Iterator<BodyDeclaration> it = node.bodyDeclarations().iterator(); it.hasNext(); ) {
      BodyDeclaration d = it.next();
      d.accept(this);
    }
    print('}');
    enter();
    return false;
  }

  public boolean visit(AnnotationTypeMemberDeclaration node) {
    if (node.getJavadoc() != null) {
      node.getJavadoc().accept(this);
    }
    printModifiers(node.modifiers());
    node.getType().accept(this);
    space();
    node.getName().accept(this);
    print("()");
    if (node.getDefault() != null) {
      space();
      print("default");
      space();
      node.getDefault().accept(this);
    }
    print(';');
    enter();
    return false;
  }

  public boolean visit(AnonymousClassDeclaration node) {
    print('{');
    push();
    enter();
    for (Iterator<BodyDeclaration> it = node.bodyDeclarations().iterator(); it.hasNext(); ) {
      BodyDeclaration b = it.next();
      b.accept(this);
    }
    back();
    enter();
    print('}');
    return false;
  }

  public boolean visit(ArrayAccess node) {
    node.getArray().accept(this);
    print('[');
    node.getIndex().accept(this);
    print(']');
    return false;
  }

  public boolean visit(ArrayCreation node) {
    print("new");
    space();
    ArrayType at = node.getType();
    int dims = at.getDimensions();
    Type elementType = at.getElementType();
    elementType.accept(this);
    for (Iterator<Expression> it = node.dimensions().iterator(); it.hasNext(); ) {
      print('[');
      Expression e = it.next();
      e.accept(this);
      print(']');
      dims--;
    }
    for (int i = 0; i < dims; i++) {
      print("[]");
    }
    if (node.getInitializer() != null) {
      node.getInitializer().accept(this);
    }
    return false;
  }

  public boolean visit(ArrayInitializer node) {
    print('{');
    for (Iterator<Expression> it = node.expressions().iterator(); it.hasNext(); ) {
      Expression e = it.next();
      e.accept(this);
      if (it.hasNext()) {
        print(',');
      }
    }
    print('}');
    return false;
  }

  public boolean visit(ArrayType node) {
    node.getElementType().accept(this);
    List<Dimension> dimensions = node.dimensions();
    int size = dimensions.size();
    for (int i = 0; i < size; i++) {
      Dimension aDimension = dimensions.get(i);
      aDimension.accept(this);
    }
    return false;
  }

  public boolean visit(AssertStatement node) {
    print("assert");
    space();
    node.getExpression().accept(this);
    if (node.getMessage() != null) {
      space();
      print(':');
      space();
      node.getMessage().accept(this);
    }
    print(';');
    enter();
    return false;
  }

  public boolean visit(Assignment node) {
    node.getLeftHandSide().accept(this);
    print(node.getOperator().toString());
    node.getRightHandSide().accept(this);
    return false;
  }

  public boolean visit(Block node) {
    print('{');
    push();
    enter();
    for (Iterator<Statement> it = node.statements().iterator(); it.hasNext(); ) {
      Statement s = it.next();
      s.accept(this);
    }
    back();
    enter();
    print('}');
    return false;
  }

  public boolean visit(BlockComment node) {
    print("/* */");
    return false;
  }

  public boolean visit(BooleanLiteral node) {
    if (node.booleanValue() == true) {
      print("true");
    } else {
      print("false");
    }
    return false;
  }

  public boolean visit(BreakStatement node) {
    print("break");
    if (node.getLabel() != null) {
      space();
      node.getLabel().accept(this);
    }
    print(';');
    enter();
    return false;
  }

  public boolean visit(CastExpression node) {
    print('(');
    node.getType().accept(this);
    print(')');
    node.getExpression().accept(this);
    return false;
  }

  public boolean visit(CatchClause node) {
    print("catch");
    space();
    print('(');
    node.getException().accept(this);
    print(')');
    space();
    node.getBody().accept(this);
    return false;
  }

  public boolean visit(CharacterLiteral node) {
    print(node.getEscapedValue());
    return false;
  }

  public boolean visit(ClassInstanceCreation node) {
    if (node.getExpression() != null) {
      node.getExpression().accept(this);
      print('.');
    }
    print("new");
    space();
    if (!node.typeArguments().isEmpty()) {
      print('<');
      for (Iterator<Type> it = node.typeArguments().iterator(); it.hasNext(); ) {
        Type t = it.next();
        t.accept(this);
        if (it.hasNext()) {
          print(',');
        }
      }
      print('>');
    }
    node.getType().accept(this);
    print('(');
    for (Iterator<Expression> it = node.arguments().iterator(); it.hasNext(); ) {
      Expression e = it.next();
      e.accept(this);
      if (it.hasNext()) {
        print(',');
      }
    }
    print(')');
    if (node.getAnonymousClassDeclaration() != null) {
      node.getAnonymousClassDeclaration().accept(this);
    }
    return false;
  }

  public boolean visit(CompilationUnit node) {
    if (node.getPackage() != null) {
      node.getPackage().accept(this);
    }
    for (Iterator<ImportDeclaration> it = node.imports().iterator(); it.hasNext(); ) {
      ImportDeclaration d = it.next();
      d.accept(this);
    }
    for (Iterator<AbstractTypeDeclaration> it = node.types().iterator(); it.hasNext(); ) {
      AbstractTypeDeclaration d = it.next();
      d.accept(this);
    }
    return false;
  }

  public boolean visit(ConditionalExpression node) {
    node.getExpression().accept(this);
    space();
    print('?');
    space();
    node.getThenExpression().accept(this);
    space();
    print(':');
    space();
    node.getElseExpression().accept(this);
    return false;
  }

  public boolean visit(ConstructorInvocation node) {
    if (!node.typeArguments().isEmpty()) {
      print('<');
      for (Iterator<Type> it = node.typeArguments().iterator(); it.hasNext(); ) {
        Type t = it.next();
        t.accept(this);
        if (it.hasNext()) {
          print(',');
        }
      }
      print('>');
    }
    print("this");
    print('(');
    for (Iterator<Expression> it = node.arguments().iterator(); it.hasNext(); ) {
      Expression e = it.next();
      e.accept(this);
      if (it.hasNext()) {
        print(',');
      }
    }
    print(')');
    print(';');
    enter();
    return false;
  }

  public boolean visit(ContinueStatement node) {
    print("continue");
    if (node.getLabel() != null) {
      space();
      node.getLabel().accept(this);
    }
    print(';');
    enter();
    return false;
  }

  public boolean visit(CreationReference node) {
    node.getType().accept(this);
    visitReferenceTypeArguments(node.typeArguments());
    print("new");
    return false;
  }

  public boolean visit(Dimension node) {
    List<Annotation> annotations = node.annotations();
    if (annotations.size() > 0) {
      space();
    }
    visitAnnotationsList(annotations);
    print("[]");
    return false;
  }

  public boolean visit(DoStatement node) {
    print("do");
    space();
    node.getBody().accept(this);
    space();
    print("while");
    space();
    print('(');
    node.getExpression().accept(this);
    print(')');
    print(';');
    enter();
    return false;
  }

  public boolean visit(EmptyStatement node) {
    print(';');
    enter();
    return false;
  }

  public boolean visit(EnhancedForStatement node) {
    print("for");
    space();
    print('(');
    node.getParameter().accept(this);
    space();
    print(":");
    space();
    node.getExpression().accept(this);
    print(')');
    space();
    node.getBody().accept(this);
    return false;
  }

  public boolean visit(EnumConstantDeclaration node) {
    if (node.getJavadoc() != null) {
      node.getJavadoc().accept(this);
    }
    printModifiers(node.modifiers());
    node.getName().accept(this);
    if (!node.arguments().isEmpty()) {
      print('(');
      for (Iterator<Expression> it = node.arguments().iterator(); it.hasNext(); ) {
        Expression e = it.next();
        e.accept(this);
        if (it.hasNext()) {
          print(",");
        }
      }
      print(')');
    }
    if (node.getAnonymousClassDeclaration() != null) {
      node.getAnonymousClassDeclaration().accept(this);
    }
    return false;
  }

  public boolean visit(EnumDeclaration node) {
    if (node.getJavadoc() != null) {
      node.getJavadoc().accept(this);
    }
    printModifiers(node.modifiers());
    print("enum");
    space();
    node.getName().accept(this);
    space();
    if (!node.superInterfaceTypes().isEmpty()) {
      print("implements");
      space();
      for (Iterator<Type> it = node.superInterfaceTypes().iterator(); it.hasNext(); ) {
        Type t = it.next();
        t.accept(this);
        if (it.hasNext()) {
          print(",");
          space();
        }
      }
      space();
    }
    print("{");
    for (Iterator<EnumConstantDeclaration> it = node.enumConstants().iterator(); it.hasNext(); ) {
      EnumConstantDeclaration d = it.next();
      d.accept(this);
      // enum constant declarations do not include punctuation
      if (it.hasNext()) {
        // enum constant declarations are separated by commas
        print(",");
        space();
      }
    }
    if (!node.bodyDeclarations().isEmpty()) {
      print(';');
      space();
      for (Iterator<BodyDeclaration> it = node.bodyDeclarations().iterator(); it.hasNext(); ) {
        BodyDeclaration d = it.next();
        d.accept(this);
        // other body declarations include trailing punctuation
      }
    }
    print("}");
    enter();
    return false;
  }

  public boolean visit(ExpressionMethodReference node) {
    node.getExpression().accept(this);
    visitReferenceTypeArguments(node.typeArguments());
    node.getName().accept(this);
    return false;
  }

  public boolean visit(ExpressionStatement node) {
    node.getExpression().accept(this);
    print(';');
    enter();
    return false;
  }

  public boolean visit(FieldAccess node) {
    node.getExpression().accept(this);
    print(".");
    node.getName().accept(this);
    return false;
  }

  public boolean visit(FieldDeclaration node) {
    if (node.getJavadoc() != null) {
      node.getJavadoc().accept(this);
    }
    printModifiers(node.modifiers());
    node.getType().accept(this);
    space();
    for (Iterator<VariableDeclarationFragment> it = node.fragments().iterator(); it.hasNext(); ) {
      VariableDeclarationFragment f = it.next();
      f.accept(this);
      if (it.hasNext()) {
        print(",");
        space();
      }
    }
    print(';');
    enter();
    return false;
  }

  public boolean visit(ForStatement node) {
    print("for");
    space();
    print('(');
    for (Iterator<Expression> it = node.initializers().iterator(); it.hasNext(); ) {
      Expression e = it.next();
      e.accept(this);
      if (it.hasNext()) {
        print(",");
      }
      space();
    }
    print(';');
    space();
    if (node.getExpression() != null) {
      node.getExpression().accept(this);
    }
    print(';');
    space();
    for (Iterator<Expression> it = node.updaters().iterator(); it.hasNext(); ) {
      Expression e = it.next();
      e.accept(this);
      if (it.hasNext()) {
        print(",");
      }
      space();
    }
    print(')');
    space();
    node.getBody().accept(this);
    return false;
  }

  public boolean visit(IfStatement node) {
    print("if");
    space();
    print('(');
    node.getExpression().accept(this);
    print(')');
    space();
    node.getThenStatement().accept(this);
    if (node.getElseStatement() != null) {
      space();
      print("else");
      space();
      node.getElseStatement().accept(this);
    }
    return false;
  }

  public boolean visit(ImportDeclaration node) {
    print("import");
    space();
    if (node.isStatic()) {
      print("static");
      space();
    }
    node.getName().accept(this);
    if (node.isOnDemand()) {
      print(".*");
    }
    print(';');
    enter();
    return false;
  }

  public boolean visit(InfixExpression node) {
    node.getLeftOperand().accept(this);
    space();
    print(node.getOperator().toString());
    space();
    node.getRightOperand().accept(this);
    final List extendedOperands = node.extendedOperands();
    if (extendedOperands.size() != 0) {
      space();
      for (Iterator<Expression> it = extendedOperands.iterator(); it.hasNext(); ) {
        print(node.getOperator().toString());
        space();
        Expression e = it.next();
        e.accept(this);
      }
    }
    return false;
  }

  public boolean visit(Initializer node) {
    if (node.getJavadoc() != null) {
      node.getJavadoc().accept(this);
    }
    printModifiers(node.modifiers());
    node.getBody().accept(this);
    return false;
  }

  public boolean visit(InstanceofExpression node) {
    node.getLeftOperand().accept(this);
    space();
    print("instanceof");
    space();
    node.getRightOperand().accept(this);
    return false;
  }

  public boolean visit(IntersectionType node) {
    for (Iterator<Type> it = node.types().iterator(); it.hasNext(); ) {
      Type t = it.next();
      t.accept(this);
      if (it.hasNext()) {
        space();
        print("&");
        space();
      }
    }
    return false;
  }

  public boolean visit(Javadoc node) {
    print("/** */");
    enter();
    /*print("/**");space();
    for (Iterator<ASTNode> it = node.tags().iterator(); it.hasNext(); ) {
			ASTNode e =  it.next();
			e.accept(this);
		}
		enter();space();print("*\/");enter();*/
    return false;
  }

  public boolean visit(LabeledStatement node) {
    node.getLabel().accept(this);
    print(":");
    space();
    node.getBody().accept(this);
    return false;
  }

  public boolean visit(LambdaExpression node) {
    boolean hasParentheses = node.hasParentheses();
    if (hasParentheses) {
      print('(');
    }
    for (Iterator<VariableDeclaration> it = node.parameters().iterator(); it.hasNext(); ) {
      VariableDeclaration v = it.next();
      v.accept(this);
      if (it.hasNext()) {
        print(",");
      }
    }
    if (hasParentheses) {
      print(')');
    }
    space();
    print("->");
    space();
    node.getBody().accept(this);
    return false;
  }

  public boolean visit(LineComment node) {
    print("//");
    enter();
    return false;
  }

  public boolean visit(MarkerAnnotation node) {
    print("@");
    node.getTypeName().accept(this);
    return false;
  }

  public boolean visit(MemberRef node) {
    if (node.getQualifier() != null) {
      node.getQualifier().accept(this);
    }
    print("#");
    node.getName().accept(this);
    return false;
  }

  public boolean visit(MemberValuePair node) {
    node.getName().accept(this);
    print("=");
    node.getValue().accept(this);
    return false;
  }

  public boolean visit(MethodDeclaration node) {
    enter();
    if (node.getJavadoc() != null) {
      node.getJavadoc().accept(this);
    }
    printModifiers(node.modifiers());
    if (!node.typeParameters().isEmpty()) {
      print("<");
      for (Iterator<TypeParameter> it = node.typeParameters().iterator(); it.hasNext(); ) {
        TypeParameter t = it.next();
        t.accept(this);
        if (it.hasNext()) {
          print(",");
        }
      }
      print(">");
    }
    if (!node.isConstructor()) {
      if (node.getReturnType2() != null) {
        node.getReturnType2().accept(this);
      } else {
        print("void");
      }
      space();
    }
    node.getName().accept(this);
    print('(');
    Type receiverType = node.getReceiverType();
    if (receiverType != null) {
      receiverType.accept(this);
      space();
      SimpleName qualifier = node.getReceiverQualifier();
      if (qualifier != null) {
        qualifier.accept(this);
        print(".");
      }
      print("this");
      if (node.parameters().size() > 0) {
        print(".");
      }
    }
    for (Iterator<SingleVariableDeclaration> it = node.parameters().iterator(); it.hasNext(); ) {
      SingleVariableDeclaration v = it.next();
      v.accept(this);
      if (it.hasNext()) {
        print(",");
      }
    }
    print(')');
    int size = node.getExtraDimensions();
    List<Dimension> dimensions = node.extraDimensions();
    for (int i = 0; i < size; i++) {
      visit(dimensions.get(i));
    }
    if (!node.thrownExceptionTypes().isEmpty()) {
      space();
      print("throws");
      space();
      for (Iterator<Type> it = node.thrownExceptionTypes().iterator(); it.hasNext(); ) {
        Type n = it.next();
        n.accept(this);
        if (it.hasNext()) {
          print(",");
          space();
        }
      }
      space();
    }
    if (node.getBody() == null) {
      print(';');
      enter();
    } else {
      node.getBody().accept(this);
    }
    return false;
  }

  public boolean visit(MethodInvocation node) {
    if (node.getExpression() != null) {
      node.getExpression().accept(this);
      print(".");
    }
    if (!node.typeArguments().isEmpty()) {
      print("<");
      for (Iterator<Type> it = node.typeArguments().iterator(); it.hasNext(); ) {
        Type t = it.next();
        t.accept(this);
        if (it.hasNext()) {
          print(",");
        }
      }
      print(">");
    }
    node.getName().accept(this);
    print('(');
    for (Iterator<Expression> it = node.arguments().iterator(); it.hasNext(); ) {
      Expression e = it.next();
      e.accept(this);
      if (it.hasNext()) {
        print(",");
      }
    }
    print(')');
    return false;
  }

  public boolean visit(MethodRef node) {
    if (node.getQualifier() != null) {
      node.getQualifier().accept(this);
    }
    print("#");
    node.getName().accept(this);
    print('(');
    for (Iterator<MethodRefParameter> it = node.parameters().iterator(); it.hasNext(); ) {
      MethodRefParameter e = it.next();
      e.accept(this);
      if (it.hasNext()) {
        print(",");
      }
    }
    print(')');
    return false;
  }

  public boolean visit(MethodRefParameter node) {
    node.getType().accept(this);
    if (node.isVarargs()) {
      print("...");
    }
    if (node.getName() != null) {
      space();
      node.getName().accept(this);
    }
    return false;
  }

  public boolean visit(Modifier node) {
    print(node.getKeyword().toString());
    return false;
  }

  public boolean visit(NameQualifiedType node) {
    node.getQualifier().accept(this);
    print(".");
    visitTypeAnnotations(node);
    node.getName().accept(this);
    return false;
  }

  public boolean visit(NormalAnnotation node) {
    print("@");
    node.getTypeName().accept(this);
    print('(');
    for (Iterator<MemberValuePair> it = node.values().iterator(); it.hasNext(); ) {
      MemberValuePair p = it.next();
      p.accept(this);
      if (it.hasNext()) {
        print(",");
      }
    }
    print(')');
    return false;
  }

  public boolean visit(NullLiteral node) {
    print("null");
    return false;
  }

  public boolean visit(NumberLiteral node) {
    print(node.getToken());
    return false;
  }

  public boolean visit(PackageDeclaration node) {
    if (node.getJavadoc() != null) {
      node.getJavadoc().accept(this);
    }
    for (Iterator<Annotation> it = node.annotations().iterator(); it.hasNext(); ) {
      Annotation p = it.next();
      p.accept(this);
      space();
    }
    print("package");
    space();
    node.getName().accept(this);
    print(';');
    enter();
    return false;
  }

  public boolean visit(ParameterizedType node) {
    node.getType().accept(this);
    print("<");
    for (Iterator<Type> it = node.typeArguments().iterator(); it.hasNext(); ) {
      Type t = it.next();
      t.accept(this);
      if (it.hasNext()) {
        print(",");
      }
    }
    print(">");
    return false;
  }

  public boolean visit(ParenthesizedExpression node) {
    print('(');
    node.getExpression().accept(this);
    print(')');
    return false;
  }

  public boolean visit(PostfixExpression node) {
    node.getOperand().accept(this);
    print(node.getOperator().toString());
    return false;
  }

  public boolean visit(PrefixExpression node) {
    print(node.getOperator().toString());
    node.getOperand().accept(this);
    return false;
  }

  public boolean visit(PrimitiveType node) {
    visitTypeAnnotations(node);
    print(node.getPrimitiveTypeCode().toString());
    return false;
  }

  public boolean visit(QualifiedName node) {
    node.getQualifier().accept(this);
    print(".");
    node.getName().accept(this);
    return false;
  }

  public boolean visit(QualifiedType node) {
    node.getQualifier().accept(this);
    print(".");
    visitTypeAnnotations(node);
    node.getName().accept(this);
    return false;
  }

  public boolean visit(ReturnStatement node) {
    print("return");
    if (node.getExpression() != null) {
      space();
      node.getExpression().accept(this);
    }
    print(';');
    enter();
    return false;
  }

  public boolean visit(SimpleName node) {
    print(node.getIdentifier());
    return false;
  }

  public boolean visit(SimpleType node) {
    visitTypeAnnotations(node);
    node.getName().accept(this);
    return false;
  }

  public boolean visit(SingleMemberAnnotation node) {
    print("@");
    node.getTypeName().accept(this);
    print('(');
    node.getValue().accept(this);
    print(')');
    return false;
  }

  public boolean visit(SingleVariableDeclaration node) {
    printModifiers(node.modifiers());
    node.getType().accept(this);
    if (node.isVarargs()) {
      List<Annotation> annotations = node.varargsAnnotations();
      if (annotations.size() > 0) {
        space();
      }
      visitAnnotationsList(annotations);
      print("...");
    }
    space();
    node.getName().accept(this);
    int size = node.getExtraDimensions();
    List<Dimension> dimensions = node.extraDimensions();
    for (int i = 0; i < size; i++) {
      visit(dimensions.get(i));
    }
    if (node.getInitializer() != null) {
      print("=");
      node.getInitializer().accept(this);
    }
    return false;
  }

  public boolean visit(StringLiteral node) {
    print(node.getEscapedValue());
    return false;
  }

  public boolean visit(SuperConstructorInvocation node) {
    if (node.getExpression() != null) {
      node.getExpression().accept(this);
      print(".");
    }
    if (!node.typeArguments().isEmpty()) {
      print("<");
      for (Iterator<Type> it = node.typeArguments().iterator(); it.hasNext(); ) {
        Type t = it.next();
        t.accept(this);
        if (it.hasNext()) {
          print(",");
        }
      }
      print(">");
    }
    print("super");
    print('(');
    for (Iterator<Expression> it = node.arguments().iterator(); it.hasNext(); ) {
      Expression e = it.next();
      e.accept(this);
      if (it.hasNext()) {
        print(",");
      }
    }
    print(')');
    print(';');
    enter();
    return false;
  }

  public boolean visit(SuperFieldAccess node) {
    if (node.getQualifier() != null) {
      node.getQualifier().accept(this);
      print(".");
    }
    print("super");
    print(".");
    node.getName().accept(this);
    return false;
  }

  public boolean visit(SuperMethodInvocation node) {
    if (node.getQualifier() != null) {
      node.getQualifier().accept(this);
      print(".");
    }
    print("super");
    print(".");
    if (!node.typeArguments().isEmpty()) {
      print("<");
      for (Iterator<Type> it = node.typeArguments().iterator(); it.hasNext(); ) {
        Type t = it.next();
        t.accept(this);
        if (it.hasNext()) {
          print(",");
        }
      }
      print(">");
    }
    node.getName().accept(this);
    print('(');
    for (Iterator<Expression> it = node.arguments().iterator(); it.hasNext(); ) {
      Expression e = it.next();
      e.accept(this);
      if (it.hasNext()) {
        print(",");
      }
    }
    print(')');
    return false;
  }

  public boolean visit(SuperMethodReference node) {
    if (node.getQualifier() != null) {
      node.getQualifier().accept(this);
      print(".");
    }
    print("super");
    visitReferenceTypeArguments(node.typeArguments());
    node.getName().accept(this);
    return false;
  }

  public boolean visit(SwitchCase node) {
    if (node.isDefault()) {
      print("default");
      space();
      print(":");
      enter();
    } else {
      print("case");
      space();
      node.getExpression().accept(this);
      print(":");
      enter();
    }
    push();
    return false;
  }

  public boolean visit(SwitchStatement node) {
    print("switch");
    space();
    print('(');
    node.getExpression().accept(this);
    print(')');
    space();
    print("{");
    push();
    enter();
    for (Iterator<Statement> it = node.statements().iterator(); it.hasNext(); ) {
      Statement s = it.next();
      s.accept(this);
      back();
    }
    back();
    enter();
    print("}");
    return false;
  }

  public boolean visit(SynchronizedStatement node) {
    print("synchronized");
    space();
    print('(');
    node.getExpression().accept(this);
    print(')');
    space();
    node.getBody().accept(this);
    return false;
  }

  public boolean visit(TagElement node) {
    if (node.isNested()) {
      print("{");
    } else {
      enter();
      space();
      print("*");
      space();
    }
    boolean previousRequiresWhiteSpace = false;
    if (node.getTagName() != null) {
      print(node.getTagName());
      previousRequiresWhiteSpace = true;
    }
    boolean previousRequiresNewLine = false;
    for (Iterator<ASTNode> it = node.fragments().iterator(); it.hasNext(); ) {
      ASTNode e = it.next();
      boolean currentIncludesWhiteSpace = false;
      if (e instanceof TextElement) {
        String text = ((TextElement) e).getText();
        if (text.length() > 0 && ScannerHelper.isWhitespace(text.charAt(0))) {
          currentIncludesWhiteSpace = true; // workaround for https://bugs.eclipse.org/403735
        }
      }
      if (previousRequiresNewLine && currentIncludesWhiteSpace) {
        enter();
        space();
        print("*");
        space();
      }
      previousRequiresNewLine = currentIncludesWhiteSpace;
      if (previousRequiresWhiteSpace && !currentIncludesWhiteSpace) {
        space();
      }
      e.accept(this);
      previousRequiresWhiteSpace = !currentIncludesWhiteSpace && !(e instanceof TagElement);
    }
    if (node.isNested()) {
      print("}");
    }
    return false;
  }

  public boolean visit(TextElement node) {
    print(node.getText());
    return false;
  }

  public boolean visit(ThisExpression node) {
    if (node.getQualifier() != null) {
      node.getQualifier().accept(this);
      print(".");
    }
    print("this");
    return false;
  }

  public boolean visit(ThrowStatement node) {
    print("throw");
    space();
    node.getExpression().accept(this);
    print(';');
    enter();
    return false;
  }

  public boolean visit(TryStatement node) {
    print("try");
    space();
    List resources = node.resources();
    if (!resources.isEmpty()) {
      print('(');
      for (Iterator<VariableDeclarationExpression> it = resources.iterator(); it.hasNext(); ) {
        VariableDeclarationExpression variable = it.next();
        variable.accept(this);
        if (it.hasNext()) {
          print(';');
        }
      }
      print(')');
    }
    node.getBody().accept(this);
    space();
    for (Iterator<CatchClause> it = node.catchClauses().iterator(); it.hasNext(); ) {
      CatchClause cc = it.next();
      cc.accept(this);
    }
    if (node.getFinally() != null) {
      space();
      print("finally");
      space();
      node.getFinally().accept(this);
    }
    return false;
  }

  public boolean visit(TypeDeclaration node) {
    if (node.getJavadoc() != null) {
      node.getJavadoc().accept(this);
    }
    printModifiers(node.modifiers());
    print(node.isInterface() ? "interface" : "class");
    space();//$NON-NLS-2$
    node.getName().accept(this);
    if (!node.typeParameters().isEmpty()) {
      print("<");
      for (Iterator<TypeParameter> it = node.typeParameters().iterator(); it.hasNext(); ) {
        TypeParameter t = it.next();
        t.accept(this);
        if (it.hasNext()) {
          print(",");
        }
      }
      print(">");
    }
    space();
    if (node.getSuperclassType() != null) {
      print("extends");
      space();
      node.getSuperclassType().accept(this);
      space();
    }
    if (!node.superInterfaceTypes().isEmpty()) {
      print(node.isInterface() ? "extends" : "implements");
      space();//$NON-NLS-2$
      for (Iterator<Type> it = node.superInterfaceTypes().iterator(); it.hasNext(); ) {
        Type t = it.next();
        t.accept(this);
        if (it.hasNext()) {
          print(",");
          space();
        }
      }
      space();
    }
    print("{");
    push();
    enter();
    for (Iterator<BodyDeclaration> it = node.bodyDeclarations().iterator(); it.hasNext(); ) {
      BodyDeclaration d = it.next();
      d.accept(this);
    }
    back();
    enter();
    print("}");
    return false;
  }

  public boolean visit(TypeDeclarationStatement node) {
    node.getDeclaration().accept(this);
    return false;
  }

  public boolean visit(TypeLiteral node) {
    node.getType().accept(this);
    print(".class");
    return false;
  }

  public boolean visit(TypeMethodReference node) {
    node.getType().accept(this);
    visitReferenceTypeArguments(node.typeArguments());
    node.getName().accept(this);
    return false;
  }

  public boolean visit(TypeParameter node) {
    printModifiers(node.modifiers());
    node.getName().accept(this);
    if (!node.typeBounds().isEmpty()) {
      space();
      print("extends");
      space();
      for (Iterator<Type> it = node.typeBounds().iterator(); it.hasNext(); ) {
        Type t = it.next();
        t.accept(this);
        if (it.hasNext()) {
          space();
          print("&");
          space();
        }
      }
    }
    return false;
  }

  public boolean visit(UnionType node) {
    for (Iterator<Type> it = node.types().iterator(); it.hasNext(); ) {
      Type t = it.next();
      t.accept(this);
      if (it.hasNext()) {
        print("|");
      }
    }
    return false;
  }

  public boolean visit(VariableDeclarationExpression node) {
    printModifiers(node.modifiers());
    node.getType().accept(this);
    space();
    for (Iterator<VariableDeclarationFragment> it = node.fragments().iterator(); it.hasNext(); ) {
      VariableDeclarationFragment f = it.next();
      f.accept(this);
      if (it.hasNext()) {
        print(",");
        space();
      }
    }
    return false;
  }

  public boolean visit(VariableDeclarationFragment node) {
    node.getName().accept(this);
    int size = node.getExtraDimensions();
    List<Dimension> dimensions = node.extraDimensions();
    for (int i = 0; i < size; i++) {
      visit(dimensions.get(i));
    }
    if (node.getInitializer() != null) {
      print("=");
      node.getInitializer().accept(this);
    }
    return false;
  }

  public boolean visit(VariableDeclarationStatement node) {
    printModifiers(node.modifiers());
    node.getType().accept(this);
    space();
    for (Iterator<VariableDeclarationFragment> it = node.fragments().iterator(); it.hasNext(); ) {
      VariableDeclarationFragment f = it.next();
      f.accept(this);
      if (it.hasNext()) {
        print(",");
        space();
      }
    }
    print(';');
    enter();
    return false;
  }

  public boolean visit(WhileStatement node) {
    print("while");
    space();
    print('(');
    node.getExpression().accept(this);
    print(')');
    space();
    node.getBody().accept(this);
    return false;
  }

  public boolean visit(WildcardType node) {
    visitTypeAnnotations(node);
    print('?');
    Type bound = node.getBound();
    if (bound != null) {
      if (node.isUpperBound()) {
        space();
        print("extends");
        space();
      } else {
        space();
        print("super");
        space();
      }
      bound.accept(this);
    }
    return false;
  }
}
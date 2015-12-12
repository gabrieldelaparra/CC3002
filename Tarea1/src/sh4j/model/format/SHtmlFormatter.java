package sh4j.model.format;

import sh4j.model.highlight.SDummy;
import sh4j.model.highlight.SHighlighter;
import sh4j.model.style.SStyle;
import sh4j.parser.model.SBlock;
import sh4j.parser.model.SText;

/**
 Applies HTML Tags to strings based on a given Style/Highlight.

 @author Gabriel De La Parra
 @version 1.0 */
public class SHtmlFormatter implements SFormatter {

  /**
   StringBuilder Field.
   ERROR: 1. StringBuffers can grow quite a lot,
   and so may become a source of memory leak
   (if the owning class has a long life time).
   */
  private final StringBuilder buffer;

  /**
   Level Field.
   To be used in indentation.
   */
  private int level;

  /**
   Style Field.
   To be assigned in the constructor.
   */
  private final SStyle style;

  /**
   Highlighter Array Field.
   To be assigned in the constructor.
   */
  private final SHighlighter[] highlighters;

  /**
   Constructor.

   @param style Highlight style to apply.
   @param highlighterStyles Highlight models to cover.
   */
  public SHtmlFormatter(SStyle style, SHighlighter... highlighterStyles) {
    this.style = style;
    highlighters = highlighterStyles;
    buffer = new StringBuilder();
  }

  /**
   Does Lookup for highlight style.
   2,3. Found 'UR'-anomaly for variable 'highlighter'/ 'DD'-anomaly 'highlight' 
   4. Potential violation of Law of Demeter (object not created locally)
   @param text text to highlight.
   @return returns the corresponding Highlighter.
   */
  private SHighlighter lookup(String text) {
    SHighlighter highlight = new SDummy();
    for (SHighlighter highlighter : highlighters) {
      if (highlighter.needsHighLight(text)) {
        highlight = highlighter;
        break;
      }
    }
    return highlight;
  }

  /**
   Applies a Styled String to the Buffer.
   ERROR: 5. Potential violation of Law of Demeter (method chain calls)

   @param word String to append.
   */
  @Override
  public void styledWord(String word) {
    buffer.append(lookup(word).highlight(word, style));
  }

  /**
   Appends a character to the buffer.
   ERROR: 6. Potential violation of Law of Demeter (method chain calls)

   @param character char to append.
   */
  @Override
  public void styledChar(char character) {
    buffer.append(lookup(character + "").highlight(character + "", style));
  }

  /**
   Appends a empty string to the buffer.
  */
  @Override
  public void styledSpace() {
    buffer.append(' ');
  }

  /**
   Appends a Carrier Return to the buffer.
  */
  @Override
  public void styledCarrierReturn() {
    buffer.append('\n');
    indent();
  }

  /**
   Reads a block.
   ERROR: 7. Found 'UR'-anomaly for variable 'stext' (lines '132'-'135').

   @param sblock stringBlock to read.
   */
  @Override
  public void styledBlock(SBlock sblock) {
    level++;
    for (SText stext : sblock.texts()) {
      stext.export(this);
    }
    level--;
  }

  /**
   Appends indentation to the buffer.
  */
  private void indent() {
    for (int i = 0; i < level; i++) {
      buffer.append("  ");
    }
  }

  /**
   Applies an HTML header to the buffer.

   @return bufferString with style-based header;
   */
  @Override
  public String formattedText() {
    String result;
    if (style.isStyle("eclipse")) {
      result = "<pre style='color:#000000;background:#ffffff;'>" + buffer.toString() + "</pre>";
    } else if (style.isStyle("dark")) {
      result = "<pre style='color:#d1d1d1;background:#000000;'>" + buffer.toString() + "</pre>";
    } else {
      result = "<pre style='color:#000000;background:#f1f0f0;'>" + buffer.toString() + "</pre>";
    }
    return result;
  }
}

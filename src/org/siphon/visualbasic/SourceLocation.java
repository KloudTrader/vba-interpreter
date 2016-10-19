package org.siphon.visualbasic;

import java.io.File;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class SourceLocation {
	
	public static final SourceLocation ByInterpreter = new SourceLocation();

	public File getSourceFile() {
		return sourceFile;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public int getLength() {
		return length;
	}
	
	private File sourceFile;

	private int line;
	
	private int column;

	private int length;
	
	public SourceLocation(File sourceFile, int line, int column, int length) {
		this.sourceFile = sourceFile;
		this.line = line;
		this.column = column;
		this.length = length;
	}
	
	private SourceLocation() {
	}
	
	public SourceLocation(File sourceFile, ParserRuleContext rule) {
		this.sourceFile = sourceFile;
		this.line = rule.start.getLine();
		this.column = rule.start.getCharPositionInLine();
		this.length = rule.stop.getStopIndex() - rule.start.getStartIndex();
	}
	
	public SourceLocation(File sourceFile, Token token) {
		this.sourceFile = sourceFile;
		this.line = token.getLine();
		this.column = token.getCharPositionInLine();
		this.length = token.getStopIndex() - token.getStartIndex();
	}

	@Override
	public String toString() {
		return String.format("[%s %s:%s %s]", sourceFile.getName(), line, column, length);
	}
}
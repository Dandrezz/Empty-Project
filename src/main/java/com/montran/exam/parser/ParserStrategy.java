package com.montran.exam.parser;

import java.util.List;

import com.montran.exam.parser.formats.Format;

/**
 * @author Diego Portero
 *
 * @param <T>
 */

public interface ParserStrategy<T extends Format> {
	
	void loadFile();
	
	List<T> processFile();

}

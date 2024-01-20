/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.cameltooling.lsp.internal.parser.fileparserhelper;

import com.github.cameltooling.lsp.internal.parser.ParserFileHelper;
import com.github.cameltooling.lsp.internal.parser.ParserFileHelperFactory;
import com.github.cameltooling.lsp.internal.parser.ParserXMLFileHelper;
import org.eclipse.lsp4j.TextDocumentItem;

public class XMLFileParser extends Parser {

	private final Parser nextFileParser;

	XMLFileParser(Parser parser) {
		this.nextFileParser = parser;
	}

	@Override
	public ParserFileHelper getMatchedFileParser(TextDocumentItem textDocumentItem, int line, ParserFileHelperFactory parserFileHelperFactory) {
		ParserXMLFileHelper xmlParser = new ParserXMLFileHelper();
		String uri = textDocumentItem.getUri();
		if (uri.endsWith(".xml") && xmlParser.getCorrespondingCamelNodeForCompletion(textDocumentItem, line) != null) {
			return xmlParser;
		} else if (nextFileParser != null) {
			return nextFileParser.getMatchedFileParser(textDocumentItem, line, parserFileHelperFactory);
		}

		return null;
	}

}

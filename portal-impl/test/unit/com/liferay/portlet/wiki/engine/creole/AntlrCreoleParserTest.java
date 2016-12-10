/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.wiki.engine.creole;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.parsers.creole.ast.ASTNode;
import com.liferay.portal.parsers.creole.ast.BaseListNode;
import com.liferay.portal.parsers.creole.ast.BoldTextNode;
import com.liferay.portal.parsers.creole.ast.CollectionNode;
import com.liferay.portal.parsers.creole.ast.ForcedEndOfLineNode;
import com.liferay.portal.parsers.creole.ast.FormattedTextNode;
import com.liferay.portal.parsers.creole.ast.HorizontalNode;
import com.liferay.portal.parsers.creole.ast.ImageNode;
import com.liferay.portal.parsers.creole.ast.ItalicTextNode;
import com.liferay.portal.parsers.creole.ast.ItemNode;
import com.liferay.portal.parsers.creole.ast.LineNode;
import com.liferay.portal.parsers.creole.ast.ListNode;
import com.liferay.portal.parsers.creole.ast.NoWikiSectionNode;
import com.liferay.portal.parsers.creole.ast.ParagraphNode;
import com.liferay.portal.parsers.creole.ast.ScapedNode;
import com.liferay.portal.parsers.creole.ast.UnformattedTextNode;
import com.liferay.portal.parsers.creole.ast.UnorderedListItemNode;
import com.liferay.portal.parsers.creole.ast.UnorderedListNode;
import com.liferay.portal.parsers.creole.ast.WikiPageNode;
import com.liferay.portal.parsers.creole.ast.link.LinkNode;
import com.liferay.portal.parsers.creole.ast.table.TableDataNode;
import com.liferay.portal.parsers.creole.ast.table.TableHeaderNode;
import com.liferay.portal.parsers.creole.ast.table.TableNode;
import com.liferay.portal.parsers.creole.parser.Creole10Lexer;
import com.liferay.portal.parsers.creole.parser.Creole10Parser;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Miguel Pastor
 * @author Manuel de la Peña
 */
public class AntlrCreoleParserTest {

	@Test
	public void testParseCorrectlyBoldContentInListItems() {
		BaseListNode unorderedListNode = parseBaseListNode("list-6.creole");

		Assert.assertEquals(1, unorderedListNode.getChildASTNodesCount());

		UnorderedListItemNode unorderedListItemNode =
			(UnorderedListItemNode)unorderedListNode.getChildASTNode(0);

		Assert.assertNotNull(unorderedListItemNode);

		FormattedTextNode formattedTextNode =
			(FormattedTextNode)unorderedListItemNode.getChildASTNode(1);

		BoldTextNode boldTextNode =
			(BoldTextNode)formattedTextNode.getChildASTNode(0);

		CollectionNode collectionNode =
			(CollectionNode)boldTextNode.getChildASTNode(0);

		UnformattedTextNode unformattedTextNode =
			(UnformattedTextNode)collectionNode.get(0);

		Assert.assertEquals("abcdefg", unformattedTextNode.getContent());
	}

	@Test
	public void testParseCorrectlyItalicContentInListItems() {
		UnorderedListNode unorderedListNode =
			(UnorderedListNode)parseBaseListNode("list-5.creole");

		Assert.assertEquals(1, unorderedListNode.getChildASTNodesCount());

		UnorderedListItemNode unorderedListItemNode =
			(UnorderedListItemNode)unorderedListNode.getChildASTNode(0);

		Assert.assertNotNull(unorderedListItemNode);
		Assert.assertEquals(2, unorderedListItemNode.getChildASTNodesCount());

		FormattedTextNode formattedTextNode =
			(FormattedTextNode)unorderedListItemNode.getChildASTNode(1);

		ItalicTextNode italicTextNode =
			(ItalicTextNode)formattedTextNode.getChildASTNode(0);

		CollectionNode collectionNode =
			(CollectionNode)italicTextNode.getChildASTNode(0);

		UnformattedTextNode unformattedTextNode =
			(UnformattedTextNode)collectionNode.get(0);

		Assert.assertEquals("abcdefg", unformattedTextNode.getContent());
	}

	@Test
	public void testParseCorrectlyOneItemFirstLevel() {
		executeFirstLevelItemListTests("list-1.creole", 1);
	}

	@Test
	public void testParseCorrectlyOneOrderedItemFirstLevel() {
		executeFirstLevelItemListTests("list-7.creole", 1);
	}

	@Test
	public void testParseCorrectlyThreeItemFirstLevel() {
		executeFirstLevelItemListTests("list-3.creole", 3);
	}

	@Test
	public void testParseCorrectlyThreeOrderedItemFirstLevel() {
		executeFirstLevelItemListTests("list-9.creole", 3);
	}

	@Test
	public void testParseCorrectlyTwoItemFirstLevel() {
		executeFirstLevelItemListTests("list-2.creole", 2);
	}

	@Test
	public void testParseCorrectlyTwoOrderedItemFirstLevel() {
		executeFirstLevelItemListTests("list-8.creole", 2);
	}

	@Test
	public void testParseEmpyImageTag() {
		WikiPageNode wikiPageNode = getWikiPageNode("image-4.creole");

		Assert.assertNotNull(wikiPageNode);

		ParagraphNode paragraphNode = (ParagraphNode)
			wikiPageNode.getChildASTNode(0);

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(1, lineNode.getChildASTNodesCount());

		ImageNode imageNode = (ImageNode)lineNode.getChildASTNode(0);

		Assert.assertEquals(StringPool.BLANK, imageNode.getLink());

		CollectionNode collectionNode = imageNode.getAltNode();

		Assert.assertNull(collectionNode);
	}

	@Test
	public void testParseHeadingBlocksMultiple() {
		WikiPageNode wikiPageNode = getWikiPageNode("heading-10.creole");

		Assert.assertEquals(3, wikiPageNode.getChildASTNodesCount());
	}

	@Test
	public void testParseHorizontalBlock() {
		WikiPageNode wikiPageNode = getWikiPageNode("horizontal-1.creole");

		Assert.assertEquals(1, wikiPageNode.getChildASTNodesCount());
		Assert.assertTrue(
			wikiPageNode.getChildASTNode(0) instanceof HorizontalNode);
	}

	@Test
	public void testParseHorizontalMixedBlocks() {
		WikiPageNode wikiPageNode = getWikiPageNode("horizontal-3.creole");

		Assert.assertEquals(3, wikiPageNode.getChildASTNodesCount());
		Assert.assertTrue(
			wikiPageNode.getChildASTNode(1) instanceof HorizontalNode);
	}

	@Test
	public void testParseHorizontalTwoBlocks() {
		WikiPageNode wikiPageNode = getWikiPageNode("horizontal-2.creole");

		Assert.assertEquals(2, wikiPageNode.getChildASTNodesCount());
		Assert.assertTrue(
			wikiPageNode.getChildASTNode(0) instanceof HorizontalNode);
		Assert.assertTrue(
			wikiPageNode.getChildASTNode(1) instanceof HorizontalNode);
	}

	@Test
	public void testParseMultilineTextParagraph() {
		WikiPageNode wikiPageNode = getWikiPageNode("text-2.creole");

		Assert.assertNotNull(wikiPageNode);
		Assert.assertEquals(1, wikiPageNode.getChildASTNodesCount());

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		List<ASTNode> astNodes = paragraphNode.getChildASTNodes();

		Assert.assertEquals(10, astNodes.size());

		for (int i = 0; i < astNodes.size(); i++) {
			ASTNode astNode = astNodes.get(i);

			LineNode lineNode = (LineNode)astNode;

			Assert.assertEquals(1, lineNode.getChildASTNodesCount());

			UnformattedTextNode unformattedTextNode =
				(UnformattedTextNode)lineNode.getChildASTNode(0);

			unformattedTextNode =
				(UnformattedTextNode)unformattedTextNode.getChildASTNode(0);

			Assert.assertEquals(
				"Simple P" + i, unformattedTextNode.getContent());
		}
	}

	@Test
	public void testParseMultipleImageTags() {
		WikiPageNode wikiPageNode = getWikiPageNode("image-5.creole");

		Assert.assertNotNull(wikiPageNode);

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(5, lineNode.getChildASTNodesCount());

		List<ASTNode> astNodes = lineNode.getChildASTNodes();

		for (int i = 0; i < astNodes.size();) {
			ImageNode imageNode = (ImageNode)astNodes.get(i);

			Assert.assertEquals("L" + ++i, imageNode.getLink());
		}
	}

	@Test
	public void testParseNoWikiBlock() {
		WikiPageNode wikiPageNode = getWikiPageNode("nowikiblock-1.creole");

		Assert.assertEquals(1, wikiPageNode.getChildASTNodesCount());
	}

	@Test
	public void testParseNoWikiBlockEmpty() {
		WikiPageNode wikiPageNode = getWikiPageNode("nowikiblock-3.creole");

		NoWikiSectionNode noWikiSectionNode =
			(NoWikiSectionNode)wikiPageNode.getChildASTNode(0);

		Assert.assertEquals(StringPool.BLANK, noWikiSectionNode.getContent());
	}

	@Test
	public void testParseNoWikiBlockMultiple() {
		WikiPageNode wikiPageNode = getWikiPageNode("nowikiblock-2.creole");

		Assert.assertEquals(3, wikiPageNode.getChildASTNodesCount());
	}

	@Test
	public void testParseNoWikiBlockNonEmpty() {
		WikiPageNode wikiPageNode = getWikiPageNode("nowikiblock-4.creole");

		NoWikiSectionNode noWikiSectionNode =
			(NoWikiSectionNode)wikiPageNode.getChildASTNode(0);

		Assert.assertEquals(
			"This is a non \\empty\\ block", noWikiSectionNode.getContent());
	}

	@Test
	public void testParseOnlySpacesContentInImageTag() {
		WikiPageNode wikiPageNode = getWikiPageNode("image-3.creole");

		Assert.assertNotNull(wikiPageNode);

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(1, lineNode.getChildASTNodesCount());

		ImageNode imageNode = (ImageNode)lineNode.getChildASTNode(0);

		Assert.assertEquals("  ", imageNode.getLink());

		CollectionNode collectionNode = imageNode.getAltNode();

		Assert.assertNull(collectionNode);
	}

	@Test
	public void testParseSimpleImageTag() {
		WikiPageNode wikiPageNode = getWikiPageNode("image-1.creole");

		Assert.assertNotNull(wikiPageNode);

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(1, lineNode.getChildASTNodesCount());

		ImageNode imageNode = (ImageNode)lineNode.getChildASTNode(0);

		Assert.assertEquals("link", imageNode.getLink());

		CollectionNode collectionNode = imageNode.getAltNode();

		Assert.assertNotNull(collectionNode);
		Assert.assertEquals(1, collectionNode.size());

		List<ASTNode> astNodes = collectionNode.getASTNodes();

		UnformattedTextNode unformattedTextNode =
			(UnformattedTextNode)astNodes.get(0);

		unformattedTextNode =
			(UnformattedTextNode)unformattedTextNode.getChildASTNode(0);

		Assert.assertEquals(
			"alternative text", unformattedTextNode.getContent());
	}

	@Test
	public void testParseSimpleImageTagWithNoAlternative() {
		WikiPageNode wikiPageNode = getWikiPageNode("image-2.creole");

		Assert.assertNotNull(wikiPageNode);

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(1, lineNode.getChildASTNodesCount());

		ImageNode imageNode = (ImageNode)lineNode.getChildASTNode(0);

		Assert.assertEquals("link", imageNode.getLink());

		CollectionNode collectionNode = imageNode.getAltNode();

		Assert.assertNull(collectionNode);
	}

	@Test
	public void testParseSimpleLinkTag() {
		WikiPageNode wikiPageNode = getWikiPageNode("link-1.creole");

		Assert.assertNotNull(wikiPageNode);

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(1, lineNode.getChildASTNodesCount());

		LinkNode linkNode = (LinkNode)lineNode.getChildASTNode(0);

		Assert.assertEquals("link", linkNode.getLink());

		CollectionNode collectionNode = linkNode.getAltCollectionNode();

		Assert.assertNotNull(collectionNode);
		Assert.assertEquals(1, collectionNode.size());

		List<ASTNode> astNodes = collectionNode.getASTNodes();

		UnformattedTextNode unformattedTextNode =
			(UnformattedTextNode)astNodes.get(0);

		CollectionNode unformattedTextNodes =
			(CollectionNode)unformattedTextNode.getChildASTNode(0);

		unformattedTextNode = (UnformattedTextNode)unformattedTextNodes.get(0);

		Assert.assertEquals(
			"alternative text", unformattedTextNode.getContent());
	}

	@Test
	public void testParseSimpleLinkTagWithoutDescription() {
		WikiPageNode wikiPageNode = getWikiPageNode("link-2.creole");

		Assert.assertNotNull(wikiPageNode);

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(1, lineNode.getChildASTNodesCount());

		LinkNode linkNode = (LinkNode)lineNode.getChildASTNode(0);

		Assert.assertEquals("link", linkNode.getLink());
		Assert.assertNull(linkNode.getAltCollectionNode());
	}

	@Test
	public void testParseSimpleLinkTagWithoutDescription2() {
		WikiPageNode wikiPageNode = getWikiPageNode("link-3.creole");

		Assert.assertNotNull(wikiPageNode);

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(5, lineNode.getChildASTNodesCount());

		List<ASTNode> astNodes = lineNode.getChildASTNodes();

		for (ASTNode astNode : astNodes) {
			if (!(astNode instanceof LinkNode)) {
				continue;
			}

			LinkNode linkNode = (LinkNode)astNode;

			Assert.assertEquals("L", linkNode.getLink());

			CollectionNode collectionNode = linkNode.getAltCollectionNode();

			Assert.assertNotNull(collectionNode);
			Assert.assertEquals(1, collectionNode.size());

			List<ASTNode> collectionNodeASTNodes = collectionNode.getASTNodes();

			UnformattedTextNode unformattedTextNode =
				(UnformattedTextNode)collectionNodeASTNodes.get(0);

			collectionNode =
				(CollectionNode)unformattedTextNode.getChildASTNode(0);

			unformattedTextNode = (UnformattedTextNode)collectionNode.get(0);

			Assert.assertEquals("A", unformattedTextNode.getContent());
		}
	}

	@Test
	public void testParseSimpleTextBoldAndItalics() {
		WikiPageNode wikiPageNode = getWikiPageNode("text-6.creole");

		Assert.assertNotNull(wikiPageNode);
		Assert.assertEquals(1, wikiPageNode.getChildASTNodesCount());

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		Assert.assertEquals(1, paragraphNode.getChildASTNodesCount());

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(2, lineNode.getChildASTNodesCount());

		UnformattedTextNode unformattedTextNode =
			(UnformattedTextNode)lineNode.getChildASTNode(0);

		unformattedTextNode =
			(UnformattedTextNode)unformattedTextNode.getChildASTNode(0);

		Assert.assertEquals("Text ", unformattedTextNode.getContent());

		BoldTextNode boldTextNode = (BoldTextNode)lineNode.getChildASTNode(1);

		Assert.assertEquals(1, boldTextNode.getChildASTNodesCount());

		ItalicTextNode italicTextNode =
			(ItalicTextNode)boldTextNode.getChildASTNode(0);

		CollectionNode collectionNode =
			(CollectionNode)italicTextNode.getChildASTNode(0);

		unformattedTextNode = (UnformattedTextNode)collectionNode.get(0);

		Assert.assertEquals("ItalicAndBold", unformattedTextNode.getContent());
	}

	@Test
	public void testParseSimpleTextParagraph() {
		WikiPageNode wikiPageNode = getWikiPageNode("text-1.creole");

		Assert.assertNotNull(wikiPageNode);
		Assert.assertEquals(1, wikiPageNode.getChildASTNodesCount());

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		List<ASTNode> astNodes = paragraphNode.getChildASTNodes();

		Assert.assertEquals(1, astNodes.size());

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(1, lineNode.getChildASTNodesCount());

		UnformattedTextNode unformattedTextNode =
			(UnformattedTextNode)lineNode.getChildASTNode(0);

		unformattedTextNode =
			(UnformattedTextNode)unformattedTextNode.getChildASTNode(0);

		Assert.assertEquals(
			"Simple paragraph", unformattedTextNode.getContent());
	}

	@Test
	public void testParseSimpleTextWithBold() {
		WikiPageNode wikiPageNode = getWikiPageNode("text-4.creole");

		Assert.assertNotNull(wikiPageNode);
		Assert.assertEquals(1, wikiPageNode.getChildASTNodesCount());

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		Assert.assertEquals(1, paragraphNode.getChildASTNodesCount());

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(2, lineNode.getChildASTNodesCount());

		UnformattedTextNode unformattedTextNode =
			(UnformattedTextNode)lineNode.getChildASTNode(0);

		unformattedTextNode =
			(UnformattedTextNode)unformattedTextNode.getChildASTNode(0);

		Assert.assertEquals(
			"Text with some content in ", unformattedTextNode.getContent());

		BoldTextNode boldTextContent = (BoldTextNode)lineNode.getChildASTNode(
			1);

		FormattedTextNode formattedTextNode =
			(FormattedTextNode)boldTextContent.getChildASTNode(0);

		CollectionNode collectionNode =
			(CollectionNode)formattedTextNode.getChildASTNode(0);

		unformattedTextNode = (UnformattedTextNode)collectionNode.get(0);

		Assert.assertEquals("bold", unformattedTextNode.getContent());
	}

	@Test
	public void testParseSimpleTextWithBoldAndItalics() {
		WikiPageNode wikiPageNode = getWikiPageNode("text-5.creole");

		Assert.assertNotNull(wikiPageNode);
		Assert.assertEquals(1, wikiPageNode.getChildASTNodesCount());

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		Assert.assertEquals(1, paragraphNode.getChildASTNodesCount());

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(4, lineNode.getChildASTNodesCount());

		UnformattedTextNode unformattedTextNode =
			(UnformattedTextNode)lineNode.getChildASTNode(0);

		unformattedTextNode =
			(UnformattedTextNode)unformattedTextNode.getChildASTNode(0);

		Assert.assertEquals(
			"Text with some content in ", unformattedTextNode.getContent());

		BoldTextNode boldTextNode = (BoldTextNode)lineNode.getChildASTNode(1);

		Assert.assertEquals(1, boldTextNode.getChildASTNodesCount());

		FormattedTextNode formattedTextNode =
			(FormattedTextNode)boldTextNode.getChildASTNode(0);

		CollectionNode collectionNode =
			(CollectionNode)formattedTextNode.getChildASTNode(0);

		unformattedTextNode = (UnformattedTextNode)collectionNode.get(0);

		Assert.assertEquals("bold", unformattedTextNode.getContent());
	}

	@Test
	public void testParseSimpleTextWithForcedEndline() {
		WikiPageNode wikiPageNode = getWikiPageNode("text-7.creole");

		Assert.assertNotNull(wikiPageNode);
		Assert.assertEquals(1, wikiPageNode.getChildASTNodesCount());

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		Assert.assertEquals(1, paragraphNode.getChildASTNodesCount());

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(3, lineNode.getChildASTNodesCount());

		UnformattedTextNode unformattedTextNode =
			(UnformattedTextNode)lineNode.getChildASTNode(0);

		unformattedTextNode =
			(UnformattedTextNode)unformattedTextNode.getChildASTNode(0);

		Assert.assertEquals("Text with ", unformattedTextNode.getContent());

		CollectionNode collectionNode =
			(CollectionNode)lineNode.getChildASTNode(1);

		Assert.assertEquals(1, collectionNode.size());
		Assert.assertTrue(collectionNode.get(0) instanceof ForcedEndOfLineNode);

		collectionNode = (CollectionNode)lineNode.getChildASTNode(2);

		unformattedTextNode = (UnformattedTextNode)collectionNode.get(0);

		Assert.assertEquals(
			"forced line break", unformattedTextNode.getContent());
	}

	@Test
	public void testParseSimpleTextWithItalics() {
		WikiPageNode wikiPageNode = getWikiPageNode("text-3.creole");

		Assert.assertNotNull(wikiPageNode);

		List<ASTNode> astNodes = wikiPageNode.getChildASTNodes();

		Assert.assertEquals(1, astNodes.size());

		ParagraphNode paragraphNode = (ParagraphNode)astNodes.get(0);

		astNodes = paragraphNode.getChildASTNodes();

		Assert.assertEquals(1, astNodes.size());

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(2, lineNode.getChildASTNodesCount());

		UnformattedTextNode unformattedTextNode =
			(UnformattedTextNode)lineNode.getChildASTNode(0);

		unformattedTextNode =
			(UnformattedTextNode)unformattedTextNode.getChildASTNode(0);

		Assert.assertEquals(
			"Text with some content in ", unformattedTextNode.getContent());

		ItalicTextNode italicTextNode =
			(ItalicTextNode)lineNode.getChildASTNode(1);

		FormattedTextNode formattedTextNode =
			(FormattedTextNode)italicTextNode.getChildASTNode(0);

		CollectionNode collectionNode =
			(CollectionNode)formattedTextNode.getChildASTNode(0);

		unformattedTextNode = (UnformattedTextNode)collectionNode.get(0);

		Assert.assertEquals("italic", unformattedTextNode.getContent());
	}

	@Test
	public void testParseSimpleTextWithItalicTextInMultipleLines() {
		WikiPageNode wikiPageNode = getWikiPageNode("text-8.creole");

		Assert.assertNotNull(wikiPageNode);
	}

	@Test
	public void testParseTableMultipleRowsAndCOlumns() {
		WikiPageNode wikiPageNode = getWikiPageNode("table-2.creole");

		Assert.assertNotNull(wikiPageNode);

		TableNode tableNode = (TableNode)wikiPageNode.getChildASTNode(0);

		Assert.assertNotNull(tableNode);
		Assert.assertEquals(4, tableNode.getChildASTNodesCount());

		CollectionNode collectionNode =
			(CollectionNode)tableNode.getChildASTNode(0);

		Assert.assertEquals(4, collectionNode.size());

		for (int i = 0; i < 4; ++i) {
			TableHeaderNode tableHeaderNode =
				(TableHeaderNode)collectionNode.get(i);

			Assert.assertNotNull(tableHeaderNode);

			UnformattedTextNode unformattedTextNode =
				(UnformattedTextNode)tableHeaderNode.getChildASTNode(0);

			Assert.assertNotNull(unformattedTextNode);
			Assert.assertEquals(1, unformattedTextNode.getChildASTNodesCount());

			unformattedTextNode =
				(UnformattedTextNode)unformattedTextNode.getChildASTNode(0);

			Assert.assertEquals(
				"H" + (i + 1), unformattedTextNode.getContent());
		}

		int count = 1;

		for (int row = 1; row < 4; ++row) {
			collectionNode = (CollectionNode)tableNode.getChildASTNode(row);

			Assert.assertEquals(4, collectionNode.size());

			for (int column = 0; column < 4; ++column) {
				TableDataNode tableDataNode = (TableDataNode)collectionNode.get(
					column);

				Assert.assertNotNull(tableDataNode);

				UnformattedTextNode unformattedTextNode =
					(UnformattedTextNode)tableDataNode.getChildASTNode(0);

				Assert.assertNotNull(unformattedTextNode);
				Assert.assertEquals(
					1, unformattedTextNode.getChildASTNodesCount());

				unformattedTextNode =
					(UnformattedTextNode)unformattedTextNode.getChildASTNode(0);

				Assert.assertEquals(
					"C" + count++, unformattedTextNode.getContent());
			}
		}
	}

	@Test
	public void testParseTableOneRowOneColumn() {
		WikiPageNode wikiPageNode = getWikiPageNode("table-1.creole");

		Assert.assertNotNull(wikiPageNode);

		TableNode tableNode = (TableNode)wikiPageNode.getChildASTNode(0);

		Assert.assertNotNull(tableNode);
		Assert.assertEquals(2, tableNode.getChildASTNodesCount());

		CollectionNode collectionNode =
			(CollectionNode)tableNode.getChildASTNode(0);

		Assert.assertEquals(1, collectionNode.size());

		TableHeaderNode tableHeaderNode = (TableHeaderNode)collectionNode.get(
			0);

		Assert.assertNotNull(tableHeaderNode);

		UnformattedTextNode unformattedTextNode =
			(UnformattedTextNode)tableHeaderNode.getChildASTNode(0);

		Assert.assertNotNull(unformattedTextNode);
		Assert.assertEquals(1, unformattedTextNode.getChildASTNodesCount());

		unformattedTextNode =
			(UnformattedTextNode)unformattedTextNode.getChildASTNode(0);

		Assert.assertEquals("H1", unformattedTextNode.getContent());

		List<ASTNode> astNodes = tableNode.getChildASTNodes();

		collectionNode = (CollectionNode)astNodes.get(1);

		Assert.assertEquals(1, collectionNode.size());

		TableDataNode tableDataNode = (TableDataNode)collectionNode.get(0);

		Assert.assertNotNull(tableDataNode);

		unformattedTextNode =
			(UnformattedTextNode)tableDataNode.getChildASTNode(0);

		Assert.assertNotNull(unformattedTextNode);
		Assert.assertEquals(1, unformattedTextNode.getChildASTNodesCount());

		unformattedTextNode =
			(UnformattedTextNode)unformattedTextNode.getChildASTNode(0);

		Assert.assertEquals("C1.1", unformattedTextNode.getContent());
	}

	@Test
	public void testSimpleEscapedCharacter() {
		WikiPageNode wikiPageNode = getWikiPageNode("escape-1.creole");

		Assert.assertNotNull(wikiPageNode);
		Assert.assertEquals(1, wikiPageNode.getChildASTNodesCount());

		ParagraphNode paragraphNode =
			(ParagraphNode)wikiPageNode.getChildASTNode(0);

		Assert.assertEquals(2, paragraphNode.getChildASTNodesCount());

		LineNode lineNode = (LineNode)paragraphNode.getChildASTNode(0);

		Assert.assertEquals(2, lineNode.getChildASTNodesCount());

		UnformattedTextNode unformattedTextNode =
			(UnformattedTextNode)lineNode.getChildASTNode(0);

		ScapedNode scapedNode = (ScapedNode)unformattedTextNode.getChildASTNode(
			0);

		Assert.assertEquals("E", scapedNode.getContent());

		CollectionNode collectionNode =
			(CollectionNode)lineNode.getChildASTNode(1);

		unformattedTextNode = (UnformattedTextNode)collectionNode.get(0);

		Assert.assertEquals("SCAPED1", unformattedTextNode.getContent());
	}

	protected void executeFirstLevelItemListTests(String fileName, int count) {
		BaseListNode baseListNode = parseBaseListNode(fileName);

		Assert.assertEquals(count, baseListNode.getChildASTNodesCount());

		for (ASTNode astNode : baseListNode.getChildASTNodes()) {
			ItemNode itemNode = (ItemNode)astNode;

			Assert.assertNotNull(itemNode);
			Assert.assertEquals(1, itemNode.getLevel());
		}
	}

	protected Creole10Parser getCreole10Parser(String fileName)
		throws IOException {

		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/" + fileName);

		ANTLRInputStream antlrInputStream = new ANTLRInputStream(inputStream);

		Creole10Lexer creole10Lexer = new Creole10Lexer(antlrInputStream);

		CommonTokenStream commonTokenStream = new CommonTokenStream(
			creole10Lexer);

		return new Creole10Parser(commonTokenStream);
	}

	protected WikiPageNode getWikiPageNode(String fileName) {
		try {
			_creole10parser = getCreole10Parser(fileName);

			_creole10parser.wikipage();
		}
		catch (IOException ioe) {
			Assert.fail("File does not exist");
		}
		catch (RecognitionException re) {
			Assert.fail("File could not be parsed");
		}

		return _creole10parser.getWikiPageNode();
	}

	protected BaseListNode parseBaseListNode(String fileName) {
		WikiPageNode wikiPageNode = getWikiPageNode(fileName);

		ListNode listNode = (ListNode)wikiPageNode.getChildASTNode(0);

		Assert.assertNotNull(listNode);

		return (BaseListNode)listNode.getChildASTNode(0);
	}

	private Creole10Parser _creole10parser;

}
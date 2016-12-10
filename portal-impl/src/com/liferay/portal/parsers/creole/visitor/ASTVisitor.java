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

package com.liferay.portal.parsers.creole.visitor;

import com.liferay.portal.parsers.creole.ast.BoldTextNode;
import com.liferay.portal.parsers.creole.ast.CollectionNode;
import com.liferay.portal.parsers.creole.ast.ForcedEndOfLineNode;
import com.liferay.portal.parsers.creole.ast.FormattedTextNode;
import com.liferay.portal.parsers.creole.ast.HeadingNode;
import com.liferay.portal.parsers.creole.ast.HorizontalNode;
import com.liferay.portal.parsers.creole.ast.ImageNode;
import com.liferay.portal.parsers.creole.ast.ItalicTextNode;
import com.liferay.portal.parsers.creole.ast.LineNode;
import com.liferay.portal.parsers.creole.ast.ListNode;
import com.liferay.portal.parsers.creole.ast.NoWikiSectionNode;
import com.liferay.portal.parsers.creole.ast.OrderedListItemNode;
import com.liferay.portal.parsers.creole.ast.OrderedListNode;
import com.liferay.portal.parsers.creole.ast.ParagraphNode;
import com.liferay.portal.parsers.creole.ast.ScapedNode;
import com.liferay.portal.parsers.creole.ast.UnformattedTextNode;
import com.liferay.portal.parsers.creole.ast.UnorderedListItemNode;
import com.liferay.portal.parsers.creole.ast.UnorderedListNode;
import com.liferay.portal.parsers.creole.ast.WikiPageNode;
import com.liferay.portal.parsers.creole.ast.extension.TableOfContentsNode;
import com.liferay.portal.parsers.creole.ast.link.LinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.C2InterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.DokuWikiInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.FlickrInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.GoogleInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.JSPWikiInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.MeatballInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.MediaWikiInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.MoinMoinInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.OddmuseInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.OhanaInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.PmWikiInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.PukiWikiInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.PurpleWikiInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.RadeoxInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.SnipSnapInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.TWikiInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.TiddlyWikiInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.UsemodInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.WikipediaInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.link.interwiki.XWikiInterwikiLinkNode;
import com.liferay.portal.parsers.creole.ast.table.TableDataNode;
import com.liferay.portal.parsers.creole.ast.table.TableHeaderNode;
import com.liferay.portal.parsers.creole.ast.table.TableNode;

/**
 * @author Miguel Pastor
 */
public interface ASTVisitor {

	public void visit(BoldTextNode boldTextNode);

	public void visit(C2InterwikiLinkNode c2InterwikiLinkNode);

	public void visit(CollectionNode collectionNode);

	public void visit(DokuWikiInterwikiLinkNode dokuWikiInterwikiLinkNode);

	public void visit(FlickrInterwikiLinkNode flickrInterwikiLinkNode);

	public void visit(ForcedEndOfLineNode forcedEndOfLineNode);

	public void visit(FormattedTextNode formattedTextNode);

	public void visit(GoogleInterwikiLinkNode googleInterwikiLinkNode);

	public void visit(HeadingNode headingNode);

	public void visit(HorizontalNode horizontalNode);

	public void visit(ImageNode imageNode);

	public void visit(ItalicTextNode italicTextNode);

	public void visit(JSPWikiInterwikiLinkNode jspWikiInterwikiLinkNode);

	public void visit(LineNode lineNode);

	public void visit(LinkNode linkNode);

	public void visit(ListNode linkNode);

	public void visit(MeatballInterwikiLinkNode meatballInterwikiLinkNode);

	public void visit(MediaWikiInterwikiLinkNode mediaWikiInterwikiLinkNode);

	public void visit(MoinMoinInterwikiLinkNode moinMoinInterwikiLinkNode);

	public void visit(NoWikiSectionNode noWikiSectionNode);

	public void visit(OddmuseInterwikiLinkNode oddmuseInterwikiLinkNode);

	public void visit(OhanaInterwikiLinkNode ohanaInterwikiLinkNode);

	public void visit(OrderedListItemNode orderedListItemNode);

	public void visit(OrderedListNode orderedListNode);

	public void visit(ParagraphNode paragraphNode);

	public void visit(PmWikiInterwikiLinkNode pmWikiInterwikiLinkNode);

	public void visit(PukiWikiInterwikiLinkNode pukiWikiInterwikiLinkNode);

	public void visit(PurpleWikiInterwikiLinkNode purpleWikiInterwikiLinkNode);

	public void visit(RadeoxInterwikiLinkNode radeoxInterwikiLinkNode);

	public void visit(ScapedNode scapedNode);

	public void visit(SnipSnapInterwikiLinkNode snipSnapInterwikiLinkNode);

	public void visit(TableDataNode tableDataNode);

	public void visit(TableHeaderNode tableHeaderNode);

	public void visit(TableNode tableNode);

	public void visit(TableOfContentsNode tableOfContentsNode);

	public void visit(TiddlyWikiInterwikiLinkNode tiddlyWikiInterwikiLinkNode);

	public void visit(TWikiInterwikiLinkNode tWikiInterwikiLinkNode);

	public void visit(UnformattedTextNode unformattedTextNode);

	public void visit(UnorderedListItemNode unorderedListItemNode);

	public void visit(UnorderedListNode unorderedListNode);

	public void visit(UsemodInterwikiLinkNode usemodInterwikiLinkNode);

	public void visit(WikiPageNode wikiPageNode);

	public void visit(WikipediaInterwikiLinkNode wikipediaInterwikiLinkNode);

	public void visit(XWikiInterwikiLinkNode xWikiInterwikiLinkNode);

}
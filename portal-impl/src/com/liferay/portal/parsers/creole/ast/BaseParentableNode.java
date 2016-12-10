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

package com.liferay.portal.parsers.creole.ast;

import java.util.List;

/**
 * @author Miguel Pastor
 */
public abstract class BaseParentableNode extends ASTNode {

	public BaseParentableNode() {
	}

	public BaseParentableNode(CollectionNode collectionNode) {
		if (collectionNode != null) {
			_collectionNode = collectionNode;
		}
	}

	public BaseParentableNode(int tokenType) {
		super(tokenType);
	}

	public void addChildASTNode(ASTNode astNode) {
		_collectionNode.add(astNode);
	}

	public ASTNode getChildASTNode(int position) {
		return _collectionNode.get(position);
	}

	public List<ASTNode> getChildASTNodes() {
		return _collectionNode.getASTNodes();
	}

	public int getChildASTNodesCount() {
		return _collectionNode.size();
	}

	private CollectionNode _collectionNode = new CollectionNode();

}
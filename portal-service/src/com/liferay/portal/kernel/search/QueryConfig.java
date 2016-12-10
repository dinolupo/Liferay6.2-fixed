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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class QueryConfig implements Serializable {

	public static final String LOCALE = "locale";

	public static final String SEARCH_SUBFOLDERS = "search.subfolders";

	public Serializable getAttribute(String name) {
		return _attributes.get(name);
	}

	public Map<String, Serializable> getAttributes() {
		return _attributes;
	}

	public int getCollatedSpellCheckResultScoresThreshold() {
		return GetterUtil.getInteger(
			_attributes.get(
				PropsKeys.
					INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_SCORES_THRESHOLD),
				_INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_SCORES_THRESHOLD);
	}

	public int getHighlightFragmentSize() {
		return GetterUtil.getInteger(
			_attributes.get(PropsKeys.INDEX_SEARCH_HIGHLIGHT_FRAGMENT_SIZE),
			_INDEX_SEARCH_HIGHLIGHT_FRAGMENT_SIZE);
	}

	public int getHighlightSnippetSize() {
		return GetterUtil.getInteger(
			_attributes.get(PropsKeys.INDEX_SEARCH_HIGHLIGHT_SNIPPET_SIZE),
			_INDEX_SEARCH_HIGHLIGHT_SNIPPET_SIZE);
	}

	public Locale getLocale() {
		Locale locale = (Locale)_attributes.get(LOCALE);

		if (locale == null) {
			locale = LocaleUtil.getMostRelevantLocale();
		}

		return locale;
	}

	public int getQueryIndexingThreshold() {
		return GetterUtil.getInteger(
			_attributes.get(
				PropsKeys.INDEX_SEARCH_QUERY_INDEXING_THRESHOLD),
				_INDEX_SEARCH_QUERY_INDEXING_THRESHOLD);
	}

	public int getQuerySuggestionMax() {
		return GetterUtil.getInteger(
			_attributes.get(
				PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_MAX),
				_INDEX_SEARCH_QUERY_SUGGESTION_MAX);
	}

	public int getQuerySuggestionScoresThreshold() {
		return GetterUtil.getInteger(
			_attributes.get(
				PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_SCORES_THRESHOLD),
				_INDEX_SEARCH_QUERY_SUGGESTION_SCORES_THRESHOLD);
	}

	public boolean isCollatedSpellCheckResultEnabled() {
		return GetterUtil.getBoolean(
			_attributes.get(
				PropsKeys.INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_ENABLED),
				_INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_ENABLED);
	}

	public boolean isHighlightEnabled() {
		return GetterUtil.getBoolean(
			_attributes.get(PropsKeys.INDEX_SEARCH_HIGHLIGHT_ENABLED), false);
	}

	public boolean isHitsProcessingEnabled() {
		return GetterUtil.getBoolean(
			_attributes.get(_HITS_PROCESSING_ENABLED), true);
	}

	public boolean isQueryIndexingEnabled() {
		return GetterUtil.getBoolean(
			_attributes.get(
				PropsKeys.INDEX_SEARCH_QUERY_INDEXING_ENABLED),
				_INDEX_SEARCH_QUERY_INDEXING_ENABLED);
	}

	public boolean isQuerySuggestionEnabled() {
		return GetterUtil.getBoolean(
			_attributes.get(PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_ENABLED),
			_INDEX_SEARCH_QUERY_SUGGESTION_ENABLED);
	}

	public boolean isScoreEnabled() {
		return GetterUtil.getBoolean(
			_attributes.get(PropsKeys.INDEX_SEARCH_SCORING_ENABLED),
			_INDEX_SEARCH_SCORING_ENABLED);
	}

	public boolean isSearchSubfolders() {
		return GetterUtil.getBoolean(_attributes.get(SEARCH_SUBFOLDERS));
	}

	public Serializable removeAttribute(String name) {
		return _attributes.remove(name);
	}

	public void setAttribute(String name, Serializable value) {
		_attributes.put(name, value);
	}

	public void setCollatedSpellCheckResultEnabled(
		boolean collatedSpellCheckResultEnabled) {

		_attributes.put(
			PropsKeys.INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_ENABLED,
			collatedSpellCheckResultEnabled);
	}

	public void setCollatedSpellCheckResultScoresThreshold(
		int collatedSpellCheckResultScoresThreshold) {

		_attributes.put(
			PropsKeys.
				INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_SCORES_THRESHOLD,
			collatedSpellCheckResultScoresThreshold);
	}

	public void setHighlightEnabled(boolean highlightEnabled) {
		if (_INDEX_SEARCH_HIGHLIGHT_ENABLED) {
			_attributes.put(
				PropsKeys.INDEX_SEARCH_HIGHLIGHT_ENABLED, highlightEnabled);
		}
		else {
			_attributes.put(PropsKeys.INDEX_SEARCH_HIGHLIGHT_ENABLED, false);
		}
	}

	public void setHighlightFragmentSize(int highlightFragmentSize) {
		_attributes.put(
			PropsKeys.INDEX_SEARCH_HIGHLIGHT_FRAGMENT_SIZE,
			highlightFragmentSize);
	}

	public void setHighlightSnippetSize(int highlightSnippetSize) {
		_attributes.put(
			PropsKeys.INDEX_SEARCH_HIGHLIGHT_SNIPPET_SIZE,
			highlightSnippetSize);
	}

	public void setHitsProcessingEnabled(boolean hitsProcessingEnabled) {
		_attributes.put(_HITS_PROCESSING_ENABLED, hitsProcessingEnabled);
	}

	public void setLocale(Locale locale) {
		_attributes.put(LOCALE, locale);
	}

	public void setQueryIndexingEnabled(boolean enabled) {
		_attributes.put(PropsKeys.INDEX_SEARCH_QUERY_INDEXING_ENABLED, enabled);
	}

	public void setQueryIndexingThreshold(int queryIndexingThreshold) {
		_attributes.put(
			PropsKeys.INDEX_SEARCH_QUERY_INDEXING_THRESHOLD,
			queryIndexingThreshold);
	}

	public void setQuerySuggestionEnabled(boolean querySuggestionEnabled) {
		_attributes.put(
			PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_ENABLED,
			querySuggestionEnabled);
	}

	public void setQuerySuggestionScoresThreshold(
		int querySuggestionScoresThreshold) {
			_attributes.put(
				PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_SCORES_THRESHOLD,
				querySuggestionScoresThreshold);
	}

	public void setQuerySuggestionsMax(int querySuggestionMax) {
		_attributes.put(
			PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_MAX, querySuggestionMax);
	}

	public void setScoreEnabled(boolean scoreEnabled) {
		_attributes.put(PropsKeys.INDEX_SEARCH_SCORING_ENABLED, scoreEnabled);
	}

	public void setSearchSubfolders(boolean searchSubfolders) {
		_attributes.put(SEARCH_SUBFOLDERS, searchSubfolders);
	}

	private static final String _HITS_PROCESSING_ENABLED =
		"hitsProcessingEnabled";

	private static final boolean
		_INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_ENABLED =
			GetterUtil.getBoolean(
				PropsUtil.get(
					PropsKeys.INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_ENABLED),
				true);

	private static final int
		_INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_SCORES_THRESHOLD =
			GetterUtil.getInteger(
				PropsUtil.get(
					PropsKeys.
						INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_SCORES_THRESHOLD),
					50);

	private static final boolean _INDEX_SEARCH_HIGHLIGHT_ENABLED =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.INDEX_SEARCH_HIGHLIGHT_ENABLED));

	private static final int _INDEX_SEARCH_HIGHLIGHT_FRAGMENT_SIZE =
		GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.INDEX_SEARCH_HIGHLIGHT_FRAGMENT_SIZE));

	private static final int _INDEX_SEARCH_HIGHLIGHT_SNIPPET_SIZE =
		GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.INDEX_SEARCH_HIGHLIGHT_SNIPPET_SIZE));

	private static final boolean _INDEX_SEARCH_QUERY_INDEXING_ENABLED =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.INDEX_SEARCH_QUERY_INDEXING_ENABLED), true);

	private static final int _INDEX_SEARCH_QUERY_INDEXING_THRESHOLD =
		GetterUtil.getInteger(
			PropsUtil.get(
				PropsKeys.INDEX_SEARCH_QUERY_INDEXING_THRESHOLD),
			50);

	private static final boolean _INDEX_SEARCH_QUERY_SUGGESTION_ENABLED =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_ENABLED),
			true);

	private static final int _INDEX_SEARCH_QUERY_SUGGESTION_MAX =
		GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_MAX), 5);

	private static final int
		_INDEX_SEARCH_QUERY_SUGGESTION_SCORES_THRESHOLD = GetterUtil.getInteger(
			PropsUtil.get(
				PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_SCORES_THRESHOLD), 50);

	private static final boolean _INDEX_SEARCH_SCORING_ENABLED =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.INDEX_SEARCH_SCORING_ENABLED));

	private Map<String, Serializable> _attributes =
		new HashMap<String, Serializable>();

}
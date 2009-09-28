package com.appspot.gaejwiki.common.wiki;

import com.appspot.gaejwiki.common.wiki.base.SameAddBlockBase;

/**
 * WikiObject
 * 整形済みテキスト
 * 
 * --
 * 
行頭が半角空白で始まる行は整形済みテキストとなります。行の自動折り返しは行なわれません。

整形済みテキストは、他のブロック要素の子要素になることができます。
整形済みテキストは、他のブロック要素を子要素にすることができません。
整形済みテキストは、すべての子要素を文字列として扱います。
 * 
 * --
 * 
 * @author daxanya
 *
 */
public class FormatedBlock extends SameAddBlockBase  {

}

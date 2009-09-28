package com.appspot.gaejwiki.common.wiki;

import com.appspot.gaejwiki.common.wiki.base.SameAddBlockBase;

/**
 * WikiObject
 * 段落
 * @author daxanya
 * 
 * --
 * 
他のブロック要素を明示しない限り、段落となります。

行頭で ~ を指定した場合も段落になります。行頭書式の文字(~、-、+、:、>、|、#、//)を通常の文字として段落の先頭に書きたい場合は、行頭に~を記述して書くことができます。

段落の先頭は1文字分字下げされます。但し、番号なしリスト構造、番号付きリスト構造、引用文内の段落では字下げされません。定義リスト内の段落の先頭は1文字分字下げされます。
段落は、新たなブロック要素が現われるまで継続します。
段落は、他のブロック要素の子要素になることができます。
段落は、他のブロック要素を子要素にすることはできません。
 *
 * --
 */
public class ParagraphBlock extends SameAddBlockBase {

}

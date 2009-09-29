package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.YesChildNoParentInlineBase;

/**
 * WikiObject
 * inline WikiName
 * @author daxanya
 *
 *
 *行中で、1つ以上の大文字→1つ以上の小文字→1つ以上の大文字→1つ以上の小文字の組合わせからなる半角//文字列はWikiNameになります。

WikiNameの中には、全角文字や半角空白文字、記号、数字を含めることはできません。
WikiNameは、PukiWiki内のページ名になります。すでに存在するページであればそのページへのリンクが自動的に貼られます。存在しない場合はWikiNameの後ろに?が自動的に付き、そのページを新規作成するためのリンクが貼られます。
WikiNameは、他のインライン要素の子要素になることができます。
WikiNameは、他のインライン要素を子要素にはできません。
 *
 */

public class WikiNameInline extends YesChildNoParentInlineBase {

}

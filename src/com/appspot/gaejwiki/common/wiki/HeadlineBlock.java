package com.appspot.gaejwiki.common.wiki;

import com.appspot.gaejwiki.common.wiki.base.NoChildParentBlockBase;



/**
 * WikiObject
 * 見出し
 * @author daxanya
 *
 * --
 * 
行頭で * を記述すると、見出しになります。見出しは *、**、*** の3段階あります。

見出しは、他のブロック要素の子要素になることはできません。見出しが現われると他のブロック要素は終了します。
見出しは、他のブロック要素を子要素にすることはできません。
 * 
 * --

 */
public class HeadlineBlock extends NoChildParentBlockBase {

}

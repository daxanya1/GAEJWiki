package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.NoChildParentInlineBase;

/**
 * WikiObject
 * inline 改行
 * @author daxanya
 *
 *
 *行末に~を書くと行末改行になります。行末改行はブロック要素内での改行になります。

行末改行の次の行の行頭書式は無効になり、文字列として扱われます。
行末改行は、他のインライン要素の子要素になることはできません。
行末改行は、他のインライン要素を子要素にすることはできません。
行末改行は、定義リストの定義語、表組みの要素、#で始まるブロック要素のパラメタの中では使用できません。
 *
 */

public class NewlineInline extends NoChildParentInlineBase {

}

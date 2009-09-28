package com.appspot.gaejwiki.common.wiki;

import com.appspot.gaejwiki.common.wiki.base.YesChildNoAddlineBlockBase;


/**
 * WikiObject
 * CSV
 * @author daxanya
 * 
 * --
 * 
行頭でカンマ(,)を記述し、インライン要素をカンマ区切りで記述すると表組みになります。

インライン要素はダブルクォーテーション(")で囲むことができます。ダブルクォーテーションで囲むことで、カンマ(,)を含むインライン要素を記述できます。
ダブルクォーテーション(")で囲んだデータの中で、ダブルクォーテーションを2つ("")続けることで、ダブルクォーテーション(")を含むインライン要素を記述できます。
インライン要素の代わりにイコールを2つ(==)記述すると、colspanを意味します。
インライン要素の左に1つ以上の半角空白文字を記述すると右寄せに、インライン要素の左右に1つ以上の半角空白文字を記述するとセンタリングになります。
表組みは、他のブロック要素の子要素になることができます。
表組みは、他のブロック要素を子要素にすることができません。

 *
 * --
 */
public class CsvBlock extends YesChildNoAddlineBlockBase {

}

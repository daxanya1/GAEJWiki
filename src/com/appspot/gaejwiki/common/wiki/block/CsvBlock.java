package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.YesChildNoAddlineBlockBase;


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

	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * CSVどうかチェックする
		 * CSV要素が一文字目であれば、CSVとする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return CSVであればtrue
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// CSV要素が一文字目であれば、CSVとする
			return (line.charAt(0) == CSV) ? true : false;
		}
	}
}

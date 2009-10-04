package com.appspot.gaejwiki.common.wiki.block;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.base.ListBlockBase;

/**
 * WikiObject
 * 番号なしリスト
 * @author daxanya
 *
 * --
 *
行頭で - を指定すると、番号なしリストになります。番号なしリストは -、--、--- の3レベルあります。

番号なしリストは、他のブロック要素の子要素になることができます。他のリスト構造の子要素にする場合は、レベルを1段増やして記述します。引用文の子要素にする場合は、レベルを増やさずに記述します。
-の直後に ~を記述すると段落を子要素にすることができます。
番号なしリストは、リストの先頭がインライン要素または段落である場合に限り、リストの次の行に他のブロック要素を記述することで、他のブロック要素を子要素にすることができます。
 *
 * --
 */
public class UnnumberedListBlock extends ListBlockBase {

	@Override
	protected List<String> cutDataList(List<String> datalist) {
		List<String> list = new ArrayList<String>();
		if (datalist == null) {
			return list;
		}
		
		
		
		return list;
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {

		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// 水平線であれば違う
			if (new HorizonBlock.Checker().isThis(line)) {
				return false;
			}
			
			// NUMBEREDLIST要素かUNNUMBEREDLIST要素が一文字目であれば、リストとする
			return (line.charAt(0) == UNNUMBEREDLIST) ? true : false;
		}
	}
}

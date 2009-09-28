package com.appspot.gaejwiki.common.wiki;

import com.appspot.gaejwiki.common.wiki.base.SameAddBlockBase;

/**
 * WikiObject
 * 表組
 * @author daxanya
 * 
 * --
 * 
行頭から | でインライン要素を区切ることで表組みになります。

各要素の先頭に下記の記述子を指定できます。
LEFT:
CENTER:
RIGHT:
BGCOLOR(色):
COLOR(色):
SIZE(サイズ):
要素の表示位置及び背景色・文字色・文字サイズ(px単位)を指定します。デフォルトは左寄せになります。
表組みの	各セルの要素の配置に	関するサンプル
左寄せ	センタリング	右寄せ
右寄せ	左寄せ	センタリング
行末にcを記述すると、書式指定行となります。書式指定行では、次の記述子が指定できます。
LEFT:
CENTER:
RIGHT:
BGCOLOR(色):
COLOR(色):
SIZE(サイズ):
記述子の後ろに数値を記述すると、セル幅がpx単位で指定できます。
行末にhを記述すると、ヘッダ行(thead)になります。
行末にfを記述すると、フッタ行(tfoot)になります。
セル内のインライン要素の先頭に~を付けると、ヘッダ(th)になります。
セル内に > を単独で記述すると右のセルと連結します(colspan)。
セル内に ~ を単独で記述すると上のセルと連結します(rowspan)。
表組みは、他のブロック要素の子要素になることができます。
表組みは、他のブロック要素を子要素にすることができません。

 *
 * --
 */
public class TableBlock extends SameAddBlockBase {

}

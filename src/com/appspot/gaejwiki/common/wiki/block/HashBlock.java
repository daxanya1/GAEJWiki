/**
 Copyright 2009 GAEJWiki Team.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.appspot.gaejwiki.common.wiki.block;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.appspot.gaejwiki.common.wiki.block.base.YesChildNoAddlineBlockBase;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;


/**
 * WikiObject
 * #～のブロック
 * @author daxanya
 * 
 * --
 * 
#contents : 目次
#hr : 水平線
#br : 行間開け
#clear : テキストの回り込みの解除
#comment,#pcomment, #article : フォーム

#ref(添付ファイル名)
#ref(ファイルのURL)
行頭で #ref を記述すると、添付ファイルまたは指定されたURLにあるファイルへのリンクを貼り付けることができます。ファイルが画像ファイルの場合は、その画像を表示します。

#ref には、カンマで区切って下記のパラメタを指定できます。パラメタを省略した場合はデフォルト値となります。
添付ファイルのページ
添付ファイルが存在するページ名を指定します。デフォルトは現在のページです。
このパラメタを指定する場合は、添付ファイル名の次に記述します。
left
center
right
ファイルへのリンク・画像を表示する位置を指定します。デフォルトではセンタリングされます。
wrap
nowrap
ファイルへのリンク・画像を枠で囲むかどうかを指定します。デフォルトは囲みません。
around
テキストの回り込みを指定します。デフォルトでは回り込みを行ないません。
nolink
デフォルトでは添付ファイルへのリンクが張られますが、nolinkを指定するとリンクを張りません。
代替文字列
ファイル名の代わりに表示する文字列や画像の代替文字列を指定できます。指定しない場合は、ファイル名になります。
代替文字列には文字列以外のインライン要素を含めることはできません。ページ名、文字列以外のインライン要素を記述しても文字列として扱われます。
このパラメタを指定する場合は、最後に記述します。
#refは、他のブロック要素の子要素になることができます。
#refは、他のブロック要素を子要素にすることはできません。

#vote(選択肢1,選択肢2,...)
行頭で #vote を記述すると、簡易投票フォームが埋め込まれます。

選択肢は文字列、ページ名、InterWiki、リンクを含めることができます。その他のインライン要素を含めることができません。
#voteは、他のブロック要素の子要素になることができますが、トップレベルに設置することを前提に左マージンを設定してありますので、他のブロック要素の子要素にはしないでください。
#voteは、他のブロック要素を子要素にすることはできません。
 *
 * --
 */
public class HashBlock extends YesChildNoAddlineBlockBase {

	enum HashBlockType {
		contents,
		hr,
		br,
		ref,
		clear,
		comment,
		pcomment,
		article,
		vote
	}
	
	private HashBlockType type;
	
	@Override
	public String toHtmlString() {
		if (type == null) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		switch (type) {
		case hr:
			sb.append("<hr class=\"short_line\" />");
			break;
		case br:
			sb.append("<div class=\"spacer\">&nbsp;</div>");
			break;
		default:
			break;
		}
		sb.append(new Util().getLineSeparator());
		return sb.toString();
	}
	
	@Override
	public void paserInline(WikiInlineParser parser) {
		Sub sub = new Sub();
		type = sub.getType(getRawData(), HASHFORMATPATTERN1);
		if (type == null) {
			type = sub.getType(getRawData(), HASHFORMATPATTERN2);
		}
		if (type == null) {
			type = sub.getType(getRawData(), HASHFORMATPATTERN3);
		}
		if (type == null) {
			return;
		}
		
		switch (type) {
		case ref:
			// ref用特殊処理
			break;
		case vote:
			// vote用特殊処理
			break;
		}

	}
	
	static public class Sub {
		
		/**
		 * lineを正規表現にかけて、マッチしたら、typeを返す
		 * @param line
		 * @param pat
		 * @return
		 */
		public HashBlockType getType(String line, String pat) {
			Pattern pattern = Pattern.compile(pat);
			Matcher matcher = pattern.matcher(line);
			if (!matcher.find()) {
				return null;
			}
			if (matcher.groupCount() == 0) {
				return null;
			}
			for (HashBlockType blocktype : HashBlockType.values()) {
				if (blocktype.toString().equals(matcher.group(1))) {
					return blocktype;
				}
			}
			return null;
		}
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			return (new WikiObjectInlineI.Checker.Util().getRegexMatcherLength(line, HASHFORMATPATTERN1) > 0) ? true : 
				(new WikiObjectInlineI.Checker.Util().getRegexMatcherLength(line, HASHFORMATPATTERN2) > 0) ? true :
				(new WikiObjectInlineI.Checker.Util().getRegexMatcherLength(line, HASHFORMATPATTERN3) > 0) ? true : false;
		}

	}

}

package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.YesChildNoParentInlineBase;

/**
 * WikiObject
 * inline &()系 のうち、子要素になれるが親要素になれないもの
 * @author daxanya
 *
 *
 *
 *行中に &br; を書くと、そこで改行されます。行末の ~ と違い、定義リストの定義語や表組の要素、# で始まるブロック要素のパラメタの中でも使用できます。

行中改行は、他のインライン要素の子要素になることができます。
行中改行は、他のインライン要素を子要素にすることはできません。

&ref(添付ファイル名);
&ref(ファイルのURL);
行中で &ref を記述すると、添付ファイルまたは指定されたURLにあるファイルへのリンクを貼り付けることができます。ファイルが画像ファイルの場合は、その画像を表示します。

&ref には、カンマで区切って下記のパラメタを指定できます。パラメタを省略した場合はデフォルト値となります。
添付ファイルのページ
添付ファイルが存在するページ名を指定します。デフォルトは現在のページです。
このパラメタを指定する場合は、添付ファイル名の次に記述します。
nolink
デフォルトでは添付ファイルへのリンクが張られますが、nolink を指定するとリンクを張りません。
代替文字列
ファイル名の代わりに表示する文字列や画像の代替文字列を指定できます。指定しない場合は、ファイル名になります。
代替文字列には文字列以外のインライン要素を含めることはできません。ページ名、文字列以外のインライン要素を記述しても文字列として扱われます。
このパラメタを指定する場合は、最後に記述します。
&ref は、他のインライン要素の子要素になることができます。
&ref は、他のインライン要素を子要素にはできません。


行中で &counter を記述するとそのページにアクセスした人の数を表示することができます。

&counter には次のオプションを指定できます。オプションを省略した場合はtotalが指定されたものとみなされます。
today
今日のアクセス数を表示します。
yesterday
昨日のアクセス数を表示します。
total
アクセス総数を表示します。
カウンタ表示は、他のインライン要素の子要素になることができます。
カウンタ表示は、他のインライン要素を子要素にはできません。


&online;
行中で &online を記述すると現在アクセス中の人数を表示することができます。

オンライン表示は、他のインライン要素の子要素になることができます。
オンライン表示は、他のインライン要素を子要素にはできません。


&version;
行中で &version を記述するとPukiWikiのバージョンを表示することができます。

バージョン表示は、他のインライン要素の子要素になることができます。
バージョン表示は、他のインライン要素を子要素にはできません。


行中で &t; と書くと、タブコードに置換されます。

通常、フォーム内ではキーボードからタブコードを直接入力できない*4ので、タブコードを入力したい位置に&t;を記述すると、保存時にタブコードに置換します。

タブコードは、記事が書き込まれるときに置換されて記録されます。
タブコードは、他のインライン要素の子要素になることができます。
タブコードは、他のインライン要素を子要素にはできません。


行中で &page; 及び &fpage; と書くと、編集中のページ名に置換されます。ページ名が階層化されたページで「hogehoge/hoge」となっている場合、 &page; は「hoge」に、 &fpage; は「hogehoge/hoge」に置換されます。階層化されていないページでは、 &page; も &fpage; も同じになります。

ページ名置換文字は、記事が書き込まれるときに置換されて記録されます。
ページ名置換文字は、他のインライン要素の子要素になることができます。
ページ名置換文字は、他のインライン要素を子要素にはできません。

行中で &date; と書くと、更新時の日付に置換されます
行中で &time; と書くと、更新時の時刻に置換されます
行中で &now; と書くと、更新時の日時に置換されます
&date;、 &time;、 &now;は、記事が書き込まれるときに置換されて記録されます。
&date;、 &time;、 &now;は、他のインライン要素の子要素になることができます。
&date;、 &time;、 &now;は、他のインライン要素を子要素にはできません。


行中で &_date; と書くと、表示時の日付に置換されて出力されます。
行中で &_time; と書くと、表示時の時刻に置換されて出力されます。
行中で &_now; と書くと、表示時の日時に置換されて出力されます。
行中で &lastmod; と書くと、そのページの最終更新日時に置換されて出力されます。
ページ名を指定すると、その指定されたページの最終更新日時に置換されて出力されます。
&_date;、 &_time;、 &_now;、 &lastmod;は、記事が表示されるときに置換されて出力されます。
&_date;、 &_time;、 &_now;、 &lastmod;は、他のインライン要素の子要素になることができます。
&_date;、 &_time;、 &_now;、 &lastmod;は、他のインライン要素を子要素にはできません。


行中で&heart;と書くと、ハートマーク &heart; に置換されて出力されます。
行中で&smile;と書くと、 &smile; に置換されて出力されます。
行中で&bigsmile;と書くと、 &bigsmile; に置換されて出力されます。
行中で&huh;と書くと、 &huh; に置換されて出力されます。
行中で&oh;と書くと、 &oh; に置換されて出力されます。
行中で&wink;と書くと、 &wink; に置換されて出力されます。
行中で&sad;と書くと、 &sad; に置換されて出力されます。
行中で&worried;と書くと、 &worried; に置換されて出力されます。
文字参照文字は、表示されるときに置換されます。
文字参照文字は、他のインライン要素の子要素になることができます。
文字参照文字は、他のインライン要素を子要素にはできません。


行中で &# と ; の間に10進数を、&#x と ;の間に16進数を書くと、Unicodeを数値参照して表示します。キーボードから直接入力できない文字やJIS第3水準・第4水準の文字などの表示に使用します。たとえば、内田百&#38290;と入力すると、内田百�閧ﾆ表示されます。

数値参照文字は、表示されるときに置換されます。
数値参照文字は、他のインライン要素の子要素になることができます。
数値参照文字は、他のインライン要素を子要素にはできません。
16進数は半角小文字で指定する必要があります(XHTML 1.0 第2版から小文字に限定されました)。

 *
 */

public class AmpersandChildInline extends YesChildNoParentInlineBase {

}

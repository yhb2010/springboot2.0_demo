selectByChapterListID
===
*根据章节策略id查询
	select a.chapterID, a.chapterName, b.chapterListName
	from qz_chapter a, qz_chapterlist b
	where a.chapterListID = b.chapterListID
	@if(!isEmpty(chapterID)){
		and a.chapterID=#chapterID#
	@}

queryForPage
===
*翻页，pageTag使得在求总数的时候输出成count(1)
	select
	@pageTag(){
		*
	@}
	from qz_chapter where chapterListID = #chapterListID#

selectChapterList
===
	select * from qz_chapter where chapterListID = #chapterListID#
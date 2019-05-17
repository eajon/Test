package cn.csfz.eajon.test.common.page;


import com.github.eajon.annotation.Name;

public class Page {

    @Name(value = "pageNum")
    private int page = 1;
    @Name(value = "pageSize")
    private int size = 10;
    @Name(require = false)
    private PageMeta.PageData pageData;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public PageMeta.PageData getPageData() {
        return pageData;
    }

    public void setPageData(PageMeta.PageData pageData) {
        this.pageData = pageData;
        this.page = pageData.getPageNum();
    }

    public void nextPage() {
        page++;
    }

    public void prePage() {
        if (page > 1)
            page--;
    }

    public boolean hasNextPage() {
        if (pageData != null) {
            if (pageData.getSize() >= page) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}

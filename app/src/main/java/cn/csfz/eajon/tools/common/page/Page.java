package cn.csfz.eajon.tools.common.page;


import com.github.eajon.annotation.GsonField;

public class Page {

    @GsonField(value = "pageNum")
    private int page = 1;
    @GsonField(value = "pageSize")
    private int size = 10;
    @GsonField(require = false)
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
        if (page > 1) {
            page--;
        }
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

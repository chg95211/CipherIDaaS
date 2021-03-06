package cipher.console.oidc.domain.web;

import java.util.ArrayList;
import java.util.List;

public class GroupPageInfoDomain {

    private Integer currentPage;//当前页数
    private Integer prevPage;//上一页的页数
    private Integer nextPage;//下一页页数
    private List<GroupInfoDomain> showUsers =new ArrayList<GroupInfoDomain>();//应用内容
    private List<Integer> showPageNums =new ArrayList<Integer>();//总页数比如{1,2，3，4,5}

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(Integer prevPage) {
        this.prevPage = prevPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public List<GroupInfoDomain> getShowUsers() {
        return showUsers;
    }

    public void setShowUsers(List<GroupInfoDomain> showUsers) {
        this.showUsers = showUsers;
    }

    public List<Integer> getShowPageNums() {
        return showPageNums;
    }

    public void setShowPageNums(List<Integer> showPageNums) {
        this.showPageNums = showPageNums;
    }

    @Override
    public String toString() {
        return "GroupPageInfoDomain{" +
                "currentPage=" + currentPage +
                ", prevPage=" + prevPage +
                ", nextPage=" + nextPage +
                ", showUsers=" + showUsers +
                ", showPageNums=" + showPageNums +
                '}';
    }
}

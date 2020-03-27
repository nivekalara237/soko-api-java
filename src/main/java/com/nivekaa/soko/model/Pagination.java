package com.nivekaa.soko.model;

/**
 * @author nivekaa
 * Created 27/03/2020 at 00:04
 * Class com.nivekaa.soko.model.Pagination
 */

/**
 *
 * "total": 26,
 * "count": 25,
 * "per_page": 25,
 * "current_page": 1,
 * "total_pages": 2,
 * "links": {
 *    "next": "http://127.0.0.1:8000/storage/api/v1/files?user%5Bincrementing%5D=1&user%5Bexists%5D=1&user%5BwasRecentlyCreated%5D=0&user%5Btimestamps%5D=1&api%5Bincrementing%5D=1&api%5Bexists%5D=1&api%5BwasRecentlyCreated%5D=0&api%5Btimestamps%5D=1&page=2"
 * }
 *
 *
 */

public class Pagination {
    private int total;
    private int count;
    private int current_page;
    private int per_page;
    private int total_pages;

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pagination{");
        sb.append("total=").append(total);
        sb.append(", count=").append(count);
        sb.append(", current_page=").append(current_page);
        sb.append(", per_page=").append(per_page);
        sb.append(", total_pages=").append(total_pages);
        sb.append('}');
        return sb.toString();
    }
}

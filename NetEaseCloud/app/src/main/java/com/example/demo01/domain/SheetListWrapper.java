package com.example.demo01.domain;

import java.util.List;

/**
 *歌单列表模型
 *
 * 只是用来测试
 */
public class SheetListWrapper {
    /**
     * 歌单列表
     */

    //json格式的key值要与class的key相同不然找不到
    private ObjectItem data; // 注意这里不是 List<Article>，而是一个对象

    public ObjectItem getData() {
        return data;
    }

    public void setData(ObjectItem data) {
        this.data = data;
    }

    public class ObjectItem {
        private int total;
        private int pages;
        private int size;
        private int page;
        private int next;
        private List<Sheet> data; // 这里是真正的文章列表数组

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getNext() {
            return next;
        }

        public void setNext(int next) {
            this.next = next;
        }

        public List<Sheet> getData() {
            return data;
        }

        public void setData(List<Sheet> data) {
            this.data = data;
        }
    }
}

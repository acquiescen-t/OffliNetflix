package com.trevor.OffliNetflix.TMDBFilm;

public class TMDBFilm {
    private int page;
    private Result[] results;
    private int totalPages;
    private int totalResults;

    public TMDBFilm() {

    }
    public TMDBFilm(int page, Result[] results, int totalPages, int totalResults) {
        this.setPage(page);
        this.setResults(results);
        this.setTotalPages(totalPages);
        this.setTotalResults(totalResults);
    }

    public int getPage() {
        return this.page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public Result[] getResults() {
        return this.results;
    }
    public void setResults(Result[] results) {
        this.results = results;
    }
    public int getTotalPages() {
        return this.totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getTotalResults() {
        return this.totalResults;
    }
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}

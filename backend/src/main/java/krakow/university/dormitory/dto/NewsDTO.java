package krakow.university.dormitory.dto;

public class NewsDTO {
    private String title;
    private String content;

    public NewsDTO() {
    }

    public NewsDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


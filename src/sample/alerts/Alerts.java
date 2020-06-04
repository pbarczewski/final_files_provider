package sample.alerts;

import javafx.scene.control.Alert;

public class Alerts {

    private Alert alertType;
    private String title;
    private String header;
    private String content;

    public Alerts(Alert alertType, String title, String header, String content) {
        this.alertType = alertType;
        this.title = title;
        this.header = header;
        this.content = content;
    }

    public static class Builder {

        private Alert alertType;
        private String title;
        private String header;
        private String content;


        public Builder alertType(Alert alertType) {
            this.alertType = alertType;
            return this;
        }


        public Builder title(String title) {
            this.title = title;
            this.alertType.setTitle(title);
            return this;
        }

        public Builder header(String header) {
            this.header = header;
            this.alertType.setHeaderText(header);
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            this.alertType.setContentText(content);
            return this;
        }



        public Alerts build() {
            this.alertType.showAndWait();
            return new Alerts(this);
        }
    }

    public Alerts(Builder builder) {
        this.alertType = builder.alertType;
        this.title = builder.title;
        this.header = builder.header;
        this.content = builder.content;
    }

}


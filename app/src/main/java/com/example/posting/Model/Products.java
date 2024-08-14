package com.example.posting.Model;

import android.net.Uri;
import java.io.Serializable;
public class Products implements Serializable {


        private String pname, description, price, image, category, pid, date, time;

        private String uid;

        public Products() {


        }

        public Products(String pname, String description, String price, String image, String category, String pid, String date, String time,String uid) {
            this.pname = pname;
            this.description = description;
            this.price = price;
            this.image = image;
            this.category = category;
            this.pid = pid;
            this.date = date;
            this.time = time;
            this.uid=uid;
        }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

        public String getUname() { return uid; }
        public void   setUname(String uname) {this.uid=uid ;}
        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

    public String getImage() {
        return image;
    }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }



        public void setTime(String time) {
            this.time = time;
        }


}


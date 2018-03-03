package org.marker.certificate.bean;/**
 * Created by marker on 2018/2/25.
 */

import java.io.Serializable;

/**
 *
 *
 * @author marker
 * @create 2018-02-25 上午10:19
 **/
public class Semester implements Serializable {

    private String name;

    private int id;

    public Semester(String name, int id) {
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

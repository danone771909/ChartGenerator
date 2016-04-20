package com.chart.repositories;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Daniel on 2016-04-17.
 */
@Entity
@Table(name = "chart")
public class Chart implements Serializable{

    private static final long serialVersionUID = 12321231L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String input;

    private String chartname;

    @Column(length = 65536)
    private String result;

    public Chart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getChartname() {
        return chartname;
    }

    public void setChartname(String chartname) {
        this.chartname = chartname;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "id: " + id + " input: " + input + " chartname: " + chartname + " result: " + result;
    }
}

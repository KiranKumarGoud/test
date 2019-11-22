package com.excelra.mvc.model.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FilterInputVisualizationDTO implements Serializable
{

        private String key;
        private Integer clicked;
        private List<VisualChartData> data = new ArrayList<VisualChartData>();
        private final static long serialVersionUID = -6993098848598145887L;

        /**
         * No args constructor for use in serialization
         *
         */
        public FilterInputVisualizationDTO() {
        }

        /**
         *
         * @param data
         * @param clicked
         * @param key
         */
        public FilterInputVisualizationDTO(String key, Integer clicked, List<VisualChartData> data) {
            super();
            this.key = key;
            this.clicked = clicked;
            this.data = data;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Integer getClicked() {
            return clicked;
        }

        public void setClicked(Integer clicked) {
            this.clicked = clicked;
        }

        public List<VisualChartData> getData() {
            return data;
        }

        public void setData(List<VisualChartData> data) {
            this.data = data;
        }

    }


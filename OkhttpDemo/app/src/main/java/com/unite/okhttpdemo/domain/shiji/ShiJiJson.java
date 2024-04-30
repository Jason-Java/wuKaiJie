package com.unite.okhttpdemo.domain.shiji;

import com.unite.okhttpdemo.domain.response.BaseResponse;

import java.util.List;

/**
 *
 */
public class ShiJiJson extends BaseResponse {

    private Response response;

    public class Response{

        private List<ShiJi> Data;

        public List<ShiJi> getData() {
            return Data;
        }

        public void setData(List<ShiJi> data) {
            Data = data;
        }
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}

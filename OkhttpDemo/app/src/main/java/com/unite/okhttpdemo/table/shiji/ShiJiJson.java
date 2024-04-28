package com.unite.okhttpdemo.table.shiji;

import com.unite.okhttpdemo.domain.response.BaseResponse;
import com.unite.okhttpdemo.domain.user.OneUser;
import com.unite.okhttpdemo.domain.user.TwoCompany;
import com.unite.okhttpdemo.domain.user.TwoUser;

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

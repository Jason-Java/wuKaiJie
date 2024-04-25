package com.unite.okhttpdemo.domain.user;

import com.unite.okhttpdemo.domain.response.BaseResponse;

import java.util.List;

/**
 *
 */
public class OneUser extends BaseResponse {
    private Response response;

    public class Response{

        private TwoUser user;

        private List<TwoCompany> company;

        public TwoUser getTwoUser() {
            return user;
        }

        public void setTwoUser(TwoUser twoUser) {
            this.user = twoUser;
        }

        public List<TwoCompany> getTwoCompany() {
            return company;
        }

        public void setTwoCompany(List<TwoCompany> twoCompany) {
            this.company = twoCompany;
        }
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
